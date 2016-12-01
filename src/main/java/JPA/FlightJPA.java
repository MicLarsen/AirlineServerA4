package JPA;

import Entities.Airport;
import Entities.Airroute;
import Entities.FlightPrices;
import Interfaces.RestInterface;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

    public Airroute persistAirroute(Airroute ar) throws SQLIntegrityConstraintViolationException {
        Airroute returnObj = ar;

        EntityManager em = utils.getEntityManager();
        if (em.find(Airroute.class, ar.getFlightID()) == null) {

            em.getTransaction().begin();

            em.persist(returnObj);
            em.flush();

            em.getTransaction().commit();

            return returnObj;
        } else {
            throw new SQLIntegrityConstraintViolationException("duplicate entry for primary key");
        }
    }

    @Override
    public List<Airroute> getFlightsByOrigin(String origin, Date date, String tickets) {
        EntityManager em = utils.getEntityManager();
        List<Airroute> list = new ArrayList();
        EntityTransaction transaction = em.getTransaction();

        int dayInMilli = 86400000;

        try {
            transaction.begin();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date d = sdf.parse(sdf.format(date));

            Date nextDay = new Date(date.getTime() + dayInMilli);
            Date d2 = sdf.parse(sdf.format(nextDay));

            Airport originAirport = em.getReference(Airport.class, origin);

            Query q = em.createQuery("Select a FROM Airroute a WHERE a.origin=:origin AND a.date>:date AND a.date<:nextDate", Airroute.class);
            q.setParameter("origin", originAirport);
            q.setParameter("date", d);
            q.setParameter("nextDate", d2);
//        q.setParameter("tickets", tickets);
            System.out.println(q.getResultList().size());
            for (Object obj : q.getResultList()) {
                list.add((Airroute) obj);
            }

            transaction.commit();
            return list;
        } catch (Exception e) {
            System.out.println("FAILED DUE TO: " + e.toString());
            transaction.rollback();
            return null;
        } finally {
            em.close();
        }
    }

    //Refactoring to use airport class!!!
    @Override
    public List<Airroute> getFlightsByOriginDest(String origin, String destination, Date date, String tickets) {
        EntityManager em = utils.getEntityManager();
        List<Airroute> list = new ArrayList();
        EntityTransaction transaction = em.getTransaction();
        
        int dayInMilli = 86400000;
        
        try {
            transaction.begin();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date d = sdf.parse(sdf.format(date));

            Date nextDay = new Date(date.getTime() + dayInMilli);
            Date d2 = sdf.parse(sdf.format(nextDay));

            Airport originAirport = em.getReference(Airport.class, origin);
            Airport destinationAirport = em.getReference(Airport.class, destination);

            Query q = em.createQuery("SELECT a FROM Airroute a WHERE a.origin=:origin AND a.destination=:dest AND a.date>:date AND a.date<:nextDate", Airroute.class);
            q.setParameter("origin", originAirport);
            q.setParameter("dest", destinationAirport);
            q.setParameter("date", d);
            q.setParameter("nextDate", d2);

            transaction.commit();

            for (Object obj : q.getResultList()) {
                list.add((Airroute) obj);
            }

            return list;
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Something went wrong in getFlightsByOriginDest: " + e.getCause());
            return null;
        } finally {
            em.close();
        }
    }

    public boolean deleteFlights(Airroute ar) {
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(ar);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public boolean deleteFlightPrices(FlightPrices fp) {
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(fp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
    }

    public Airroute updateFlight(Airroute ar) {
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(ar);
            em.flush();
            transaction.commit();
            return ar;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    public FlightPrices updateFlightPrices(FlightPrices fp) {
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(fp);
            em.flush();
            transaction.commit();
            return fp;
        } catch (Exception e) {
            transaction.rollback();
            return null;
        }
    }

    public double calculateTotalPrice(String origin, String destination, String tickets) {
        EntityManager em = utils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        int ticketsInt = Integer.parseInt(tickets);

        transaction.begin();
        Query q = em.createQuery("SELECT f FROM FlightPrices f WHERE f.origin=:origin AND f.destination=:dest", FlightPrices.class);
        q.setParameter("origin", origin);
        q.setParameter("dest", destination);
        transaction.commit();

        FlightPrices fp = (FlightPrices) q.getSingleResult();

        return fp.getPrice() * ticketsInt;
    }
}
