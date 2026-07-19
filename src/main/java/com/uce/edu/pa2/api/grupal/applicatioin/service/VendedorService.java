package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;
import com.uce.edu.pa2.api.grupal.domain.model.Vendedor;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.VendedorRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl ri;

    public void crearVendedor(Vendedor vendedor) {
        this.ri.persist(vendedor);
    }

    public void actualizarVendedor(Vendedor vendedor, String cedula) {
        // Validar si el vendedor realmente existe en la BD
        Vendedor base = this.buscaVendedorId(cedula);
        if (base == null) {
            throw new jakarta.ws.rs.NotFoundException("El vendedor con cédula " + cedula + " no existe.");
        }

        // Actualizar solo los campos propios del perfil del vendedor
        base.setNombre(vendedor.getNombre());
        base.setApellido(vendedor.getApellido());

    }

    public Vendedor buscaVendedorId(String cedula) {
        return this.ri.findById(cedula);
    }

    public void eliminarVendedorId(String cedula) {
        this.ri.deleteById(cedula);
    }

    public List<Vendedor> buscarTodos() {
        return this.ri.findAll().list();
    }
}