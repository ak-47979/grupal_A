package com.uce.edu.pa2.api.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reserva")
public class Reserva extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_generador")
    @SequenceGenerator(name = "seq_reserva_generador", sequenceName = "seq_reserva", allocationSize = 1)
    @Column(name = "rese_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cedula_cliente", nullable = true)
    @JsonIgnoreProperties("reservas")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cedula_vendedor", nullable = true)
    @JsonIgnoreProperties("reservas")
    private Vendedor vendedor;

    @ManyToOne
    @JoinColumn(name = "placa_vehiculo", nullable = true)
    @JsonIgnoreProperties("reservas")
    private Vehiculo vehiculo;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Reserva() {
    }

    public Reserva(Integer id, Vendedor vendedor, Vehiculo vehiculo, LocalDateTime fecha) {
        this.id = id;
        this.vendedor = vendedor;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", vendedor=" + vendedor + ", vehiculo=" + vehiculo + ", fecha=" + fecha
                + "cliente=" + cliente + "]";
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}