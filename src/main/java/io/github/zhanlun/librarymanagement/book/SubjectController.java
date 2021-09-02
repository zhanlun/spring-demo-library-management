package io.github.zhanlun.librarymanagement.book;

import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getSubjects(@RequestParam Map<String, String> allRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        List<Subject> results;
        long total;
        Page<Subject> pageResult = subjectService.getSubjects(allRequestParams);
        results = pageResult.toList();
        total = pageResult.getTotalElements();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results.stream()
                .distinct()
                .collect(Collectors.toList()));
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<List<Subject>> getSubjectsById(@RequestParam(name = "id", required = false) Integer[] idList) {
        HttpHeaders headers = new HttpHeaders();
        List<Subject> results;
        long total;
        List<Subject> subjectsByIdList = subjectService.getSubjectsByIdList(idList);
        results = subjectsByIdList;
        total = subjectsByIdList.size();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable("id") Integer id) throws NotFoundException {
        Subject subject = subjectService.getSubject(id);
        return ResponseEntity.ok().body(subject);
    }

    @PostMapping
    public ResponseEntity<Subject> addSubject(@Valid @RequestBody Subject subject) {
        Subject createdSubject = subjectService.addSubject(subject);
        return new ResponseEntity<>(createdSubject, HttpStatus.CREATED);
    }
}
