/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.BookingOrder;
import Entities.passengers;
import JPA.BookingJPA;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author nickl
 */
@Path("reservation")
public class BookingResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of BookingResource
     */
    public BookingResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.BookingResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of BookingResource
     * @param content representation for the resource
     * @return 
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{flightId}")
    public String putJson(String content, @PathParam("flightId") String flightId) {
        Gson gson = new GsonBuilder().create();
        BookingOrder bo = gson.fromJson(content, BookingOrder.class);
        
        BookingJPA bjpa = new BookingJPA();
        bo = bjpa.persistEntity(bo, flightId);
        
        
        System.out.println(bo.getFlightId().getFlightID());
        JSONObject main = new JSONObject();
        
        main.put("flightNumber", bo.getFlightId().getFlightNumber());
        main.put("origin", bo.getFlightId().getOrigin().getFullNameAndIATA());
        main.put("destination", bo.getFlightId().getDestination().getFullNameAndIATA());
        main.put("flightTime", bo.getFlightId().getTraveltime());
        main.put("numberOfSeats", bo.getNumberOfSeats());
        main.put("reserveeName", bo.getReserveeName());
        
        JSONArray passengerList = new JSONArray();
        for(passengers p : bo.getPassengers()){
            JSONObject obj = new JSONObject();
            
            obj.put("firstName", p.getFirstName());
            obj.put("lastName", p.getLastName());
            
            passengerList.add(obj);    
        }
        
        main.put("passengers", passengerList);
        
        bjpa.deleteBookingOrder(bo);

        return main.toString();
        
    }
}
