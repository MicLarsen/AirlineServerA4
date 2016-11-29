package JPATest;

import Entities.Airroute;
import Entities.FlightPrices;
import JPA.FlightJPA;
import JPA.JPAUtils;
import java.util.ArrayList;
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

/**
 *
 * @author nickl
 */
public class FlightJPATest {
    
    private static JPAUtils jpau;
    private static List<FlightPrices> fpList;
    private static List<Airroute> arList;
    private static EntityManagerFactory emf;
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
        
        EntityManager em = jpau.getEntityManager();
        
        assertNotNull(em);
    }
    
    @Test //TFD!
    public void FlightPriceEntityPersistToDatabaseTest(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        FlightPrices fp = new FlightPrices("test", "test", 20.00);
        FlightJPA fjpa = new FlightJPA();
        FlightPrices objPersisted = fjpa.persistFlightPrices(fp);
        fpList.add(fp);
        
        assertEquals(objPersisted.getClass(), em.find(FlightPrices.class, objPersisted.getId()).getClass());   
    }
    
    @Test
    public void AirrouteEntityPersistToDatabase(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        Airroute ar = new Airroute("testAirline", "12345679876543", "767687909087656789", "test date", 10983, 123098, "CPH", "JFK");
        
        FlightJPA fjpa = new FlightJPA();
        Airroute objPersisted = fjpa.persistAirroute(ar);
        arList.add(objPersisted);
        assertEquals(objPersisted.getClass(), em.find(Airroute.class, objPersisted.getFlightID()).getClass());
        
    }
    
    @Test
    public void getFlightsByOriginAndDestination(){
        
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
        
    }
    
    private void insertDummyFlights(){
        List<Airroute> list = new ArrayList();
        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        
        list.add(new Airroute("testAirline1", "1234", "1726381723", "testDate", 32, 404, "CPH", "ATL"));
        list.add(new Airroute("testAirline2", "2234", "1726381723", "testDate", 32, 404, "CPH", "ATL"));
        list.add(new Airroute("testAirline3", "3234", "1726381723", "testDate", 32, 404, "CPH", "ATL"));
        list.add(new Airroute("testAirline4", "4234", "1726381723", "testDate", 32, 404, "JFK", "ATL"));
        
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
