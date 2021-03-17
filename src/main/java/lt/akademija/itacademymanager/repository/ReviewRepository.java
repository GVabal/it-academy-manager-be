package lt.akademija.itacademymanager.repository;

import lt.akademija.itacademymanager.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
