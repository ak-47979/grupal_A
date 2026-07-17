package com.uce.edu.pa2.api.grupal.applicatioin.service;



import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.ServicioAdicional;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ServicioAdicionalRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ServicioAdicionalService {

    @Inject
    private ServicioAdicionalRepositoryImpl  ri;

    public void crearServicio(ServicioAdicional servicio) {
        this.ri.persist(servicio);
    }

    public void actualizarServicio(ServicioAdicional servicio, Integer id) {
        ServicioAdicional base = this.buscaServicioId(id);
        base.setNombre(servicio.getNombre());
        base.setPrecioDia(servicio.getPrecioDia());
        base.setReservas(servicio.getReservas());
    }

    public ServicioAdicional buscaServicioId(Integer id) {
        return ServicioAdicional.findById(id);
    }

    public void eliminarServicioId(Integer id) {
        this.ri.deleteById(id);
    }

    public List<ServicioAdicional> buscarTodos() {
        return this.ri.findAll().list();
    }
}