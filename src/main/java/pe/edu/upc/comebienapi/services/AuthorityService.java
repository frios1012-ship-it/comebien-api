package pe.edu.upc.comebienapi.services;

import pe.edu.upc.comebienapi.entities.Authority;

public interface AuthorityService {

    public Authority findById(Long id);
    public Authority findByName(String name);

    public Authority add(Authority authority);

}
