package com.hibernate.example.demohibernate.controller.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.hibernate.example.demohibernate.controller.dto.UsuarioDTO;
import com.hibernate.example.demohibernate.model.Usuario;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,  uses={RutinaDtoMapper.class})
public interface UsuarioDtoMapper { 

    @BeanMapping(ignoreByDefault = false) 
    //@Mapping(target = "listaRutinas", ignore = true)
    UsuarioDTO toDto(Usuario usuario);
    
    @InheritInverseConfiguration
    // @Mapping(target = "version", ignore = true)
    // @Mapping(target = "listaRutinas", ignore = true)
    Usuario toEntity(UsuarioDTO usuarioDTO);
    
    //@Mapping(target = "listaRutinas", ignore = true)
    List<UsuarioDTO> toDto(List<Usuario> listaUsuarios);
    
    List<Usuario> toEntity(List<UsuarioDTO> listaUsuarios);
}
