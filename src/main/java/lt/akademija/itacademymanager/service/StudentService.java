package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.StudentNotFoundException;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);

    }

    public Student updateStudent(Student newStudent, Integer id){
        if(studentRepository.existsById(id)){
            newStudent.setId(id);
            return studentRepository.save(newStudent);
        }
        throw new StudentNotFoundException("Student does not exist");
    }
    public List<Student> getAllStudents(){return studentRepository.findAll();}

    public Student getStudentById(int id){
        return (Student) this.studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
    }

}
