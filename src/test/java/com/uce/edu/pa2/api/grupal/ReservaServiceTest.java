package com.uce.edu.pa2.api.grupal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;





import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.uce.edu.pa2.api.grupal.applicatioin.service.ReservaService;
import com.uce.edu.pa2.api.grupal.domain.model.Reserva;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReservaServiceTest {

    @Inject
    ReservaService service;

    private static Integer reservaId;

    @Test
    @Order(1)
    public void probarCrearReserva() {
        Reserva nueva = new Reserva();
        nueva.setFecha(LocalDate.now().plusDays(5)); // Reserva para dentro de 5 días
        
        // Si tu base de datos exige que no sean nulos, aquí podrías hacer:
        // nueva.setCliente(new Cliente("172XXXXXX")); 
        // nueva.setAuto(new Auto("PCX-0000"));
        nueva.setCliente(null); 
        nueva.setAuto(null);
        nueva.setGarantia(null);
        nueva.setServiciosAdicionales(new ArrayList<>());

        service.crearReserva(nueva);

        Assertions.assertNotNull(nueva.getId(), "El ID de la reserva no debería ser nulo tras persistir");
        reservaId = nueva.getId();
        
        System.out.println(">>> Reserva mapeada y creada exitosamente con ID: " + reservaId);
    }

    @Test
    @Order(2)
    public void probarBuscarTodasYPorId() {
        List<Reserva> lista = service.buscarTodos();
        Assertions.assertFalse(lista.isEmpty(), "La lista de reservas no debería estar vacía");

        Reserva encontrada = service.buscaReservaId(reservaId);
        Assertions.assertNotNull(encontrada, "Debería encontrar la reserva con el ID guardado");
        Assertions.assertEquals(LocalDate.now().plusDays(5), encontrada.getFecha());
    }

    @Test
    @Order(3)
    public void probarActualizarReserva() {
        Reserva modificada = new Reserva();
        // Cambiamos la fecha para comprobar la actualización
        modificada.setFecha(LocalDate.now().plusDays(10)); 
        modificada.setCliente(null);
        modificada.setAuto(null);

        service.actualizarReserva(modificada, reservaId);

        Reserva base = service.buscaReservaId(reservaId);
        Assertions.assertEquals(LocalDate.now().plusDays(10), base.getFecha(), "La fecha debió actualizarse a 10 días posteriores");
        
        System.out.println(">>> Reserva actualizada correctamente en la base de datos.");
    }

    @Test
    @Order(4)
    public void probarEliminarReserva() {
        service.eliminarReservaId(reservaId);

        Reserva eliminada = service.buscaReservaId(reservaId);
        Assertions.assertNull(eliminada, "La reserva debería haber sido eliminada de la base de datos");
        
        System.out.println(">>> Reserva eliminada correctamente de la base de datos.");
    }
}