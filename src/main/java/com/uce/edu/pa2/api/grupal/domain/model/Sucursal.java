package com.uce.edu.pa2.api.grupal.domain.model;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sucursal")
public class Sucursal extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_sucursal_generador")
    @SequenceGenerator(name = "seq_sucursal_generador", sequenceName = "seq_sucursal", allocationSize = 1)

    @Column(name = "sucu_id")
    private Integer id;

    @Column(name = "sucu_nombre", nullable = false)
    private String nombre;

    @Column(name = "sucu_ciudad", nullable = false)
    private String ciudad;

    // Cambiado a LAZY para no saturar la base de datos
    @OneToMany(mappedBy = "sucursal", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("sucursal")
    private List<Vehiculo> vehiculos;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        return "Sucursal [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + "]";
    }

    
}