package com.uce.edu.pa2.api.grupal.applicatioin.service;

import java.util.List;

import com.uce.edu.pa2.api.grupal.domain.model.Cliente;
import com.uce.edu.pa2.api.grupal.domain.model.Reserva;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ClienteRepositoryImpl;
import com.uce.edu.pa2.api.grupal.insfraestructure.repository.ReservaRepositoryImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl ri;
    @Inject
    private ReservaRepositoryImpl reservaRepositoryImpl;

    public void crearCliente(Cliente cliente) {
        this.ri.persist(cliente);
    }

    @Transactional
    public void actualizarCliente(Cliente cliente, String cedula) {
        // Validar si el cliente existe
        Cliente base = this.buscaClienteId(cedula);
        if (base == null) {
            throw new jakarta.ws.rs.NotFoundException("El cliente con cédula " + cedula + " no existe.");
        }

        // Actualizar campos básicos
        base.setNombre(cliente.getNombre());
        base.setTelefono(cliente.getTelefono());

        // Si el JSON incluye la lista de reservas (no es null), procesamos los cambios
        if (cliente.getReservas() != null) {

            // Mapeamos las reservas que vienen en el JSON a una lista de sus IDs
            List<Integer> idsNuevos = (cliente.getReservas() != null) ? cliente.getReservas().stream()
                    .filter(reserva -> reserva != null && reserva.getId() != null) // 1. Filtra objetos e id nulo
                    .map(Reserva::getId) // 2. Transforma a Integer de forma segura
                    .toList()
                    : List.of();

            // Mapeamos las reservas que actualmente tiene el cliente en la BD a sus IDs
            List<Integer> idsActuales = (base.getReservas() != null) ? base.getReservas().stream()
                    .filter(reserva -> reserva != null && reserva.getId() != null) // Filtra nulos
                    .map(Reserva::getId)
                    .toList()
                    : List.of();

            // Solo actuamos si las listas de IDs son diferentes (hubo cambios reales)
            if (!idsNuevos.equals(idsActuales)) {

                // Desvincular de la BD las reservas viejas que ya no van a pertenecer a este
                // cliente
                for (Reserva reservaVieja : base.getReservas()) {
                    if (!idsNuevos.contains(reservaVieja.getId())) {
                        reservaVieja.setCliente(null);
                    }
                }
                base.getReservas().clear(); // Limpiamos la lista administrada para reconstruirla

                // Validar e integrar las reservas del JSON
                for (Integer idNuevo : idsNuevos) {
                    // Verificar si cada reserva del JSON existe en la BD
                    Reserva reservaBD = reservaRepositoryImpl.findById(idNuevo);
                    if (reservaBD == null) {
                        throw new jakarta.ws.rs.BadRequestException(
                                "La reserva con ID " + idNuevo + " no existe en el sistema.");
                    }
                    // Establecemos la relación bidireccional segura
                    reservaBD.setCliente(base);
                    base.getReservas().add(reservaBD);
                }
            }

        }

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
