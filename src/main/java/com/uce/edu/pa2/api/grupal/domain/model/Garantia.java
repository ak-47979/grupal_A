package com.uce.edu.pa2.api.grupal.domain.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Table(name = "garantia")
@Entity
public class Garantia extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_garantia_generador", sequenceName = "seq_garantia", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_garantia_generador")
    @Column(name = "gara_id")
    private Integer id;

    @Column(name = "gara_tipo")
    private String tipo; // Ej: Tarjeta de crédito, depósito

    @Column(name = "gara_monto")
    private Double monto;

    // Relación inversa OneToOne: Mapeada por la propiedad de la clase Reserva
    @OneToOne(mappedBy = "garantia")
    private Reserva reserva;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return "Garantia [id=" + id + ", tipo=" + tipo + ", monto=" + monto + ", reserva=" + reserva + "]";
    }

    
}