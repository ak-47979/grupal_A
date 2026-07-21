package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;
import com.uce.edu.pa2.api.grupal.domain.model.Sucursal;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.SucursalRepositoryImpl;

import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class SucursalService {

    @Inject
    private SucursalRepositoryImpl ri;

    // Cambié el nombre del parámetro a "sucursal" para que sea más claro que
    // "servicio"
    public void crearSucursal(Sucursal sucursal) {
        // Validar datos nulos antes de persistir
        if (sucursal == null || sucursal.getNombre() == null || sucursal.getCiudad() == null) {
            throw new jakarta.ws.rs.BadRequestException("Error: El nombre y la ciudad son obligatorios.");
        }
        this.ri.persist(sucursal);
    }

    public void actualizarSucursal(Sucursal sucursal, Integer id) {
        // Validar si la sucursal existe en la BD
        Sucursal base = this.buscaSucursalId(id);
        if (base == null) {
            throw new jakarta.ws.rs.NotFoundException("La sucursal con ID " + id + " no existe.");
        }

        // Actualizar solo los campos propios de la sucursal
        base.setNombre(sucursal.getNombre());
        base.setCiudad(sucursal.getCiudad());
        // Se gestiona desde vehivulo las sucursales
        
    }

    public Sucursal buscaSucursalId(Integer id) {
        return this.ri.findById(id);
    }

    public void eliminarSucursalId(Integer id) {
        // Validar si existe antes de eliminar
        Sucursal base = this.buscaSucursalId(id);
        if (base == null) {
            throw new jakarta.ws.rs.NotFoundException("No se puede eliminar: La sucursal con ID " + id + " no existe.");
        }
        this.ri.delete(base); // Usamos delete(base) en lugar de deleteById para asegurar el proceso
    }

    public List<Sucursal> buscarTodos() {
        return this.ri.findAll().list();
    }
}