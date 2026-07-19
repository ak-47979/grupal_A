package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Cliente;
import com.uce.edu.pa2.api.grupal.domain.model.Reserva;
import com.uce.edu.pa2.api.grupal.domain.model.Vehiculo;
import com.uce.edu.pa2.api.grupal.domain.model.Vendedor;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ClienteRepositoryImpl;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ReservaRepositoryImpl;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.VehiculoRepositoryImpl;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.VendedorRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaService {

    @Inject
    private ReservaRepositoryImpl ri;
    @Inject
    private VehiculoRepositoryImpl vehiculoRepositoryImpl;
    @Inject
    private ClienteRepositoryImpl clienteRepositoryImpl;

    private VendedorRepositoryImpl vendedorRepositoryImpl;
    public void crearReserva(Reserva reserva) {
        this.ri.persist(reserva);
    }

    public void actualizarReserva(Reserva reserva, Integer id) {
        Reserva reservaExistente = this.buscaReservaId(id);
        if (reservaExistente == null) {
            throw new jakarta.ws.rs.NotFoundException("La reserva con ID " + id + " no existe.");
        }

        // Validar Vehículo por Placa
        String placa = reserva.getVehiculo() != null ? reserva.getVehiculo().getPlaca() : null;
        Vehiculo vehiculoBD = vehiculoRepositoryImpl.findById(placa);
        if (vehiculoBD == null) {
            throw new jakarta.ws.rs.BadRequestException("No existe el vehículo con la placa especificada.");
        }

        // Validar Cliente por Cédula
        String cedulaCliente = reserva.getCliente() != null ? reserva.getCliente().getCedula() : null;
        Cliente clienteBD = clienteRepositoryImpl.findById(cedulaCliente); 
        if (clienteBD == null) {
            throw new jakarta.ws.rs.BadRequestException("No existe el cliente con la cédula especificada.");
        }

        // Validar Vendedor por Cédula
        String cedulaVendedor = reserva.getVendedor() != null ? reserva.getVendedor().getCedula() : null;
        Vendedor vendedorBD = vendedorRepositoryImpl.findById(cedulaVendedor);
        if (vendedorBD == null) {
            throw new jakarta.ws.rs.BadRequestException("No existe el vendedor con la cédula especificada.");
        }

        // Asigna valores para que no existan errores
        reservaExistente.setCliente(clienteBD);
        reservaExistente.setVendedor(vendedorBD);
        reservaExistente.setVehiculo(vehiculoBD);
        reservaExistente.setFecha(reserva.getFecha());
    }

    public Reserva buscaReservaId(Integer id) {
        return this.ri.findById(id);
    }

    public void eliminarReservaId(Integer id) {
        this.ri.deleteById(id);
    }

    public List<Reserva> buscarTodos() {
        return this.ri.findAll().list();
    }
}