
import Entity.FlightPrices;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class populateAirports {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tempPU");
        EntityManager em = emf.createEntityManager();

        List<FlightPrices> list = new ArrayList();

        //cph depatures
        list.add(new FlightPrices("CPH", "LHR", 20.00));
        list.add(new FlightPrices("CPH", "BCN", 30.00));
        list.add(new FlightPrices("CPH", "JFK", 60.00));
        list.add(new FlightPrices("CPH", "ATL", 60.00));
        list.add(new FlightPrices("CPH", "AMS", 40.00));
        list.add(new FlightPrices("CPH", "OSL", 10.00));
        list.add(new FlightPrices("CPH", "TXL", 30.00));
        list.add(new FlightPrices("CPH", "MOW", 500.00));
        //LHR depatures
        list.add(new FlightPrices("LHR", "CPH", 20.00));
        list.add(new FlightPrices("LHR", "BCN", 30.00));
        list.add(new FlightPrices("LHR", "JFK", 60.00));
        list.add(new FlightPrices("LHR", "ATL", 60.00));
        list.add(new FlightPrices("LHR", "AMS", 40.00));
        list.add(new FlightPrices("LHR", "OSL", 40.00));
        list.add(new FlightPrices("LHR", "TXL", 620.00));
        list.add(new FlightPrices("LHR", "MOW", 500.00));
        //BCN depatures
        list.add(new FlightPrices("BCN", "CPH", 20.00));
        list.add(new FlightPrices("BCN", "LHR", 30.00));
        list.add(new FlightPrices("BCN", "JFK", 60.00));
        list.add(new FlightPrices("BCN", "ATL", 60.00));
        list.add(new FlightPrices("BCN", "AMS", 40.00));
        list.add(new FlightPrices("BCN", "OSL", 40.00));
        list.add(new FlightPrices("BCN", "TXL", 620.00));
        list.add(new FlightPrices("BCN", "MOW", 500.00));
        //JFK depatures
        list.add(new FlightPrices("JFK", "CPH", 20.00));
        list.add(new FlightPrices("JFK", "LHR", 30.00));
        list.add(new FlightPrices("JFK", "BCN", 60.00));
        list.add(new FlightPrices("JFK", "ATL", 60.00));
        list.add(new FlightPrices("JFK", "AMS", 40.00));
        list.add(new FlightPrices("JFK", "OSL", 40.00));
        list.add(new FlightPrices("JFK", "TXL", 620.00));
        list.add(new FlightPrices("JFK", "MOW", 500.00));
        //ATL depatuers
        list.add(new FlightPrices("ATL", "CPH", 20.00));
        list.add(new FlightPrices("ATL", "LHR", 30.00));
        list.add(new FlightPrices("ATL", "BCN", 60.00));
        list.add(new FlightPrices("ATL", "JFK", 60.00));
        list.add(new FlightPrices("ATL", "AMS", 40.00));
        list.add(new FlightPrices("ATL", "OSL", 40.00));
        list.add(new FlightPrices("ATL", "TXL", 620.00));
        list.add(new FlightPrices("ATL", "MOW", 500.00));
        //AMS depatures
        list.add(new FlightPrices("AMS", "CPH", 20.00));
        list.add(new FlightPrices("AMS", "LHR", 30.00));
        list.add(new FlightPrices("AMS", "BCN", 60.00));
        list.add(new FlightPrices("AMS", "JFK", 60.00));
        list.add(new FlightPrices("AMS", "ATL", 40.00));
        list.add(new FlightPrices("AMS", "OSL", 40.00));
        list.add(new FlightPrices("AMS", "TXL", 620.00));
        list.add(new FlightPrices("AMS", "MOW", 500.00));
        //OSL depatures
        list.add(new FlightPrices("OSL", "CPH", 20.00));
        list.add(new FlightPrices("OSL", "LHR", 30.00));
        list.add(new FlightPrices("OSL", "BCN", 60.00));
        list.add(new FlightPrices("OSL", "JFK", 60.00));
        list.add(new FlightPrices("OSL", "ATL", 40.00));
        list.add(new FlightPrices("OSL", "AMS", 40.00));
        list.add(new FlightPrices("OSL", "TXL", 620.00));
        list.add(new FlightPrices("OSL", "MOW", 500.00));
        //TXL depatures
        list.add(new FlightPrices("TXL", "CPH", 20.00));
        list.add(new FlightPrices("TXL", "LHR", 30.00));
        list.add(new FlightPrices("TXL", "BCN", 60.00));
        list.add(new FlightPrices("TXL", "JFK", 60.00));
        list.add(new FlightPrices("TXL", "ATL", 40.00));
        list.add(new FlightPrices("TXL", "AMS", 40.00));
        list.add(new FlightPrices("TXL", "OSL", 620.00));
        list.add(new FlightPrices("TXL", "MOW", 500.00));
        //MOW depatures
        list.add(new FlightPrices("MOW", "CPH", 20.00));
        list.add(new FlightPrices("MOW", "LHR", 30.00));
        list.add(new FlightPrices("MOW", "BCN", 60.00));
        list.add(new FlightPrices("MOW", "JFK", 60.00));
        list.add(new FlightPrices("MOW", "ATL", 40.00));
        list.add(new FlightPrices("MOW", "AMS", 40.00));
        list.add(new FlightPrices("MOW", "OSL", 620.00));
        list.add(new FlightPrices("MOW", "TXL", 500.00));

        
        //Persist calls!
        try {
            em.getTransaction().begin();
            for (FlightPrices fp : list) {
                em.persist(fp);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
