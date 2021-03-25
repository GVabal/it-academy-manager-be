package lt.akademija.itacademymanager.repository;


import lt.akademija.itacademymanager.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ApplicationUser, Integer> {
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    ApplicationUser findByEmail(String emailAddress);
}
