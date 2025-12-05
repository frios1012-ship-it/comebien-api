package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.comebienapi.dtos.*;
import pe.edu.upc.comebienapi.repositories.*;
import pe.edu.upc.comebienapi.services.ReporteService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    @Autowired private ReporteRecetaRepository recetaRepo;
    @Autowired private ReporteRutinaRepository rutinaRepo;
    @Autowired private ClienteRepository clienteRepo;
    @Autowired private ReporteGeneralRepository generalRepo;

    // --- Recetas ---
    @Override
    public List<DTOTopReceta> top5Recetas() {
        return recetaRepo.top5Recetas().stream()
                .map(r -> new DTOTopReceta((String) r[0], ((Number) r[1]).doubleValue(), ((Number) r[2]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOPromedioCaloriasCliente> promedioCaloriasPorCliente() {
        return recetaRepo.promedioCaloriasPorCliente().stream()
                .map(r -> new DTOPromedioCaloriasCliente((String) r[0], ((Number) r[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTORecetasPorCliente> totalRecetasPorCliente() {
        return recetaRepo.totalRecetasPorCliente().stream()
                .map(r -> new DTORecetasPorCliente((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTORecetaSimple> buscarRecetasPorNombre(String nombre) {
        return recetaRepo.findByNombreContainingIgnoreCase(nombre)
                .stream().map(r -> new DTORecetaSimple(r.getId(), r.getNombre(), r.getCaloriasTotales()))
                .collect(Collectors.toList());
    }

    // --- Rutinas ---
    @Override
    public List<DTORutinasPorNivel> rutinasPorNivel() {
        return rutinaRepo.rutinasPorNivel().stream()
                .map(r -> new DTORutinasPorNivel((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOPromedioDuracionObjetivo> promedioDuracionPorObjetivo() {
        return rutinaRepo.promedioDuracionPorObjetivo().stream()
                .map(r -> new DTOPromedioDuracionObjetivo((String) r[0], ((Number) r[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTORutinaSimple> buscarRutinasPorObjetivo(String objetivo) {
        return rutinaRepo.findByObjetivoContainingIgnoreCase(objetivo)
                .stream().map(r -> new DTORutinaSimple(r.getId(), r.getNombre(), r.getObjetivo()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTORutinaSimple> rutinasCortas(Double minutos) {
        return rutinaRepo.findByDuracionTotalLessThan(minutos)
                .stream().map(r -> new DTORutinaSimple(r.getId(), r.getNombre(), r.getObjetivo()))
                .collect(Collectors.toList());
    }

    // --- Clientes ---
    @Override
    public List<DTOClienteSimple> buscarClientesPorNombre(String nombre) {
        return clienteRepo.buscarPorNombre(nombre).stream()
                .map(c -> new DTOClienteSimple(
                        c.getId(),
                        c.getNombre(),
                        c.getUser() != null ? c.getUser().getUsername() : "Sin usuario"
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOClientesConListas> clientesConMasListas() {
        return clienteRepo.clientesConMasListas().stream()
                .map(r -> new DTOClientesConListas((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

    // --- Generales ---
    @Override
    public List<DTOTopListaFavorita> topListasFavoritas() {
        return generalRepo.topListasFavoritas().stream()
                .map(r -> new DTOTopListaFavorita((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<DTOPromedioCalificacionCliente> promedioCalificacionPorCliente() {
        return generalRepo.promedioCalificacionPorCliente().stream()
                .map(r -> new DTOPromedioCalificacionCliente((String) r[0], ((Number) r[1]).doubleValue()))
                .collect(Collectors.toList());
    }
}
