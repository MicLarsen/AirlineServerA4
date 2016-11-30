package JPA;

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

    public BookingOrder persistEntity(BookingOrder bo) {
        EntityManager em = utils.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(bo);
            em.flush();
            em.getTransaction().commit();
            
            return bo;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        }
    }
    
    public BookingOrder retrieveBookingOrder(String flightId, String reserveeEmail){
        EntityManager em = utils.getEntityManager();
        BookingOrder bo;
        try{
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT b FROM BookingOrder b WHERE b.flightId=:flightId AND b.reserveeEmail=:email", BookingOrder.class);
            q.setParameter("flightId", flightId);
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
            
            em.remove(bo);
            
            em.getTransaction().commit();
            return true;
        } catch(Exception e){
            em.getTransaction().rollback();
            return false;
        }
    }

}