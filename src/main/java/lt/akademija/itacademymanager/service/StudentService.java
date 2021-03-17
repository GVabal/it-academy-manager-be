package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.StudentNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfilePictureService profilePictureService;

    // transactional?
    public Student addStudent(StudentNewRequest student, byte[] picture) {
        ProfilePicture profilePicture = null;
        try {
            profilePicture = profilePictureService.storePicture(picture);
        } catch (IOException e) {}

        Student s = student.toStudent();

        s.setPictureUrl("http://localhost:8080/api/profile-pictures/" + profilePicture.getId());
        return studentRepository.save(s);
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
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
    }

    public void deleteStudentById(int id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}
