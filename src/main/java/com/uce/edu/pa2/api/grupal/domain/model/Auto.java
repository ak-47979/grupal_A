package com.uce.edu.pa2.api.grupal.domain.model;

import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Table(name = "auto")
@Entity
public class Auto extends PanacheEntityBase {

    @Id
    @Column(name = "auto_matricula") // La matrícula actúa como clave primaria string
    private String matricula;

    @Column(name = "auto_marca")
    private String marca;

    @Column(name = "auto_modelo")
    private String modelo;

    // Relación inversa OneToMany: Un auto puede registrarse en múltiples reservas
    @OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
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

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "Auto [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + "]";
    }

   
}
