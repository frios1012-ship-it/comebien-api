    package pe.edu.upc.comebienapi.controllers;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import pe.edu.upc.comebienapi.dtos.DTOEjercicio;
    import pe.edu.upc.comebienapi.services.EjercicioService;

    import java.util.List;

    @RestController
    @RequestMapping("/comebien/ejercicios")
    public class EjercicioController {

        @Autowired
        private EjercicioService ejercicioService;

        @PostMapping
        public ResponseEntity<DTOEjercicio> create(@RequestBody DTOEjercicio dto) {
            return new ResponseEntity<>(ejercicioService.add(dto), HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<DTOEjercicio>> listAll() {
            return new ResponseEntity<>(ejercicioService.listAll(), HttpStatus.OK);
        }

        @PutMapping("/{id}")
        public ResponseEntity<DTOEjercicio> update(@PathVariable Long id, @RequestBody DTOEjercicio dto) {
            DTOEjercicio updated = ejercicioService.update(id, dto);
            if (updated == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            ejercicioService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
