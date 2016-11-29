package JPA;

import Entities.Airroute;
import Entities.FlightPrices;
import Interfaces.RestInterface;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author nickl
 */
public class FlightJPA implements RestInterface {

    JPAUtils utils = new JPAUtils();

    public FlightPrices persistFlightPrices(FlightPrices fp) {
        FlightPrices returnObj = fp;

        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.persist(returnObj);
        em.flush();
        transaction.commit();

        return returnObj;
    }
    
    public Airroute persistAirroute(Airroute ar){
        Airroute returnObj = ar;
        
        EntityManager em = utils.getEntityManager();
        
        em.getTransaction().begin();
        
        em.persist(returnObj);
        em.flush();
        
        em.getTransaction().commit();
        
        return returnObj;
    }

    @Override
    public List<Airroute> getFlightsByOrigin(String origin, String date, String tickets) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Airroute> getFlightsByOriginDest(String origin, String destination, String date, String tickets) {
        EntityManager em = utils.getEntityManager();
        List<Airroute> list = new ArrayList();
            EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Query q = em.createQuery("SELECT a FROM Airroute a WHERE a.origin=:origin AND a.destination=:dest AND a.date=:date", Airroute.class);
            q.setParameter("origin", origin);
            q.setParameter("dest", destination);
            q.setParameter("date", date);
            
            transaction.commit();
            
            for(Object obj : q.getResultList()){
                list.add((Airroute) obj);
            }
            
            return list;
        } catch(Exception e ){
            transaction.rollback();
            System.out.println("Something went wrong in getFlightsByOriginDest: " + e.getCause());
            return null;
        } finally {
            em.close();
        }
    }
    
    
    /** calculate total prices!
     * q = em.createQuery("SELECT f FROM FlightPrices f WHERE f.origin=:origin AND f.destination=:dest", FlightPrices.class);
            q.setParameter("origin", origin);
            q.setParameter("dest", destination);
            
            FlightPrices fp = (FlightPrices) q.getSingleResult();
            
            for(Airroute ar : list){
                ar.calculateTotalPrice(fp.getPrice(), tickets);
            }
            
     */
}
