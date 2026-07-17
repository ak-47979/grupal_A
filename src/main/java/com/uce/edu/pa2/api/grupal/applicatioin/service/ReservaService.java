package com.uce.edu.pa2.api.grupal.applicatioin.service;



import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Reserva;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ReservaRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaService {

    @Inject
    private ReservaRepositoryImpl ri;

    public void crearReserva(Reserva reserva) {
        this.ri.persist(reserva);
    }

    public void actualizarReserva(Reserva reserva, Integer id) {
        Reserva base = this.buscaReservaId(id);
        base.setFecha(reserva.getFecha());
        base.setCliente(reserva.getCliente());
        base.setAuto(reserva.getAuto());
        base.setGarantia(reserva.getGarantia());
        base.setServiciosAdicionales(reserva.getServiciosAdicionales());
    }

    public Reserva buscaReservaId(Integer id) {
        return Reserva.findById(id);
    }

    public void eliminarReservaId(Integer id) {
        this.ri.deleteById(id);
    }

    public List<Reserva> buscarTodos() {
        return this.ri.findAll().list();
    }
}
