package k17.example.readingbook.controller;


import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.request.*;
import k17.example.readingbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class BookController
{
    @Autowired
    private BookService bookService;

    @GetMapping(value="/books")
    public ResponseEntity<?> getListBooks() {
        List<BookDto> books = bookService.getAllBook();
        return ResponseEntity.ok(books);
    }

    @GetMapping(value="books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable int id) {
        BookDto book =  bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok("delete success");
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBookById(@Validated @RequestBody ParamsUpdateBook req, @PathVariable int id) {
        BookDto book = bookService.updateBookById(req,id);
        return ResponseEntity.ok(book);
    }

    @PostMapping("/books/create")
    public ResponseEntity<?> createBook(@Validated @RequestBody ParamsCreateBook req) {
        BookDto book = bookService.createBook(req);
        return ResponseEntity.ok(book);
    }

    @PutMapping ("books/increaseview/{id}")
    public ResponseEntity<?> increaseView(@PathVariable int id) {
        bookService.increaseView(id);
        return ResponseEntity.ok("increase view success");
    }


}
