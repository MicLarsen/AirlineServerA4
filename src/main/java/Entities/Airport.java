package Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 *
 * @author nickl
 */
@Entity
public class Airport {
    
    @Id
    private String IATACode;
    
    private String airportName;
    
    public Airport(){}
    
    public Airport(String IATACode, String airportName){
        this.IATACode = IATACode;
        this.airportName = airportName;
    }

    public String getFullNameAndIATA(){
        return airportName + "(" + IATACode + ")";
    }
    
    public String getIATACode() {
        return IATACode;
    }

    public void setIATACode(String IATACode) {
        this.IATACode = IATACode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }
}
