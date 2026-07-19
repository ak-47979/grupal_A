package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Sucursal;
import com.uce.edu.pa2.api.grupal.domain.model.Vehiculo;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.SucursalRepositoryImpl;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.VehiculoRepositoryImpl;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl ri;

    @Inject
    private SucursalRepositoryImpl sucursalRepositoryImpl;
    public void crearAuto(Vehiculo auto) {
        this.ri.persist(auto);
    }
    @Blocking
    public void actualizarAuto(Vehiculo auto, String placa) {
    // Validar si el vehículo realmente existe en la BD
    Vehiculo base = this.buscaAutoId(placa);
    if (base == null) {
        throw new jakarta.ws.rs.NotFoundException("El vehículo con placa " + placa + " no existe.");
    }
    // Actualizar los campos propios del vehículo
    base.setMarca(auto.getMarca());
    base.setModelo(auto.getModelo());

    // RELACIÓN MANYTOONE de Sucursal
    if (auto.getSucursal() != null && auto.getSucursal().getId() != null) {
        // Buscamos la sucursal real en la BD 
        Sucursal sucursalBD = sucursalRepositoryImpl.findById(auto.getSucursal().getId());
        if (sucursalBD == null) {
            throw new jakarta.ws.rs.BadRequestException("No existe la sucursal especificada.");
        }
        base.setSucursal(sucursalBD);
    } else {
        // significa que el cliente quiere desvincular el auto de la sucursal actual.
        base.setSucursal(null); 
    }
}

    public Vehiculo buscaAutoId(String placa) {
        return this.ri.findById(placa);
    }

    public void eliminarAutoId(String placa) {
        this.ri.deleteById(placa);
    }

    public List<Vehiculo> buscarTodos() {
        return this.ri.findAll().list();
    }
}