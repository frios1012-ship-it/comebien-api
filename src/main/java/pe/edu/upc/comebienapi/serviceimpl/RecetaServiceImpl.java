package pe.edu.upc.comebienapi.serviceimpl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.comebienapi.dtos.*;
import pe.edu.upc.comebienapi.entities.*;
import pe.edu.upc.comebienapi.repositories.*;
import pe.edu.upc.comebienapi.services.RecetaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecetaServiceImpl implements RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private IngredienteRepository ingredienteRepository;
    @Autowired
    private IngredienteRecetaRepository ingredienteRecetaRepository;

    @Override
    @Transactional
    public DTOReceta add(DTOReceta dto) {
        Receta receta = new Receta();
        receta.setNombre(dto.getNombre());
        receta.setInstrucciones(dto.getInstrucciones());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setCaloriasTotales(dto.getCaloriasTotales());
        receta.setImagen(dto.getImagen());
        receta.setCliente(clienteRepository.findById(dto.getClienteId()).orElse(null));

        // Construimos hijos en memoria y “amarramos” ambos lados
        if (dto.getIngredientes() != null) {
            for (DTOIngredienteReceta d : dto.getIngredientes()) {
                IngredienteReceta ir = new IngredienteReceta();
                ir.setCantidad(d.getCantidad());
                ir.setUnidad(d.getUnidad());
                ir.setIngrediente(ingredienteRepository.findById(d.getIngredienteId()).orElse(null));

                ir.setReceta(receta);                 // back-reference
                receta.getIngredientes().add(ir);     // lado propietario en memoria
            }
        }

        // Persistimos UNA sola vez (cascade guarda hijos)
        receta = recetaRepository.save(receta);

        // Sin refetch: mapeamos desde el mismo objeto en memoria (está dentro de la tx)
        return mapToDTO(receta);
    }

    @Override
    @Transactional
    public DTOReceta update(Long id, DTOReceta dto) {
        Receta receta = recetaRepository.findById(id).orElse(null);
        if (receta == null) return null;

        receta.setNombre(dto.getNombre());
        receta.setInstrucciones(dto.getInstrucciones());
        receta.setTiempoPreparacion(dto.getTiempoPreparacion());
        receta.setCaloriasTotales(dto.getCaloriasTotales());
        receta.setImagen(dto.getImagen());
        receta.setCliente(clienteRepository.findById(dto.getClienteId()).orElse(null));

        receta.getIngredientes().clear();

        List<IngredienteReceta> nuevos = new ArrayList<>();

        for (DTOIngredienteReceta d : dto.getIngredientes()) {
            IngredienteReceta ir = new IngredienteReceta();
            ir.setCantidad(d.getCantidad());
            ir.setUnidad(d.getUnidad());
            ir.setIngrediente(ingredienteRepository.findById(d.getIngredienteId()).orElse(null));
            ir.setReceta(receta);
            nuevos.add(ir);
        }

        receta.getIngredientes().addAll(nuevos);

        receta = recetaRepository.save(receta);

        return mapToDTO(receta);
    }

    @Override
    public void delete(Long id) {
        recetaRepository.deleteById(id);
    }

    @Override
    public List<DTOReceta> listAll() {
        List<Receta> recetas = recetaRepository.findAll();

        recetas.forEach(r -> r.getIngredientes().size());

        return recetas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DTOReceta findById(Long id) {
        Receta receta = recetaRepository.findById(id).orElse(null);
        if (receta == null) return null;

        receta.getIngredientes().size();

        return mapToDTO(receta);
    }

    private DTOReceta mapToDTO(Receta r) {
        DTOReceta dto = new DTOReceta();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setInstrucciones(r.getInstrucciones());
        dto.setTiempoPreparacion(r.getTiempoPreparacion());
        dto.setCaloriasTotales(r.getCaloriasTotales());
        dto.setImagen(r.getImagen());
        dto.setClienteId(r.getCliente() != null ? r.getCliente().getId() : null);

        if (r.getIngredientes() != null && !r.getIngredientes().isEmpty()) {
            List<DTOIngredienteReceta> ingDtos = r.getIngredientes().stream().map(ir -> {
                DTOIngredienteReceta d = new DTOIngredienteReceta();
                d.setId(ir.getId());
                d.setCantidad(ir.getCantidad());
                d.setUnidad(ir.getUnidad());
                d.setIngredienteId(ir.getIngrediente() != null ? ir.getIngrediente().getId() : null);
                d.setIngredienteNombre(ir.getIngrediente() != null ? ir.getIngrediente().getNombre() : null);
                return d;
            }).toList();
            dto.setIngredientes(ingDtos);
        } else {
            dto.setIngredientes(List.of()); // lista vacía, no null
        }

        return dto;
    }

    private DTOIngredienteReceta mapIngredienteToDTO(IngredienteReceta ingrediente) {
        DTOIngredienteReceta dto = new DTOIngredienteReceta();
        dto.setId(ingrediente.getId());
        dto.setCantidad(ingrediente.getCantidad());
        dto.setUnidad(ingrediente.getUnidad());
        if (ingrediente.getIngrediente() != null) {
            dto.setIngredienteId(ingrediente.getIngrediente().getId());
            dto.setIngredienteNombre(ingrediente.getIngrediente().getNombre());
        }
        return dto;
    }
}
