package k17.example.readingbook.controller;

import k17.example.readingbook.entity.Comment;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.dto.CommentPagingDto;
import k17.example.readingbook.model.request.ParamComment;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsCreateComment;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import k17.example.readingbook.service.CommentService;
import k17.example.readingbook.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @GetMapping(value="/comments/{bookId}/{pageNumber}")
    public ResponseEntity<?> getListCommentPaging(@PathVariable int bookId,@PathVariable int pageNumber) {
        CommentPagingDto commentPagingDto = commentService.getAllComment(bookId,pageNumber);
        return ResponseEntity.ok(commentPagingDto);
    }

    @PostMapping("comments/create")
    public ResponseEntity<?> createComment(@Validated @RequestBody ParamComment req) {
        Comment comment = commentService.createComment(req);
        return ResponseEntity.ok(comment);
    }
    @DeleteMapping("comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable int id) {
        commentService.deleteCommentByIdUser(id);
        return ResponseEntity.ok("delete success");
    }

    @PutMapping("comments/{id}")
    public ResponseEntity<?> updateComment(@PathVariable int id,@Validated @RequestBody ParamComment req) {
        Comment comment = commentService.updateCommentById(id,req);
        return ResponseEntity.ok(comment);
    }


}
