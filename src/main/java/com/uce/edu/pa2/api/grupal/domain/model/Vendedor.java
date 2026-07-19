package com.uce.edu.pa2.api.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "vendedor")
public class Vendedor extends PanacheEntityBase {

    @Id
    @Column(name = "vend_cedula")
    public String cedula;

    @Column(name = "vend_nombre", nullable = false)
    public String nombre;

    @Column(name = "vend_apellido", nullable = false)
    public String apellido;

    // Relación inversa: Un vendedor participa en muchas reservas
    @OneToMany(mappedBy = "vendedor",fetch = FetchType.EAGER)
    @JsonIgnoreProperties("vendedor")
    public List<Reserva> reservas;
    public Vendedor(){}
    public Vendedor(String cedula, String nombre, String apellido) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    @Override
    public String toString() {
        return "Vendedor [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + "]";
    }
    
}