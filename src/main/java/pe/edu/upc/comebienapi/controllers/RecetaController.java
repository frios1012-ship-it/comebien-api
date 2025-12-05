package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOReceta;
import pe.edu.upc.comebienapi.services.RecetaService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comebien/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @PostMapping
    public ResponseEntity<DTOReceta> create(@RequestBody DTOReceta receta) {
        return new ResponseEntity<>(recetaService.add(receta), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOReceta>> listAll() {
        return new ResponseEntity<>(recetaService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTOReceta> getById(@PathVariable Long id) {
        DTOReceta receta = recetaService.findById(id);
        if (receta == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(receta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOReceta> update(@PathVariable Long id, @RequestBody DTOReceta receta) {
        DTOReceta updated = recetaService.update(id, receta);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recetaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
