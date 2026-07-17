package com.uce.edu.pa2.api.grupal.domain.model;

import java.time.LocalDate;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Table(name = "reserva")
@Entity
public class Reserva extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_reserva_generador", sequenceName = "seq_reserva", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva_generador")
    @Column(name = "reser_id")
    private Integer id;

    @Column(name = "reser_fecha")
    private LocalDate fecha;

    // Relación ManyToOne: Muchas reservas pertenecen a un Cliente
    // Vincula mediante la cédula del cliente como Llave Foránea (FK)
    @ManyToOne
    @JoinColumn(name = "clie_cedula", referencedColumnName = "clie_cedula")
    private Cliente cliente;

    // Relación ManyToOne: Muchas reservas corresponden a un Auto
    // Vincula mediante la matrícula del auto como Llave Foránea (FK)
    @ManyToOne
    @JoinColumn(name = "auto_matricula", referencedColumnName = "auto_matricula")
    private Auto auto;

    // Relación OneToOne: Una reserva tiene una única garantía
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gara_id", referencedColumnName = "gara_id")
    private Garantia garantia;

    // Relación ManyToMany: Una reserva tiene muchos servicios adicionales
    @ManyToMany
    @JoinTable(
        name = "reserva_servicio",
        joinColumns = @JoinColumn(name = "reser_id"),
        inverseJoinColumns = @JoinColumn(name = "serv_id")
    )
    private List<ServicioAdicional> serviciosAdicionales;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

    public List<ServicioAdicional> getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(List<ServicioAdicional> serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", auto=" + auto + ", garantia="
                + garantia + "]";
    }

    
}