package io.github.zhanlun.librarymanagement.book;

import io.github.zhanlun.librarymanagement.exception.BadRequestException;
import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.github.zhanlun.librarymanagement.model.PagingUtil.getPageable;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public BookService(BookRepository bookRepository, SubjectRepository subjectRepository) {
        this.bookRepository = bookRepository;
        this.subjectRepository = subjectRepository;
    }

    public Page<Book> getBooks(Map<String, String> allRequestParams) {
        Pageable pageable = getPageable(allRequestParams);

        // filters
        String name = allRequestParams.get("name");
        String subjectIdString = allRequestParams.get("subject.id");
        Integer subjectId = subjectIdString == null ? null : Integer.parseInt(subjectIdString);
        return bookRepository.search(pageable, name, subjectId);
    }

    public Book getBook(Integer id) throws NotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty()) {
            throw NotFoundException.createWith("Book with id " + id + " not found");
        }
        return bookOptional.get();
    }

    public Book addBook(Book book) throws BadRequestException {
        if (book.getSubject() == null || !subjectRepository.existsById(book.getSubject().getId())) {
            throw BadRequestException.createWith("Subject is not valid");
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, Book book) throws NotFoundException, BadRequestException {
        if (!bookRepository.existsById(id)) {
            throw NotFoundException.createWith("Book with id " + id + " not found");
        }
        if (book.getSubject() == null || !subjectRepository.existsById(book.getSubject().getId())) {
            throw BadRequestException.createWith("Subject is not valid");
        }
        book.setId(id);
        book.setCheckouts(null);
        return bookRepository.save(book);
    }

    public List<Book> getBooksByIdList(Integer[] idList) {
        return bookRepository.findAllById(List.of(idList));
    }
}
