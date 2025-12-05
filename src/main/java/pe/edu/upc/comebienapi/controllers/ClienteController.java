package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.DTOCliente;
import pe.edu.upc.comebienapi.services.ClienteService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comebien/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<DTOCliente> create(@RequestBody DTOCliente cliente) {
        DTOCliente saved = clienteService.add(cliente);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DTOCliente>> listAll() {
        return new ResponseEntity<>(clienteService.listAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTOCliente> update(@PathVariable Long id, @RequestBody DTOCliente cliente) {
        DTOCliente updated = clienteService.update(id, cliente);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
