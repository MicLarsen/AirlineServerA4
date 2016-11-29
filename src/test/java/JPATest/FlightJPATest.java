package JPATest;

import Entity.FlightPrices;
import JPA.FlightJPA;
import JPA.JPAUtils;
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
    
    JPAUtils jpau;
    
    public FlightJPATest() {
        jpau = new JPAUtils();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tempPU");
        EntityManager em = emf.createEntityManager();
        FlightPrices fp = new FlightPrices("test", "test", 20.00);
        
        FlightJPA fjpa = new FlightJPA();
        FlightPrices objPersisted = fjpa.persistEntity(fp);
        
        assertEquals(objPersisted.getClass(), em.find(FlightPrices.class, objPersisted.getId()).getClass());   
    }
    
}
