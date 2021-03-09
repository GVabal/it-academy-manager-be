package lt.akademija.itacademymanager.repository;

import lt.akademija.itacademymanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
