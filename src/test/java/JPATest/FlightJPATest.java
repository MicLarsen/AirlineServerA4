package JPATest;

import Entities.FlightPrices;
import JPA.FlightJPA;
import JPA.JPAUtils;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
    
    private JPAUtils jpau;
    private static List<FlightPrices> list;
    
    public FlightJPATest() {
        list = new ArrayList();
        jpau = new JPAUtils();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        cleanupDatabase();
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        FlightPrices fp = new FlightPrices("test", "test", 20.00);
        FlightJPA fjpa = new FlightJPA();
        FlightPrices objPersisted = fjpa.persistEntity(fp);
        list.add(fp);
        
        assertEquals(objPersisted.getClass(), em.find(FlightPrices.class, objPersisted.getId()).getClass());   
    }
    
    private static void cleanupDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AirlinePU");
        EntityManager em = emf.createEntityManager();
        
        for (FlightPrices fp : list) {
            System.out.println(fp.getId());
            FlightPrices toRemove = em.find(FlightPrices.class, fp.getId());
            em.remove(toRemove);
        }
    }
    
}
