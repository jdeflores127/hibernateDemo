package com.hibernate.example.demohibernate.controller.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hibernate.example.demohibernate.controller.ErrorRespuesta;
import com.hibernate.example.demohibernate.controller.dto.UsuarioDTO;
import com.hibernate.example.demohibernate.controller.mappers.UsuarioDtoMapper;
import com.hibernate.example.demohibernate.model.Usuario;
import com.hibernate.example.demohibernate.utils.TransactionUtil;

import jakarta.persistence.EntityManagerFactory;

import static java.time.LocalDateTime.now;

@Controller(value = "usuario")
@RequestMapping(value = "/usuario")
public class UsuarioRestController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired UsuarioDtoMapper usuarioDtoMapper;

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {

        TransactionUtil.inTransaction(entityManagerFactory, entityManager -> entityManager.persist(usuario) );
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<Usuario> items = TransactionUtil.inTransactionWithReturn(entityManagerFactory,
            entityManager -> entityManager.createQuery("from Usuario").getResultList());
        return new ResponseEntity<>(usuarioDtoMapper.toDto(items), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usu) {

        TransactionUtil.inTransaction(entityManagerFactory, entityManager -> {
            
            Usuario existingItemOptional = Optional
                .ofNullable(entityManager.find(Usuario.class, usu.getIdUsuario()))
                .orElseThrow(()->new UsuarioException("No existe el usuario con ID "));
                
                existingItemOptional=usu;

                entityManager.merge(existingItemOptional);
        });

        return new ResponseEntity<>(usu, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDTO> getUserById(@PathVariable("id") Long id) {

        Usuario usuario = TransactionUtil.inTransactionWithReturn(entityManagerFactory,
            entityManager -> {
                
                Usuario usu=Optional
                        .ofNullable(entityManager.find(Usuario.class, id))
                        .orElseThrow( ()->new UsuarioException("No existe el usuario con ID "+id));
                
                usu.getListaRutinas().stream().forEach(System.out::println);
                
                return usu;
            });
           
            
        return new ResponseEntity<>(usuarioDtoMapper.toDto(usuario), HttpStatus.OK);
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<ErrorRespuesta> errorUsuarioHandler(Exception e) {
        ErrorRespuesta mensajeError = ErrorRespuesta.builder()
        .codigoError(10)
        .descripcionError(e.getMessage())
        .fechaPeticion(now())
        .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorRespuesta> errorHandler(Exception e) throws Exception {
        ErrorRespuesta mensajeError = ErrorRespuesta.builder()
        .codigoError(10)
        .descripcionError("Ocurrio un error interno, consulte con el area de soporte")
        .fechaPeticion(now())
        .build();

        e.printStackTrace();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensajeError);
    }
}
