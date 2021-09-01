package io.github.zhanlun.librarymanagement.book;

import io.github.zhanlun.librarymanagement.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(@RequestParam Map<String, String> allRequestParams) {
        HttpHeaders headers = new HttpHeaders();
        List<Book> results;
        long total;
        Page<Book> pageResult = bookService.getBooks(allRequestParams);
        results = pageResult.toList();
        total = pageResult.getTotalElements();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    /**
     * Get many by id
     *
     * @param idList in the form of ?id=1&id=2&id=99
     */
    @GetMapping(params = {"id"})
    public ResponseEntity<List<Book>> getBooksById(@RequestParam(name = "id", required = false) Integer[] idList) {
        HttpHeaders headers = new HttpHeaders();
        List<Book> results;
        long total;
        List<Book> booksByIdList = bookService.getBooksByIdList(idList);
        results = booksByIdList;
        total = booksByIdList.size();
        headers.add("X-Total-Count", String.valueOf(total));
        headers.add("Access-Control-Expose-Headers", "X-Total-Count");
        return ResponseEntity.ok().headers(headers).body(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") Integer id) throws NotFoundException {
        Book book = bookService.getBook(id);
        return ResponseEntity.ok().body(book);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") Integer id) throws NotFoundException {
        Book createdBook = bookService.updateBook(id, book);
        return ResponseEntity.ok().body(book);
    }
}
