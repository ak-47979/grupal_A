package com.uce.edu.pa2.api.grupal.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente extends PanacheEntityBase {

    @Id
    @Column(name = "clie_cedula")
    private String cedula;

    @Column(name = "clie_nombre", nullable = false)
    private String nombre;

    @Column(name = "clie_telefono")
    private String telefono;

    // Relación inversa: Un cliente puede tener un historial de muchas reservas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties("cliente")
    private List<Reserva> reservas;
    public Cliente(){

    }
    public Cliente(String cedula, String nombre, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}