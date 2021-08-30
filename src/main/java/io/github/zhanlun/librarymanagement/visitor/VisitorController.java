package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public ResponseEntity<List<Visitor>> getVisitors() {
        List<Visitor> visitors = visitorService.getVisitors();
        return ResponseEntity.ok().body(visitors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable("id") Integer id) {
        Visitor visitor = visitorService.getVisitor(id);
        return ResponseEntity.ok().body(visitor);
    }

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@RequestBody Visitor visitor) {
        Visitor createdVisitor = visitorService.addVisitor(visitor);
        return new ResponseEntity<>(createdVisitor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(@RequestBody Visitor visitor, @PathVariable("id") Integer id) {
        Visitor createdVisitor = visitorService.updateVisitor(id, visitor);
        return ResponseEntity.ok().body(createdVisitor);
    }
}
