package lt.akademija.itacademymanager.repository;

import lt.akademija.itacademymanager.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByStudentId(int id);
}
