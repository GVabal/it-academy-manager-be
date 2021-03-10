package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.repository.StudentRepository;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }
}
