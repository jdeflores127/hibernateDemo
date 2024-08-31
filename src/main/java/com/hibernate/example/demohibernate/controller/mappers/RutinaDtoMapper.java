package com.hibernate.example.demohibernate.controller.mappers;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.hibernate.example.demohibernate.controller.dto.RutinaDTO;
import com.hibernate.example.demohibernate.model.Rutina;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses={UsuarioDtoMapper.class})
public interface RutinaDtoMapper { 
    
    //@Mapping(target = "usuario", source="usuario" ,defaultExpression = "java(new UsuarioDTO())")
    RutinaDTO toDto(Rutina rutina); 
     
    @InheritInverseConfiguration 
    Rutina toEntity(RutinaDTO rutinadto);
    
    //@Mapping(target = "usuario",defaultExpression = "java(new UsuarioDTO())")
    List<RutinaDTO> toDto(List<Rutina> listaUsuarios);

    List<Rutina> toEntity(List<RutinaDTO> listaUsuarios);


}
