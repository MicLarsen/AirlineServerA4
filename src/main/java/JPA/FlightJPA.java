package JPA;

import Entities.Airroute;
import Entities.FlightPrices;
import Interfaces.RestInterface;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author nickl
 */
public class FlightJPA implements RestInterface{

    JPAUtils utils = new JPAUtils();
    
    
    public FlightPrices persistEntity(FlightPrices fp) {
        FlightPrices returnObj = fp;
        
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        transaction.begin();
        em.persist(returnObj);
        em.flush();
        transaction.commit();
        
        return returnObj;
    }

    @Override
    public List<Airroute> getFlightsByOrigin(String origin, String date, String tickets) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Airroute> getFlightsByOriginDest(String origin, String destination, String date, String tickets) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
