package com.hibernate.example.demohibernate.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDTO {
    private Long idUsuario;
    private String correoRegistro;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private Integer edad;
    private Float peso;
    private Integer imc;
    private Float porcentajeGrasa;
    private Float porcentajeMusculo;
    private Integer metabolismoBasal;

}
