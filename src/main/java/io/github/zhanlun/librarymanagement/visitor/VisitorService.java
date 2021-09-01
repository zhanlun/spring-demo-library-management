package io.github.zhanlun.librarymanagement.visitor;

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
public class VisitorService {
    private final VisitorRepository visitorRepository;

    @Autowired
    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public List<Visitor> getVisitors() {
        return visitorRepository.findAll();
    }

    public Visitor getVisitor(Integer id) throws NotFoundException {
        Optional<Visitor> visitorOptional = visitorRepository.findById(id);
        if (visitorOptional.isEmpty()) {
            throw NotFoundException.createWith("Visitor with id " + id + " not found");
        }
        return visitorOptional.get();
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor updateVisitor(Integer id, Visitor visitor) throws NotFoundException {
        if (!visitorRepository.existsById(id)) {
            throw NotFoundException.createWith("Visitor with id " + id + " not found");
        }
        visitor.setId(id);
        return visitorRepository.save(visitor);
    }

    public Page<Visitor> getVisitors(Map<String, String> allRequestParams) {
        Pageable pageable = getPageable(allRequestParams);

        // filters
        String name = allRequestParams.get("name");

        return visitorRepository.search(pageable, name);
    }
//
//    private Pageable getPageable(Map<String, String> allRequestParams) {
//        String _start = allRequestParams.get("_start");
//        String _end = allRequestParams.get("_end");
//        int start = _start == null ? 0 : Integer.parseInt(_start);
//        int end = _end == null ? 10 : Integer.parseInt(_end);
//        int pageSize = end - start;
//        int pageStart = start / pageSize;
//        String _sort = allRequestParams.get("_sort");
//        String _order = allRequestParams.get("_order");
//        boolean isDesc = _order != null && _order.equalsIgnoreCase("DESC");
//        _sort = _sort == null ? "id" : _sort;
//        Sort sortBy = Sort.by(_sort);
//        sortBy = isDesc ? sortBy.descending() : sortBy.ascending();
//        Pageable pageable = PageRequest.of(pageStart, pageSize, sortBy);
//        return pageable;
//    }

    public List<Visitor> getVisitorsByIdList(Integer[] idList) {
        return visitorRepository.findAllById(List.of(idList));
    }
}
