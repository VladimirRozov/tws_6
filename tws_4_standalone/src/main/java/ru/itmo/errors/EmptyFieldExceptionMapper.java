/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.itmo.errors;

/**
 *
 * @author rozov
 */
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Provider
public class EmptyFieldExceptionMapper implements ExceptionMapper<EmptyFieldException> {
    @Override
    public Response toResponse(EmptyFieldException ex) {
        return Response.status(Status.BAD_REQUEST).entity(ex.getMessage()).build();
    }
}