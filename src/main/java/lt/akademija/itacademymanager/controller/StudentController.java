package lt.akademija.itacademymanager.controller;

import lombok.AllArgsConstructor;
import lt.akademija.itacademymanager.model.Student;
import lt.akademija.itacademymanager.payload.StudentNewRequest;
import lt.akademija.itacademymanager.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public Student addStudent(@RequestBody @Valid StudentNewRequest request) {
        return studentService.addStudent(request.toStudent());
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



}
