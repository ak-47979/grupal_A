package com.uce.edu.pa2.api.grupal.applicatioin.service;



import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Garantia;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.GarantiaRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class GarantiaService {

    @Inject
    private GarantiaRepositoryImpl ri;

    public void crearGarantia(Garantia garantia) {
        this.ri.persist(garantia);
    }

    public void actualizarGarantia(Garantia garantia, Integer id) {
        Garantia base = this.buscaGarantiaId(id);
        base.setTipo(garantia.getTipo());
        base.setMonto(garantia.getMonto());
        base.setReserva(garantia.getReserva());
    }

    public Garantia buscaGarantiaId(Integer id) {
        return Garantia.findById(id);
    }

    public void eliminarGarantiaId(Integer id) {
        this.ri.deleteById(id);
    }

    public List<Garantia> buscarTodos() {
        return this.ri.findAll().list();
    }
}