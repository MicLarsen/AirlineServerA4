/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Entities.Airroute;
import Interfaces.RestInterface;
import JPA.FlightJPA;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author Joakim
 */
@Path("flights")
public class FlightsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FlightsResource
     */
    public FlightsResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.FlightsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{from}/{date}/{tickets}")
    public String getJson(@PathParam("from") String from, @PathParam("date") String date, @PathParam("tickets") String ticket) {
        RestInterface fjpa = new FlightJPA();
        JSONObject main = new JSONObject();
        main.put("airline", "gruppe4");
        JSONArray results = new JSONArray();
        List<Airroute> arr = fjpa.getFlightsByOrigin(from, date, ticket);
        
        for (Airroute res : arr) {
            JSONObject obj = new JSONObject();
            obj.put("flightID",res.getFlightID());
            obj.put("flightNumber", res.getFlightNumber());
            obj.put("date", res.getDate());
            obj.put("numberOfSeats", res.getNumberOfSeats());
//            obj.put("totalPrice", res.getTotalPrice());
            obj.put("travelTime", res.getTraveltime());
            obj.put("origin", res.getOrigin());
            obj.put("destination", res.getDestination());
            
            results.add(obj);
        }
        main.put("flights",results);
        
        return main.toString();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{from}/{to}/{date}/{tickets}")
    public String getFlight(@PathParam("from") String from,@PathParam("to") String to, @PathParam("date") String date, @PathParam("tickets") String ticket){
        RestInterface fjpa = new FlightJPA();
        JSONObject main = new JSONObject();
        main.put("airline", "gruppe4");
        JSONArray results = new JSONArray();
        List<Airroute> arr = fjpa.getFlightsByOriginDest(from, to, date, ticket);
        
        for (Airroute res : arr) {
            JSONObject obj = new JSONObject();
            obj.put("flightID",res.getFlightID());
            obj.put("flightNumber", res.getFlightNumber());
            obj.put("date", res.getDate());
            obj.put("numberOfSeats", res.getNumberOfSeats());
//            obj.put("totalPrice", res.getTotalPrice());
            obj.put("travelTime", res.getTraveltime());
            obj.put("origin", res.getOrigin());
            obj.put("destination", res.getDestination());
            
            results.add(obj);
        }
        main.put("flights",results);
        
        return main.toString();
    }

    /**
     * PUT method for updating or creating an instance of FlightsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
