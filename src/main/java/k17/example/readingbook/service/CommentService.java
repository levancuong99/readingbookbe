package k17.example.readingbook.service;

import k17.example.readingbook.entity.Comment;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.dto.CommentPagingDto;
import k17.example.readingbook.model.request.ParamComment;
import k17.example.readingbook.model.request.ParamsCreateComment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentPagingDto getAllComment(int bookId, int pageNumber);
    Comment createComment(ParamComment p);
}
