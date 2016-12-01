package Entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author nickl
 */
@Entity
public class BookingOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<passengers> passengers;
    private String reserveeName, reserveePhone;
    
    @ManyToOne
    private Airroute flightId;
    
    @Column(unique = true)
    private String reserveeEmail;
    
    private int numberOfSeats;
    
    public BookingOrder(){
        
    } 
    
    public BookingOrder(Airroute flightId, int numberOfSeats, String reserveeName, String reserveePhone, String reserveeEmail, List<passengers> passengers){
        this.flightId = flightId;
        this.numberOfSeats = numberOfSeats;
        this.reserveeName = reserveeName;
        this.reserveePhone = reserveePhone;
        this.reserveeEmail = reserveeEmail;
        this.passengers = passengers;
    }
    
    public List<passengers> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<passengers> passengers) {
        this.passengers = passengers;
    }

    public String getReserveeName() {
        return reserveeName;
    }

    public void setReserveeName(String reserveeName) {
        this.reserveeName = reserveeName;
    }

    public String getReserveePhone() {
        return reserveePhone;
    }

    public void setReserveePhone(String reserveePhone) {
        this.reserveePhone = reserveePhone;
    }

    public Airroute getFlightId() {
        return flightId;
    }

    public void setFlightId(Airroute flightId) {
        this.flightId = flightId;
    }

    public String getReserveeEmail() {
        return reserveeEmail;
    }

    public void setReserveeEmail(String reserveeEmail) {
        this.reserveeEmail = reserveeEmail;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }
}
