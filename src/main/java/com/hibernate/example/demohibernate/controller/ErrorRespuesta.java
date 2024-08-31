package com.hibernate.example.demohibernate.controller;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorRespuesta {
    private String descripcionError;
    private Integer codigoError;
    private LocalDateTime fechaPeticion;
}
