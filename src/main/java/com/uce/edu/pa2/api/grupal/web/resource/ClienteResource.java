package com.uce.edu.pa2.api.grupal.web.resource;

import java.util.List;

import com.uce.edu.pa2.api.grupal.applicatioin.service.ClienteService;
import com.uce.edu.pa2.api.grupal.domain.model.Cliente;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
//decirle al navegador (o a cualquier cliente que haga la petición)
//exactamente qué tipo de formato le estás enviando desde tu servidor REST
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/cliente")
public class ClienteResource {

    @Inject
    private ClienteService cs;

    // http://localhost:8081/cliente/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> buscarTodos() {
        return this.cs.buscarTodos();
    }

    // http://localhost:8081/cliente/buscar/{id}
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente buscaClienteId(@PathParam("id") String cliente) {
        return this.cs.buscaClienteId(cliente);
    }
    // http://localhost:8081/cliente/guardar
    @Path("/guardar")
    @POST
    public void crearCliente(Cliente cliente) {
        this.cs.crearCliente(cliente);
    }
    // http://localhost:8081/cliente/actualizar/{id}
    @Path("/actualizar/{id}")
    @PUT
    public void actualizarCliente(Cliente cliente, @PathParam("id") String cedula) {
        this.cs.actualizarCliente(cliente, cedula);
    }
      // http://localhost:8081/cliente/eliminar/{id}
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarClienteId(@PathParam("id") String cedula) {
        this.cs.eliminarClienteId(cedula);
        ;
    }

}