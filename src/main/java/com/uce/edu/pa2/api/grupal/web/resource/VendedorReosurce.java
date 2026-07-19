package com.uce.edu.pa2.api.grupal.web.resource;

import java.util.List;
import com.uce.edu.pa2.api.grupal.applicatioin.service.VendedorService;
import com.uce.edu.pa2.api.grupal.domain.model.Vendedor;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/vendedor")
public class VendedorReosurce {
    @Inject
    private VendedorService vs;

    // http://localhost:8081/vendedor/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vendedor> buscarTodos() {
        return this.vs.buscarTodos();
    }

    // http://localhost:8081/vendedor/buscar/{id}
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vendedor buscaVendedorId(@PathParam("id") String vendedor) {
        return this.vs.buscaVendedorId(vendedor);
    }

    // http://localhost:8081/vendedor/guardar
    @Path("/guardar")
    @POST
    public void crearVendedor(Vendedor vendedor) {
        this.vs.crearVendedor(vendedor);
    }

    // http://localhost:8081/vendedor/actualizar/{id}
    @Path("/actualizar/{id}")
    @PUT
    public void actualizarVendedor(Vendedor vendedor, @PathParam("id") String cedula) {
        this.vs.actualizarVendedor(vendedor, cedula);
    }

    // http://localhost:8081/vendedor/eliminar/{id}
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarVendedorId(@PathParam("id") String cedula) {
        this.vs.eliminarVendedorId(cedula);
        ;
        ;
    }
}
