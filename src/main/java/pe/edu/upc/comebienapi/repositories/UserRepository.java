package pe.edu.upc.comebienapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.comebienapi.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);

}
