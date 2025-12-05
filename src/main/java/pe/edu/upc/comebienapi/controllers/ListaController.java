package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOLista;
import pe.edu.upc.comebienapi.services.ListaService;

import java.util.List;

@RestController
@RequestMapping("/comebien/listas")
public class ListaController {

    @Autowired
    private ListaService listaService;

    @PostMapping
    public ResponseEntity<DTOLista> create(@RequestBody DTOLista dto) {
        return new ResponseEntity<>(listaService.add(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOLista>> listAll() {
        return new ResponseEntity<>(listaService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOLista> update(@PathVariable Long id, @RequestBody DTOLista dto) {
        DTOLista updated = listaService.update(id, dto);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        listaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
