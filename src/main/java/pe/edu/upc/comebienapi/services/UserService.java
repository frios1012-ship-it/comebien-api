package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.dtos.DTOUser;
import pe.edu.upc.comebienapi.entities.User;

public interface  UserService {

    public User findById(Long id);
    public User findByUsername(String username);
    public DTOUser add(DTOUser userDTO);

}

