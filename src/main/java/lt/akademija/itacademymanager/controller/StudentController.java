package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public Student addStudent(@RequestPart @Valid StudentNewRequest request, @RequestPart byte[] picture) {
        // čia patikrinti ar picture existuoja?
        return studentService.addStudent(request, picture);
    }

    @PutMapping(path = "/{id}")
    public Student updatePerson(@RequestBody @Valid StudentNewRequest request, @PathVariable("id") Integer id) {
        return studentService.updateStudent(request.toStudent(), id);
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

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudentById(id);
    }
}
