package io.github.zhanlun.librarymanagement.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects() {
        List<Subject> subjects = subjectService.getSubjects();
        return ResponseEntity.ok().body(subjects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable("id") Integer id) {
        Subject subject = subjectService.getSubject(id);
        return ResponseEntity.ok().body(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.addSubject(subject);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }
}
