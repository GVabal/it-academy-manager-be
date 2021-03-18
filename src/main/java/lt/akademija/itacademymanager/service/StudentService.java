package lt.akademija.itacademymanager.service;

import lombok.RequiredArgsConstructor;
import lt.akademija.itacademymanager.exception.StudentNotFoundException;
import lt.akademija.itacademymanager.model.ProfilePicture;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentService {
    @Value("${environment.address}")
    private String address;

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
        ProfilePicture profilePicture = profilePictureService.storePicture(picture);
        Student student = new Student(
                request.getFirstName(),
                request.getLastName(),
                generatePictureUrl(profilePicture.getId()),
                request.getOccupation(),
                request.getDirection()
        );
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(StudentNewRequest request, Integer id){
        Student oldStudent = getStudentById(id);
        if (oldStudent.extractPictureId() != 0) {
            profilePictureService.deletePicture(oldStudent.extractPictureId());
        }
        Student updatedStudent = new Student(
                request.getFirstName(),
                request.getLastName(),
                null,
                request.getOccupation(),
                request.getDirection()
        );
        updatedStudent.setId(id);
        return studentRepository.save(updatedStudent);
    }

    @Transactional
    public Student updateStudentWithPicture(StudentNewRequest request, int id, MultipartFile picture) {
        ProfilePicture newProfilePicture = profilePictureService.storePicture(picture);
        Student oldStudent = getStudentById(id);
        if (oldStudent.extractPictureId() != 0) {
            profilePictureService.deletePicture(oldStudent.extractPictureId());
        }
        Student updatedStudent = new Student(
                request.getFirstName(),
                request.getLastName(),
                generatePictureUrl(newProfilePicture.getId()),
                request.getOccupation(),
                request.getDirection()
        );
        updatedStudent.setId(id);
        return studentRepository.save(updatedStudent);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " does not exist"));
    }

    @Transactional
    public void deleteStudentById(int id) {
        Student student = getStudentById(id);
        if (profilePictureService.existsById(student.extractPictureId())) {
            profilePictureService.deletePicture(student.extractPictureId());
        }
        studentRepository.delete(student);
    }

    private String generatePictureUrl(int id) {
        return address + "/api/profile-pictures/" + id;
    }
}
