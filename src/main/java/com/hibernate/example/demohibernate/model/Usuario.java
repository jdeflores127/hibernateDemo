package com.hibernate.example.demohibernate.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.NaturalId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDUSUARIO")
    @SequenceGenerator(name = "SEQ_IDUSUARIO", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_IDUSUARIO")
    private Long idUsuario;
    
    @NaturalId
    private String correoRegistro;

    @Column
    private String nombre;
    
    @Column
    private String apPaterno;
    
    @Column
    private String apMaterno;
    
    @Column
    private Integer edad;

    @Column
    private Float peso;

    @Column
    private Integer imc;

    @Column
    private Float porcentajeGrasa;
    
    @Column
    private Float porcentajeMusculo;

    @Column
    private Integer metabolismoBasal;

    @OneToMany(mappedBy = "usuario")
    private List<Rutina> listaRutinas = new ArrayList<>();

    //Columna usada para una transaccion Optimista
    @Version
    private Long version;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getImc() {
        return imc;
    }

    public void setImc(Integer imc) {
        this.imc = imc;
    }

    public Float getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(Float porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public Float getPorcentajeMusculo() {
        return porcentajeMusculo;
    }

    public void setPorcentajeMusculo(Float porcentajeMusculo) {
        this.porcentajeMusculo = porcentajeMusculo;
    }

    public Integer getMetabolismoBasal() {
        return metabolismoBasal;
    }

    public void setMetabolismoBasal(Integer metabolismoBasal) {
        this.metabolismoBasal = metabolismoBasal;
    }
    
    public String getCorreoRegistro() {
        return correoRegistro;
    }

    public void setCorreoRegistro(String correoRegistro) {
        this.correoRegistro = correoRegistro;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Rutina> getListaRutinas() {
        return listaRutinas;
    }

    public void setListaRutinas(List<Rutina> listaRutinas) {
        this.listaRutinas = listaRutinas;
    }
    

    @Override
    public String toString() {
        return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno="
                + apMaterno + ", edad=" + edad + ", peso=" + peso + ", imc=" + imc + ", porcentajeGrasa="
                + porcentajeGrasa + ", porcentajeMusculo=" + porcentajeMusculo + ", metabolismoBasal="
                + metabolismoBasal + "]";
    }

}
