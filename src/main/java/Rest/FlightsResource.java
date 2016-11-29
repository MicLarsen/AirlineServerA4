/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Interfaces.RestInterface;
import java.util.ArrayList;
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
        RestInterface restint = new RestInterface() {};
        JSONObject main = new JSONObject();
        main.put("airline", "gruppe4");
        JSONArray results = new JSONArray();
        ArrayList arr = restint.getFlights(from,date,tickets);
        
        for (Object res : arr) {
            JSONObject obj = new JSONObject();
            obj.put("flightID",res.getFlightID());
            
            results.add(obj);
        }
        main.put("flights",results);
        throw new UnsupportedOperationException();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{from}/{to}/{date}/{tickets}")
    public String getFlight(@PathParam("from") String from,@PathParam("to") String to, @PathParam("date") String date, @PathParam("tickets") String ticket){
        throw new UnsupportedOperationException();
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
