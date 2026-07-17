package com.uce.edu.pa2.api.grupal.domain.model;



import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Table(name = "servicio_adicional")
@Entity
public class ServicioAdicional extends PanacheEntityBase {

    @Id
    @SequenceGenerator(name = "seq_servicio_generador", sequenceName = "seq_servicio", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servicio_generador")
    @Column(name = "serv_id")
    private Integer id;

    @Column(name = "serv_nombre")
    private String nombre; // Ej: GPS, Silla de bebé, Seguro Premium

    @Column(name = "serv_precio_dia")
    private Double precioDia;

    // Relación inversa ManyToMany: Este servicio puede estar asociado a muchas reservas
    @ManyToMany(mappedBy = "serviciosAdicionales")
    private List<Reserva> reservas;

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

    public Double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(Double precioDia) {
        this.precioDia = precioDia;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "ServicioAdicional [id=" + id + ", nombre=" + nombre + ", precioDia=" + precioDia + "]";
    }

   
}
