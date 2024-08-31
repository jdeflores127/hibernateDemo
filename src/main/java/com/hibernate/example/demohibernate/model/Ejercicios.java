package com.hibernate.example.demohibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Ejercicios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ejercicios")
    @SequenceGenerator(name = "seq_ejercicios", allocationSize = 1, initialValue = 1, sequenceName = "seq_ejercicios")
    private Long id;

    @Column
    private String nombreEjercicio;

    @Column
    private String descripcion;

    @Enumerated(EnumType.STRING) 
    private GrupoMuscular grupoMuscular;

    @Column
    private Boolean pesoCorporal;

    @Column
    private Boolean requiereMancuernas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEjercicio() {
        return nombreEjercicio;
    }

    public void setNombreEjercicio(String nombreEjercicio) {
        this.nombreEjercicio = nombreEjercicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public Boolean getPesoCorporal() {
        return pesoCorporal;
    }

    public void setPesoCorporal(Boolean pesoCorporal) {
        this.pesoCorporal = pesoCorporal;
    }

    public Boolean getRequiereMancuernas() {
        return requiereMancuernas;
    }

    public void setRequiereMancuernas(Boolean requiereMancuernas) {
        this.requiereMancuernas = requiereMancuernas;
    }

    

}
