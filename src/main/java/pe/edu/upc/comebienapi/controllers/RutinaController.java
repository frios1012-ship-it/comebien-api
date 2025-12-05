package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTORutina;
import pe.edu.upc.comebienapi.services.RutinaService;

import java.util.List;

@RestController
@RequestMapping("/comebien/rutinas")
public class RutinaController {

    @Autowired
    private RutinaService rutinaService;

    @PostMapping
    public ResponseEntity<DTORutina> create(@RequestBody DTORutina dto) {
        return new ResponseEntity<>(rutinaService.add(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTORutina>> listAll() {
        return new ResponseEntity<>(rutinaService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTORutina> update(@PathVariable Long id, @RequestBody DTORutina dto) {
        DTORutina updated = rutinaService.update(id, dto);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rutinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
