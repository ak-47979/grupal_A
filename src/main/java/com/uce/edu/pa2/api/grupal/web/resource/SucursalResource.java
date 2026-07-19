package com.uce.edu.pa2.api.grupal.web.resource;

import java.util.List;
import com.uce.edu.pa2.api.grupal.applicatioin.service.SucursalService;
import com.uce.edu.pa2.api.grupal.domain.model.Sucursal;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sucursal")
public class SucursalResource {
 @Inject
    private SucursalService ss;

    // http://localhost:8081/sucursal/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarTodos() {
        return this.ss.buscarTodos();
    }

    // http://localhost:8081/sucursal/buscar/{id}
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Sucursal buscaSucursalId(@PathParam("id") Integer id) {
        return this.ss.buscaSucursalId(id);
    }
    // http://localhost:8081/sucursal/guardar
    @Path("/guardar")
    @POST
    public void crearSucursal(Sucursal sucursal) {
        this.ss.crearSucursal(sucursal);
    }
    // http://localhost:8081/sucursal/actualizar/{id}
    @Path("/actualizar/{id}")
    @PUT
    public void actualizarSucursal(Sucursal sucursal, @PathParam("id") Integer id) {
        this.ss.actualizarSucursal(sucursal, id);
    }
      // http://localhost:8081/sucursal/eliminar/{id}
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarSucursalId(@PathParam("id") Integer id) {
        this.ss.eliminarSucursalId(id);
        ;
    }

}
