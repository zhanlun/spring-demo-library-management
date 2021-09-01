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

import static io.github.zhanlun.librarymanagement.model.PagingUtil.getPageable;

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
        Pageable pageable = getPageable(allRequestParams);
        return subjectRepository.search(pageable);
    }

    public List<Subject> getSubjectsByIdList(Integer[] idList) {
        return subjectRepository.findAllById(List.of(idList));
    }
}
