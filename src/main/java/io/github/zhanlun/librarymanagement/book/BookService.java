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
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> getBooks(Map<String, String> allRequestParams) {
        int start = Integer.parseInt(allRequestParams.get("_start"));
        int end = Integer.parseInt(allRequestParams.get("_end"));
        int pageSize = end - start;
        int pageStart = start / pageSize;
        String sort = allRequestParams.get("_sort");
        boolean isDesc = allRequestParams.get("_order").equalsIgnoreCase("DESC");
        if (sort == null) {
            sort = "name";
        }
        if (sort.contains(".")) {
            sort = sort.replace("id", "name");
        }
        Sort sortBy = Sort.by(sort);
        sortBy = isDesc ? sortBy.descending() : sortBy.ascending();

        Pageable pageable = PageRequest.of(pageStart, pageSize, sortBy);

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

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Integer id, Book book) throws NotFoundException {
        if (!bookRepository.existsById(id)) {
            throw NotFoundException.createWith("Book with id " + id + " not found");
        }
        book.setId(id);
        return bookRepository.save(book);
    }

    public List<Book> getBooksByIdList(Integer[] idList) {
        return bookRepository.findAllById(List.of(idList));
    }

//    public Page<Book> getBooks(Integer[] range, String[] sort, Map<String, Object> filter) {
//        int start = 0, end = 10;
//        if (range != null) {
//            start = range[0];
//            end = range[1];
//        }
//        int pageSize = end - start;
//        int pageStart = start / pageSize;
//
//        Sort sortBy;
//        Pageable pageable;
//        if (sort == null) {
//            pageable = PageRequest.of(pageStart, pageSize);
//        } else {
//            String sortKey = sort[0];
//            boolean isDesc = sort[1].toUpperCase().equals("DESC");
//            if (sortKey.contains(".")) {
//                sortKey = sortKey.replace("id", "name");
//            }
//            sortBy = Sort.by(sortKey);
//            pageable = PageRequest.of(pageStart, pageSize, sortBy);
//            sortBy = isDesc ? sortBy.descending() : sortBy.ascending();
//        }
//
//        // filters
//
//        String name = null;
//        Integer subjectId = null;
//        if (filter != null) {
//            Map<String, String> filterMap =
//            Object nameObject = filter.get("name");
//            name = nameObject == null ? null : nameObject.toString();
//
//            Object subjectIdObject = filter.get("subject.id");
//            String subjectIdString = subjectIdObject == null ? null : subjectIdObject.toString();
//            subjectId = subjectIdString == null ? null : Integer.parseInt(subjectIdString);
//        }
//
//        return bookRepository.search(pageable, name, subjectId);
//    }
}
