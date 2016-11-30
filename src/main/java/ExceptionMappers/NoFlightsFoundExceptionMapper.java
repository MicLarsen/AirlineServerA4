/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExceptionMappers;

import Exceptions.NoFlightsFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author nicolaicornelis
 */
@Provider
public class NoFlightsFoundExceptionMapper implements ExceptionMapper<NoFlightsFoundException> {

    @Override
    public Response toResponse(NoFlightsFoundException exception) {
        return Response.status(Status.NOT_FOUND).entity(exception.getJSON()).build();
    }

}
