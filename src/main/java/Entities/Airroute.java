package Entities;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
    private String flightID;

    private String airline;
    private String flightNumber;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int numberOfSeats, traveltime;
    
    @ManyToOne
    private Airport origin;
    
    @ManyToOne
    private Airport destination;
    
    
    @Transient
    private double totalPrice;

    public Airroute(){
    }
    
    public Airroute(String flightID, String flightNumber, Date date, int numberOfSeats, int traveltime, Airport origin, Airport destination) {
        this.airline = "AirlineG4A";
        this.flightID = flightID;
        this.flightNumber = flightNumber;
        this.date = date;
        this.numberOfSeats = numberOfSeats;
        this.traveltime = traveltime;
        this.origin = origin;
        this.destination = destination;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public void calculateTotalPrice(String tickets){
        double price = (this.totalPrice * Integer.parseInt(tickets));
        this.totalPrice = price;
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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(int traveltime) {
        this.traveltime = traveltime;
    }

    public Airport getOrigin() {
        return origin;
    }

    public void setOrigin(Airport origin) {
        this.origin = origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }
}
