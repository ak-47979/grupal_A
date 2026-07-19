package com.uce.edu.pa2.api.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehiculo")
public class Vehiculo extends PanacheEntityBase {

    @Id
    @Column(name = "vehi_placa")
    public String placa;

    @Column(name = "vehi_marca", nullable = true)
    public String marca;

    @Column(name = "vehi_modelo", nullable = true)
    public String modelo;

    // Relación ManyToOne: El vehículo está estacionado en una sucursal específica
    @ManyToOne
    @JoinColumn(name = "vehi_sucu_id", nullable = true)
    public Sucursal sucursal;
    public Vehiculo(){}
    public Vehiculo(String placa, String marca, String modelo, Sucursal sucursal) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.sucursal = sucursal;
    }

    // Relación inversa: Un vehículo puede ser reservado múltiples veces en distintas fechas
    @OneToMany(mappedBy = "vehiculo")
    @com.fasterxml.jackson.annotation.JsonIgnore // 🟢 SOLUCIÓN DEFINITIVA
    public List<Reserva> reservas;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "Vehiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", sucursal=" + sucursal + "]";
    }
    
}