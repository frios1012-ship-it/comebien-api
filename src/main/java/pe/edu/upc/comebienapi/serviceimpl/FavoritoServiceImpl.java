package pe.edu.upc.comebienapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.comebienapi.dtos.DTOFavorito;
import pe.edu.upc.comebienapi.entities.Favorito;
import pe.edu.upc.comebienapi.repositories.ClienteRepository;
import pe.edu.upc.comebienapi.repositories.ListaRepository;
import pe.edu.upc.comebienapi.repositories.FavoritoRepository;
import pe.edu.upc.comebienapi.services.FavoritoService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ListaRepository listaRepository;

    @Override
    @Transactional
    public DTOFavorito add(DTOFavorito dto) {

        Favorito f = new Favorito();
        f.setNotas(dto.getNotas());
        f.setCliente(clienteRepository.findById(dto.getClienteId()).orElse(null));
        f.setLista(listaRepository.findById(dto.getListaId()).orElse(null));

        // 🔥 Fecha automática siempre
        f.setFechaGuardado(LocalDate.now());

        f = favoritoRepository.save(f);
        return mapToDTO(f);
    }

    @Override
    @Transactional
    public DTOFavorito update(Long id, DTOFavorito dto) {

        Favorito f = favoritoRepository.findById(id).orElse(null);
        if (f == null) return null;

        f.setNotas(dto.getNotas());
        f.setCliente(clienteRepository.findById(dto.getClienteId()).orElse(null));
        f.setLista(listaRepository.findById(dto.getListaId()).orElse(null));

        // ❌ No cambiamos la fechaGuardado
        // f.setFechaGuardado(...)

        favoritoRepository.save(f);
        return mapToDTO(f);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        favoritoRepository.deleteById(id);
    }

    @Override
    public List<DTOFavorito> listAll() {
        return favoritoRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private DTOFavorito mapToDTO(Favorito f) {
        DTOFavorito dto = new DTOFavorito();
        dto.setId(f.getId());
        dto.setFechaGuardado(f.getFechaGuardado());
        dto.setNotas(f.getNotas());
        dto.setClienteId(f.getCliente() != null ? f.getCliente().getId() : null);
        dto.setClienteNombre(f.getCliente() != null ? f.getCliente().getNombre() : null);
        dto.setListaId(f.getLista() != null ? f.getLista().getId() : null);
        dto.setListaNombre(f.getLista() != null ? f.getLista().getNombre() : null);
        return dto;
    }
}
