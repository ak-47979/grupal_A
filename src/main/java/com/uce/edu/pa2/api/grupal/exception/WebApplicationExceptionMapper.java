package com.uce.edu.pa2.api.grupal.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    @Override
    public Response toResponse(WebApplicationException exception) {
        // Captura el mensaje que pusiste en el throw new ...
        String mensaje = exception.getMessage(); 

        // Crea un JSON estructurado para la respuesta
        Map<String, String> errorResponse = Map.of("error", mensaje);

        return Response.status(exception.getResponse().getStatus())
                .type(MediaType.APPLICATION_JSON)
                .entity(errorResponse)
                .build();
    }
}