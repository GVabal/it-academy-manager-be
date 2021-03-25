package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.request.StudentNewRequest;
import lt.akademija.itacademymanager.payload.response.ReviewResponse;
import lt.akademija.itacademymanager.service.ReviewService;
import lt.akademija.itacademymanager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;
    private final ReviewService reviewService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@RequestPart @Valid StudentNewRequest request,
                              @RequestPart(required = false) MultipartFile picture) {
        if (picture == null) {
            return studentService.addStudent(request);
        }
        return studentService.addStudentWithPicture(request, picture);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Student updateStudent(@RequestPart @Valid StudentNewRequest request, @PathVariable("id") Integer id,
                                 @RequestPart(required = false) MultipartFile picture) {
        if (picture == null) {
            return studentService.updateStudent(request, id);
        }
        return studentService.updateStudentWithPicture(request, id, picture);
    }

    @GetMapping
    @ResponseBody
    public List<Student> getStudentList() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("{id}/reviews")
    public List<ReviewResponse> getReviewsForStudent(@PathVariable int id) {
        return reviewService.getAllReviewsForStudent(id);
    }
}
