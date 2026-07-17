package com.uce.edu.pa2.api.grupal.applicatioin.service;


import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Auto;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.AutoRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class AutoService {

    @Inject
    private AutoRepositoryImpl ri;

    public void crearAuto(Auto auto) {
        this.ri.persist(auto);
    }

    public void actualizarAuto(Auto auto, String matricula) {
        Auto base = this.buscaAutoId(matricula);
        base.setMarca(auto.getMarca());
        base.setModelo(auto.getModelo());
        base.setReservas(auto.getReservas());
    }

    public Auto buscaAutoId(String matricula) {
        return Auto.findById(matricula);
    }

    public void eliminarAutoId(String matricula) {
        this.ri.delete("matricula", matricula);
    }

    public List<Auto> buscarTodos() {
        return this.ri.findAll().list();
    }
}