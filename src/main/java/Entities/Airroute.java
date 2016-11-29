package Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Michael
 *
 * "airline": "AngularJS Airline", "flights": [ { "flightID":
 * "2257-1457179200000", "flightNumber": "COL2257", "date":
 * "2016-03-05T13:00:00.000Z", "numberOfSeats": 3, "totalPrice": 180,
 * "traveltime": 120, "origin": "CDG", "destination": "CPH", } ] }
 *
 */
@Entity
public class Airroute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String airline;
    private String flightID, flightNumber;
    private Date date;
    private double numberOfSeats, traveltime;
    private String origin, destination;

    public Airroute(String airline, String flightID, String flightNumber, Date date, double numberOfSeats, double traveltime, String origin, String destination) {
        this.airline = "AirlineG4A";
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
        this.traveltime = traveltime;
        this.origin = origin;
        this.destination = destination;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(double numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(double traveltime) {
        this.traveltime = traveltime;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
