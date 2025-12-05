package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOCalificacion;
import pe.edu.upc.comebienapi.services.CalificacionService;

import java.util.List;

@RestController
@RequestMapping("/comebien/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public DTOCalificacion create(@RequestBody DTOCalificacion dto) {
        return calificacionService.add(dto);
    }

    @PutMapping("/{id}")
    public DTOCalificacion update(@PathVariable Long id, @RequestBody DTOCalificacion dto) {
        return calificacionService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        calificacionService.delete(id);
    }

    @GetMapping
    public List<DTOCalificacion> listAll() {
        return calificacionService.listAll();
    }
}
