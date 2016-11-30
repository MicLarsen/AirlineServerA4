package JPATest;

import Entities.Airport;
import Entities.Airroute;
import Entities.FlightPrices;
import JPA.FlightJPA;
import JPA.JPAUtils;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nickl
 */
public class FlightJPATest {
    
    private static JPAUtils jpau;
    private static List<FlightPrices> fpList;
    private static List<Airroute> arList;
    private static EntityManagerFactory emf;
    
    @Rule
    public final ExpectedException exception = ExpectedException.none();
    
    public FlightJPATest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("AirlinePU");
        fpList = new ArrayList();
        arList = new ArrayList();
        jpau = new JPAUtils();
    }
    
    @AfterClass
    public static void tearDownClass() {
        cleanupDataBase();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test //TFD!
    public void DatabaseConnectionTest(){
        System.out.println("#DatabaseConnectionTest Started!#");
        
        EntityManager em = jpau.getEntityManager();
        
        assertNotNull(em);
        System.out.println("#DatabaseConnectionTest Completed#");
    }
    
    @Ignore
    @Test //TFD!
    public void FlightPriceEntityPersistToDatabaseTest(){
        System.out.println("#Starting FlightPriceEntityPersistToDatabaseTest#");
        
        EntityManager em = emf.createEntityManager();
        FlightPrices fp = new FlightPrices("test", "test", 20.00);
        FlightJPA fjpa = new FlightJPA();
        FlightPrices objPersisted = fjpa.persistFlightPrices(fp);
        fpList.add(fp);
        
        assertEquals(objPersisted.getClass(), em.find(FlightPrices.class, objPersisted.getId()).getClass()); 
        System.out.println("#FlightPriceEntityPersistToDatabaseTest Completed#");
    }
    
    
    @Test
    public void AirrouteEntityPersistToDatabase(){
        System.out.println("#AirrouteEntityPersistToDatabase started!#");
        
        EntityManager em = emf.createEntityManager();
        
        Airport cph = em.getReference(Airport.class, "CPH");
        Airport jfk = em.getReference(Airport.class, "JFK");
        
        Airroute ar = new Airroute("testAirline", "12345679876543", "767687909087656789", new Date(), 10983, 123098, cph, jfk);
        try{
        FlightJPA fjpa = new FlightJPA();
        Airroute objPersisted = fjpa.persistAirroute(ar);
        arList.add(objPersisted);
        assertEquals(objPersisted.getClass(), em.find(Airroute.class, objPersisted.getFlightID()).getClass());
        System.out.println("#AirrouteEntityPersistToDatabase Completed!#");
        } catch(SQLIntegrityConstraintViolationException e){
        }
    }
    
    @Ignore
    @Test
    public void DuplicateEntryOnAirroutePersistToDatabase() throws SQLIntegrityConstraintViolationException {
        System.out.println("#DuplicateEntryOnAirroutePersistToDatabase Started#");
        
        EntityManager em = emf.createEntityManager();
        
        Airport cph = new Airport("CPH", "Copenhagen Airport");
        Airport atl = new Airport("ATL", "Hartsfield-Jackson Atlanta International Airport");
        
        Airroute ar = new Airroute("testAirline", "123456879", "464646", new Date(), 1, 24, cph, atl);
        Airroute ar2 = new Airroute("testAirline", "123456879", "464646", new Date(), 1, 24, cph, atl);
        arList.add(ar);
        FlightJPA fjpa = new FlightJPA();
        
        fjpa.persistAirroute(ar);
        
        exception.expect(SQLIntegrityConstraintViolationException.class);
        
        fjpa.persistAirroute(ar2);
        System.out.println("#DuplicateEntryOnAirroutePersistToDatabase Completed#");
    }
    
    @Ignore
    @Test
    public void getFlightsByOriginAndDestination(){
        System.out.println("#getFlightsByOriginAndDestination Started#");
        
        String origin = "CPH";
        String destination = "ATL";
        String date = "testDate";
        String tickets = "2";
        
        insertDummyFlights();
        
        FlightJPA fjpa = new FlightJPA();
        
        List<Airroute> returnedList = fjpa.getFlightsByOriginDest(origin, destination, date, tickets);
        System.out.println("");
        assertEquals(3, returnedList.size());
        assertEquals("3234", returnedList.get(2).getFlightID());
        
        System.out.println("#getFlightsByOriginAndDestination Completed!#");
    }
    
    private void insertDummyFlights(){
        List<Airroute> list = new ArrayList();
        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        
        Airport cph = new Airport("CPH", "Copenhagen Airport");
        Airport atl = new Airport("ATL", "Hartsfield-Jackson Atlanta International Airport");
        Airport jfk = new Airport("JFK", "John F. Kennedy International Airport");
        
        list.add(new Airroute("testAirline1", "1234", "1726381723", new Date(), 32, 404, cph, atl));
        list.add(new Airroute("testAirline2", "2234", "1726381723", new Date(), 32, 404, cph, atl));
        list.add(new Airroute("testAirline3", "3234", "1726381723", new Date(), 32, 404, cph, atl));
        list.add(new Airroute("testAirline4", "4234", "1726381723", new Date(), 32, 404, jfk, atl));
        
        try{
            em.getTransaction().begin();
            
            for(Airroute ar : list){
                em.persist(ar);
                em.flush();
                arList.add(ar);
            }
            
            em.getTransaction().commit();
       } catch(Exception e){
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        
    }
    
    //Removes all entities inserted during tests 
    private static void cleanupDataBase() {
        System.out.println("cleanupDataBase");
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            System.out.println("Removing amount of FlightPrices: " + fpList.size());
            for(FlightPrices obj : fpList){
                FlightPrices doRemove = em.find(FlightPrices.class, obj.getId());
                em.remove(doRemove);
                System.out.println("FlightPrice removed: " + obj.getId());
            }
            
            System.out.println("Removing amount of Airroutes: " + arList.size());
            for(Airroute obj :  arList){
                System.out.println("FLIGHTID: " + obj.getFlightID());
                Airroute doRemove = em.find(Airroute.class, obj.getFlightID());
                em.remove(doRemove);
                System.out.println("Airroute removed: " + obj.getFlightID());
            }

            transaction.commit();
        } catch (Exception e) {
            System.out.println("cleanupDataBase failed due to: " + e.getCause() + e.getLocalizedMessage());
            transaction.rollback();
        } finally {
            em.close();
        }
    }
    
}
