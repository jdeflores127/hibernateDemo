package com.hibernate.example.demohibernate.controller.rutina;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hibernate.example.demohibernate.controller.ErrorRespuesta;
import com.hibernate.example.demohibernate.controller.dto.RutinaDTO;
import com.hibernate.example.demohibernate.controller.mappers.RutinaDtoMapper;
import com.hibernate.example.demohibernate.model.Rutina;
import com.hibernate.example.demohibernate.utils.TransactionUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/rutina")
class RutinaRestController {

    @Autowired
    EntityManagerFactory emf;

    @Autowired
    RutinaDtoMapper rutinaDtoMapper;

    @GetMapping
    public ResponseEntity<List<RutinaDTO>> getAll() {

        List<Rutina> items = TransactionUtil.inTransactionWithReturn(emf,
                em -> em.createQuery("from Rutina").getResultList());
        
        
        return new ResponseEntity<>(rutinaDtoMapper.toDto(items), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<RutinaDTO> getById(@PathVariable("id") Long id) {

        RutinaDTO rutina = TransactionUtil.inTransactionWithReturn(emf,
                em -> {
                        Rutina rutinadb = Optional.ofNullable(em.find(Rutina.class, id))
                        .orElseThrow(() -> new RutinaException("No existe la rutina con Id " + id));

                        return rutinaDtoMapper.toDto(rutinadb);
                    });

        return new ResponseEntity<>(rutina, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<RutinaDTO> create(@RequestBody RutinaDTO item) {
        Rutina rutina = rutinaDtoMapper.toEntity(item);
        rutina.setIdRutina(null);

        TransactionUtil.inTransaction(emf, em -> em.persist(rutina));

        return new ResponseEntity<>(item, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<RutinaDTO> update(@RequestBody RutinaDTO item) {
        Rutina rutina = rutinaDtoMapper.toEntity(item);

        TransactionUtil.inTransaction(emf, em -> {
            Rutina rutinaDb = this.getRutinaById(em, rutina.getIdRutina());
            rutinaDb = rutina;
            em.merge(rutinaDb);
        });

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    private Rutina getRutinaById(EntityManager em, Long id) {
        return Optional.ofNullable(em.find(Rutina.class, id))
                .orElseThrow(() -> new RutinaException("No existe la rutina con Id " + id));
    }

    @ExceptionHandler(RutinaException.class)
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
