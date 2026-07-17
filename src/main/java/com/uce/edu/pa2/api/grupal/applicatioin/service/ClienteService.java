package com.uce.edu.pa2.api.grupal.applicatioin.service;



import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Cliente;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ClienteRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl ri;

    public void crearCliente(Cliente cliente) {
        this.ri.persist(cliente);
    }

    public void actualizarCliente(Cliente cliente, String cedula) {
        Cliente base = this.buscaClienteId(cedula);
        base.setNombre(cliente.getNombre());
        base.setTelefono(cliente.getTelefono());
        base.setReservas(cliente.getReservas());
    }

    public Cliente buscaClienteId(String cedula) {
        return Cliente.findById(cedula);
    }

    public void eliminarClienteId(String cedula) {
       this.ri.delete("cedula", cedula);
    }

    public List<Cliente> buscarTodos() {
        return this.ri.findAll().list();
    }
}
