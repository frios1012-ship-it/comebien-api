package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.*;
import java.util.List;

public interface ReporteService {
    // Recetas
    List<DTOTopReceta> top5Recetas();
    List<DTOPromedioCaloriasCliente> promedioCaloriasPorCliente();
    List<DTORecetasPorCliente> totalRecetasPorCliente();
    List<DTORecetaSimple> buscarRecetasPorNombre(String nombre);

    // Rutinas
    List<DTORutinasPorNivel> rutinasPorNivel();
    List<DTOPromedioDuracionObjetivo> promedioDuracionPorObjetivo();
    List<DTORutinaSimple> buscarRutinasPorObjetivo(String objetivo);
    List<DTORutinaSimple> rutinasCortas(Double minutos);

    // Clientes
    List<DTOClienteSimple> buscarClientesPorNombre(String nombre);
    List<DTOClientesConListas> clientesConMasListas();

    // Generales
    List<DTOTopListaFavorita> topListasFavoritas();
    List<DTOPromedioCalificacionCliente> promedioCalificacionPorCliente();
}
