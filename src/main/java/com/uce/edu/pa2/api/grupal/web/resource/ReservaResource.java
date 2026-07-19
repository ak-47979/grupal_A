package com.uce.edu.pa2.api.grupal.web.resource;

import java.util.List;


import com.uce.edu.pa2.api.grupal.applicatioin.service.ReservaService;
import com.uce.edu.pa2.api.grupal.domain.model.Reserva;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/reserva")
public class ReservaResource {
 @Inject
    private ReservaService rs;

    // http://localhost:8081/reserva/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reserva> buscarTodos() {
        return this.rs.buscarTodos();
    }

    // http://localhost:8081/reserva/buscar/{id}
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva buscaReservaId(@PathParam("id") Integer reserva) {
        return this.rs.buscaReservaId(reserva);
    }
    // http://localhost:8081/reserva/guardar
    @Path("/guardar")
    @POST
    public void crearReserva(Reserva cliente) {
        this.rs.crearReserva(cliente);
    }
    // http://localhost:8081/reserva/actualizar/{id}
    @Path("/actualizar/{id}")
    @PUT
    public void actualizarReserva(Reserva cliente, @PathParam("id") Integer id) {
        this.rs.actualizarReserva(cliente, id);
    }
      // http://localhost:8081/reserva/eliminar/{id}
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarReservaId(@PathParam("id") Integer id) {
        this.rs.eliminarReservaId(id);
        ;
    }

}
