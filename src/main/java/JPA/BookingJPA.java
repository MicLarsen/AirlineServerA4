package JPA;

import Entities.Airroute;
import Entities.BookingOrder;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author nickl
 */
public class BookingJPA {

    private JPAUtils utils;

    public BookingJPA() {
        utils = new JPAUtils();
    }

    public BookingOrder persistEntity(BookingOrder bo, String flightId) {
        EntityManager em = utils.getEntityManager();

        try {
            em.getTransaction().begin();
            
            Airroute ar = em.find(Airroute.class, flightId);
            bo.setFlightId(ar);
            em.persist(bo);
            em.flush();
            em.getTransaction().commit();
            
            return bo;
        } catch (Exception e) {
            System.out.println("BookingJPA failed due to: " + e.toString());
            em.getTransaction().rollback();
            return null;
        }
    }
    
    public BookingOrder retrieveBookingOrder(String flightId, String reserveeEmail){
        EntityManager em = utils.getEntityManager();
        BookingOrder bo;
        try{
            em.getTransaction().begin();
            
            Airroute ar = em.find(Airroute.class, flightId);
            
            Query q = em.createQuery("SELECT b FROM BookingOrder b WHERE b.flightId=:flightId AND b.reserveeEmail=:email", BookingOrder.class);
            q.setParameter("flightId", ar);
            q.setParameter("email", reserveeEmail);            
            em.getTransaction().commit();
            bo = (BookingOrder) q.getSingleResult();
            
            return bo;
        }catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }
    }
    
    public BookingOrder updateBookingOrder(BookingOrder bo){
        EntityManager em = utils.getEntityManager();
        
        try{
            em.getTransaction().begin();
            em.merge(bo);
            em.flush();
            em.getTransaction().commit();
            
            return bo;
        } catch(Exception e){
            em.getTransaction().rollback();
            return null;
        }
    }
    
    public boolean deleteBookingOrder(BookingOrder bo){
        EntityManager em = utils.getEntityManager();
        
        try{
            em.getTransaction().begin();
            
            bo = em.find(BookingOrder.class, bo.getId());
            
            em.remove(bo);
            
            em.getTransaction().commit();
            return true;
        } catch(Exception e){
            System.out.println("DeleteBookingOrder failed due to: " + e);
            em.getTransaction().rollback();
            return false;
        }
    }

}
