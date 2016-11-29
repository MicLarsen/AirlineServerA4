package JPA;

import Entities.FlightPrices;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author nickl
 */
public class FlightJPA {

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

    
}
