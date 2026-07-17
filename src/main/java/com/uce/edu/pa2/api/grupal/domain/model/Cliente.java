package com.uce.edu.pa2.api.grupal.domain.model;


import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Table(name = "cliente")
@Entity
public class Cliente extends PanacheEntityBase {

    @Id
    @Column(name = "clie_cedula") // La cédula actúa como clave primaria string
    private String cedula;

    @Column(name = "clie_nombre")
    private String nombre;

    @Column(name = "clie_telefono")
    private String telefono;

    // Relación inversa OneToMany: Un cliente tiene una lista de reservas
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

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

    @Override
    public String toString() {
        return "Cliente [cedula=" + cedula + ", nombre=" + nombre + ", telefono=" + telefono + "]";
    }

    
}