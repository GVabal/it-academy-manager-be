package lt.akademija.itacademymanager.service;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.exception.StudentNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ProfilePictureService profilePictureService;

    public Student addStudent(StudentNewRequest request) {
        Student student = new Student(
                request.getFirstName(),
                request.getLastName(),
                null,
                request.getOccupation(),
                request.getDirection()
        );
        return studentRepository.save(student);
    }

    @Transactional
    public Student addStudentWithPicture(StudentNewRequest request, MultipartFile picture) {
        ProfilePicture profilePicture = null;
        try {
            profilePicture = profilePictureService.storePicture(picture);
        } catch (IOException e) {
            // how to handle this gracefully?
        }
        Student student = new Student(
                request.getFirstName(),
                request.getLastName(),
                "http://localhost:8080/api/profile-pictures/" + profilePicture.getId(),
                request.getOccupation(),
                request.getDirection()
        );
        return studentRepository.save(student);
    }

    public Student updateStudent(StudentNewRequest request, Integer id){
            Student updatedStudent = getStudentById(id);
            // update fetched student with request data
            return studentRepository.save(updatedStudent);
    }
    public List<Student> getAllStudents(){return studentRepository.findAll();}

    public Student getStudentById(int id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
    }

    @Transactional
    public void deleteStudentById(int id) {
        Student student = getStudentById(id);
        if (profilePictureService.existsById(student.getPictureId())) {
            profilePictureService.deletePicture(student.getPictureId());
        }
        studentRepository.delete(student);
    }
}
