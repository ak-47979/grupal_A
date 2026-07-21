package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Vendedor;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.VendedorRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl ri;

    public void crearVendedor(Vendedor vendedor) {
        //Validar que el objeto completo no llegue nulo
        if (vendedor == null) {
            throw new BadRequestException("Los datos del vendedor no pueden ser nulos.");
        }

        //Validar la cédula: obligatoria, no más de 10 dígitos y solo números
        if (vendedor.getCedula() == null || vendedor.getCedula().isBlank()) {
            throw new BadRequestException("La cédula del vendedor es obligatoria.");
        }

        if (!vendedor.getCedula().matches("\\d{10}")) {
            throw new BadRequestException("La cédula es inválida. Debe contener exactamente 10 dígitos numéricos.");
        }

        //Validar que nombre y apellido no sean nulos o vacíos
        if (vendedor.getNombre() == null || vendedor.getNombre().isBlank()) {
            throw new BadRequestException("El nombre del vendedor es obligatorio.");
        }

        if (vendedor.getApellido() == null || vendedor.getApellido().isBlank()) {
            throw new BadRequestException("El apellido del vendedor es obligatorio.");
        }

        //Validar que la cédula no esté registrada previamente
        if (this.ri.findById(vendedor.getCedula()) != null) {
            throw new BadRequestException("Ya existe un vendedor registrado con la cédula: " + vendedor.getCedula());
        }

        this.ri.persist(vendedor);
    }

    public void actualizarVendedor(Vendedor vendedor, String cedula) {
        //Validar si el vendedor realmente existe en la BD
        Vendedor base = this.buscaVendedorId(cedula);
        if (base == null) {
            throw new NotFoundException("El vendedor con cédula " + cedula + " no existe.");
        }

        //Validaciones al actualizar para evitar sobreescribir con valores nulos o vacíos
        if (vendedor.getNombre() != null && !vendedor.getNombre().isBlank()) {
            base.setNombre(vendedor.getNombre());
        }

        if (vendedor.getApellido() != null && !vendedor.getApellido().isBlank()) {
            base.setApellido(vendedor.getApellido());
        }
    }

    public Vendedor buscaVendedorId(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            throw new BadRequestException("Debe proporcionar una cédula para realizar la búsqueda.");
        }
        
        if (cedula.length() > 10) {
            throw new BadRequestException("La cédula ingresada excede los 10 dígitos permitidos.");
        }

        return this.ri.findById(cedula);
    }

    public void eliminarVendedorId(String cedula) {
        // Validar existencia antes de eliminar
        Vendedor base = this.buscaVendedorId(cedula);
        if (base == null) {
            throw new NotFoundException("No se puede eliminar. El vendedor con cédula " + cedula + " no existe.");
        }
        this.ri.deleteById(cedula);
    }

    public List<Vendedor> buscarTodos() {
        return this.ri.findAll().list();
    }
}