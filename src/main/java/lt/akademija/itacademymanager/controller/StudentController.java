package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student addStudent(@RequestBody @Valid StudentNewRequest request) {
        return studentService.addStudent(request.toStudent());
    }
}
