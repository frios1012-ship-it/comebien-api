package pe.edu.upc.comebienapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.comebienapi.dtos.*;
import pe.edu.upc.comebienapi.services.ReporteService;

import java.util.List;

@RestController
@RequestMapping("/comebien/reportes")
public class ReporteController {

    @Autowired
    private ReporteService service;

    // Recetas
    @GetMapping("/top-recetas") public List<DTOTopReceta> topRec() { return service.top5Recetas(); }
    @GetMapping("/promedio-calorias") public List<DTOPromedioCaloriasCliente> promCal() { return service.promedioCaloriasPorCliente(); }
    @GetMapping("/total-recetas-cliente") public List<DTORecetasPorCliente> totRec() { return service.totalRecetasPorCliente(); }
    @GetMapping("/buscar-receta/{nombre}") public List<DTORecetaSimple> recNom(@PathVariable String nombre) { return service.buscarRecetasPorNombre(nombre); }

    // Rutinas
    @GetMapping("/rutinas-nivel") public List<DTORutinasPorNivel> rutNivel() { return service.rutinasPorNivel(); }
    @GetMapping("/promedio-duracion") public List<DTOPromedioDuracionObjetivo> promDur() { return service.promedioDuracionPorObjetivo(); }
    @GetMapping("/buscar-rutina/{objetivo}") public List<DTORutinaSimple> rutObj(@PathVariable String objetivo) { return service.buscarRutinasPorObjetivo(objetivo); }
    @GetMapping("/rutinas-cortas/{min}") public List<DTORutinaSimple> rutCort(@PathVariable("min") Double min) { return service.rutinasCortas(min); }

    // Clientes
    @GetMapping("/buscar-clientes/{nombre}") public List<DTOClienteSimple> buscarClientesPorNombre(@PathVariable String nombre) { return service.buscarClientesPorNombre(nombre); }
    @GetMapping("/clientes-mas-listas") public List<DTOClientesConListas> cliListas() { return service.clientesConMasListas(); }

    // Generales
    @GetMapping("/top-listas-favoritas")
    public List<DTOTopListaFavorita> getTopListasFavoritas() { return service.topListasFavoritas(); }
    @GetMapping("/promedio-calif") public List<DTOPromedioCalificacionCliente> promCalif() { return service.promedioCalificacionPorCliente(); }
}
