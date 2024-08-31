package com.hibernate.example.demohibernate.controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public class RutinaDTO {
    private Long idRutina;
    private String nombreRutina;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaFinalizacion;
    private LocalTime duracionRutina;
    private UsuarioDTO usuario;
    
    

    public RutinaDTO() {
    }
    
    public Long getIdRutina() {
        return idRutina;
    }
    public void setIdRutina(Long idRutina) {
        this.idRutina = idRutina;
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
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    
}
