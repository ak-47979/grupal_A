package com.uce.edu.pa2.api.grupal.web.resource;

import java.util.List;
import com.uce.edu.pa2.api.grupal.applicatioin.service.VehiculoService;
import com.uce.edu.pa2.api.grupal.domain.model.Vehiculo;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/vehiculo")
public class VehiculoResorce {
 @Inject
    private VehiculoService vs;

    // http://localhost:8081/vehiculo/todos
    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vehiculo> buscarTodos() {
        return this.vs.buscarTodos();
    }

    // http://localhost:8081/vehiculo/buscar/{id}
    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vehiculo buscaAutoId(@PathParam("id") String placa) {
        return this.vs.buscaAutoId(placa);
    }
    // http://localhost:8081/vehiculo/guardar
    @Path("/guardar")
    @POST
    public void crearAuto(Vehiculo vehiculo) {
        this.vs.crearAuto(vehiculo);
    }
    // http://localhost:8081/vehiculo/actualizar/{id}
    @Path("/actualizar/{id}")
    @PUT
    public void actualizarAuto(Vehiculo vehiculo, @PathParam("id") String placa) {
        this.vs.actualizarAuto(vehiculo, placa);
    }
      // http://localhost:8081/vehiculo/eliminar/{id}
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminarAutoId(@PathParam("id") String auto) {
        this.vs.eliminarAutoId(auto);
        ;
    }

}
