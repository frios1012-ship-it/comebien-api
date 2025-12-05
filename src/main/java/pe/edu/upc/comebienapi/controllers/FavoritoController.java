package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOFavorito;
import pe.edu.upc.comebienapi.services.FavoritoService;

import java.util.List;

@RestController
@RequestMapping("/comebien/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<DTOFavorito> create(@RequestBody DTOFavorito dto) {
        return new ResponseEntity<>(favoritoService.add(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOFavorito>> listAll() {
        return new ResponseEntity<>(favoritoService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOFavorito> update(@PathVariable Long id, @RequestBody DTOFavorito dto) {
        DTOFavorito updated = favoritoService.update(id, dto);
        if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        favoritoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
