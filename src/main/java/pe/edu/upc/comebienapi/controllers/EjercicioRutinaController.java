package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOEjercicioRutina;
import pe.edu.upc.comebienapi.services.EjercicioRutinaService;

import java.util.List;

@RestController
@RequestMapping("/comebien/ejercicios-rutina")
public class EjercicioRutinaController {

    @Autowired
    private EjercicioRutinaService ejercicioRutinaService;

    @PostMapping
    public ResponseEntity<DTOEjercicioRutina> create(@RequestBody DTOEjercicioRutina dto) {
        return new ResponseEntity<>(ejercicioRutinaService.add(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOEjercicioRutina>> listAll() {
        return new ResponseEntity<>(ejercicioRutinaService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOEjercicioRutina> update(@PathVariable Long id, @RequestBody DTOEjercicioRutina dto) {
        DTOEjercicioRutina updated = ejercicioRutinaService.update(id, dto);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ejercicioRutinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
