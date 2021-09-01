package io.github.zhanlun.librarymanagement.visitor;

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

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @GetMapping
    public ResponseEntity<List<Visitor>> getVisitors(@RequestParam Map<String, String> allRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        List<Visitor> results;
        long total;
        Page<Visitor> pageResult = visitorService.getVisitors(allRequestParams);
        results = pageResult.toList();
        total = pageResult.getTotalElements();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    @GetMapping(params = {"id"})
    public ResponseEntity<List<Visitor>> getVisitorsById(@RequestParam(name = "id", required = false) Integer[] idList) {
        HttpHeaders headers = new HttpHeaders();
        List<Visitor> results;
        long total;
        List<Visitor> visitorsByIdList = visitorService.getVisitorsByIdList(idList);
        results = visitorsByIdList;
        total = visitorsByIdList.size();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitor(@PathVariable("id") Integer id) throws NotFoundException {
        Visitor visitor = visitorService.getVisitor(id);
        return ResponseEntity.ok().body(visitor);
    }

    @PostMapping
    public ResponseEntity<Visitor> addVisitor(@Valid @RequestBody Visitor visitor) {
        Visitor createdVisitor = visitorService.addVisitor(visitor);
        return new ResponseEntity<>(createdVisitor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitor> updateVisitor(@Valid @RequestBody Visitor visitor, @PathVariable("id") Integer id) throws NotFoundException {
        Visitor createdVisitor = visitorService.updateVisitor(id, visitor);
        return ResponseEntity.ok().body(createdVisitor);
    }
}
