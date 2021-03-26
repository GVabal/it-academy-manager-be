package lt.akademija.itacademymanager.repository;


import lt.akademija.itacademymanager.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {

    boolean existsByEmail(String email);

    Optional<ApplicationUser> findByEmail(String emailAddress);
}
