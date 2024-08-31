package com.hibernate.example.demohibernate.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Rutina")
public class Rutina {
    @Id
    @GeneratedValue(generator = "SEQ_RUTINA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SEQ_RUTINA" ,sequenceName = "SEQ_RUTINA", initialValue = 1, allocationSize = 1)
    private Long idRutina;

    @Column
    private String nombreRutina;

    @Column
    private String descripcion;

    @Column
    private LocalDate fechaCreacion;

    @Column
    private LocalDate fechaFinalizacion;

    @Column
    private LocalTime duracionRutina;

    //Llave foranea a la entidad Usuario.idUsuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuarioRutina", 
        referencedColumnName = "idUsuario",
        unique = false,
        nullable = true,
        foreignKey = @ForeignKey(name="FK_idUsuario_Rutina"))
    private Usuario usuario;

    public Long getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(Long idRutina) {
        this.idRutina = idRutina;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public LocalTime getDuracionRutina() {
        return duracionRutina;
    }

    public void setDuracionRutina(LocalTime duracionRutina) {
        this.duracionRutina = duracionRutina;
    }

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Rutina [idRutina=" + idRutina + ", nombreRutina=" + nombreRutina + ", descripcion=" + descripcion
                + ", fechaCreacion=" + fechaCreacion + ", fechaFinalizacion=" + fechaFinalizacion + ", duracionRutina="
                + duracionRutina + ", usuario=" + usuario + "]";
    }
    
}
