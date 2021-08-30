package io.github.zhanlun.librarymanagement.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Visitor getVisitor(Integer id) {
        Optional<Visitor> visitorOptional = visitorRepository.findById(id);
        if (visitorOptional.isEmpty()) {
            throw new IllegalStateException();
        }
        return visitorOptional.get();
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    public Visitor updateVisitor(Integer id, Visitor visitor) {
        if (!visitorRepository.existsById(id)) {
            throw new IllegalArgumentException();
        }
        visitor.setId(id);
        return visitorRepository.save(visitor);
    }
}
