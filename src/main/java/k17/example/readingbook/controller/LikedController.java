package k17.example.readingbook.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.service.LikedService;
import k17.example.readingbook.service.ViewedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class LikedController
{
    @Autowired
    private LikedService likedService;

    @GetMapping(value="/books/liked/{idUser}/{pageNumber}")
    public ResponseEntity<?> getListBookLikedPaging(@PathVariable int idUser,@PathVariable int pageNumber) {
        BookPagingDto bookPagingDto = likedService.getAllBookLikedByUser(idUser,pageNumber);
        return ResponseEntity.ok(bookPagingDto);
    }
    @PostMapping(value="/books/liked/create/{idUser}/{bookId}")
    public ResponseEntity<?> addLiked(@PathVariable int idUser,@PathVariable int bookId) {
        likedService.addLiked(idUser,bookId);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping(value="/books/liked/delete/{idUser}/{bookId}")
    public ResponseEntity<?> deleteLiked(@PathVariable int idUser,@PathVariable int bookId) {
        likedService.deleteLiked(idUser,bookId);
        return ResponseEntity.ok("success");
    }

    @GetMapping(value="/books/liked/check/{idUser}/{bookId}")
    public ResponseEntity<?> isLiked(@PathVariable int idUser,@PathVariable int bookId) {
        Boolean isLiked = likedService.isLikedByUser(idUser,bookId);
        return ResponseEntity.ok(isLiked);
    }


}
