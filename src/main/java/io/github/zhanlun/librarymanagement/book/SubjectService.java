package io.github.zhanlun.librarymanagement.book;

import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubject(Integer id) throws NotFoundException {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isEmpty()) {
            throw NotFoundException.createWith("Subject with id " + id + " not found");
        }
        return subjectOptional.get();
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Page<Subject> getSubjects(Map<String, String> allRequestParams) {
        int start = Integer.parseInt(allRequestParams.get("_start"));
        int end = Integer.parseInt(allRequestParams.get("_end"));
        int pageSize = end - start;
        int pageStart = start / pageSize;
        String sort = allRequestParams.get("_sort");
        boolean isDesc = allRequestParams.get("_order").equalsIgnoreCase("DESC");
        if (sort == null) {
            sort = "name";
        }
        Sort sortBy = Sort.by(sort);
        sortBy = isDesc ? sortBy.descending() : sortBy.ascending();

        Pageable pageable = PageRequest.of(pageStart, pageSize, sortBy);

        return subjectRepository.search(pageable);
    }

    public List<Subject> getSubjectsByIdList(Integer[] idList) {
        return subjectRepository.findAllById(List.of(idList));
    }
}
