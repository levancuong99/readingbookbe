package k17.example.readingbook.controller;

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


}
