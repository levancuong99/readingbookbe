package k17.example.readingbook.controller;

import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class ViewedController
{
    @Autowired
    private ViewedService viewedService;

    @GetMapping(value="/books/viewed/{idUser}/{pageNumber}")
    public ResponseEntity<?> getListBookViewedPaging(@PathVariable int idUser,@PathVariable int pageNumber) {
        BookPagingDto bookPagingDto = viewedService.getAllBookViewedByUser(idUser,pageNumber);
        return ResponseEntity.ok(bookPagingDto);
    }
    @PostMapping(value="/books/viewed/create/{idUser}/{bookId}")
    public ResponseEntity<?> addViewed(@PathVariable int idUser,@PathVariable int bookId) {
         viewedService.addViewed(idUser,bookId);
        return ResponseEntity.ok("success");
    }


}
