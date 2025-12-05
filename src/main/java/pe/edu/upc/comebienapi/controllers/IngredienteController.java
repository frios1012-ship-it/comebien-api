package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOIngrediente;
import pe.edu.upc.comebienapi.services.IngredienteService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comebien/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping
    public ResponseEntity<DTOIngrediente> create(@RequestBody DTOIngrediente alimento) {
        return new ResponseEntity<>(ingredienteService.add(alimento), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOIngrediente>> listAll() {
        return new ResponseEntity<>(ingredienteService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOIngrediente> update(@PathVariable Long id, @RequestBody DTOIngrediente alimento) {
        DTOIngrediente updated = ingredienteService.update(id, alimento);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
