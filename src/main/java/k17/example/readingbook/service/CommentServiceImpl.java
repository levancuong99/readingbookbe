package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.entity.Comment;
import k17.example.readingbook.entity.Viewed;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.dto.CommentPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.model.request.ParamsCreateComment;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import k17.example.readingbook.repository.CommentRepository;
import k17.example.readingbook.repository.ViewedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements  CommentService{

    @Autowired
    private CommentRepository commentRepository;

    int numberRowPerPage=5;



    @Override
    public CommentPagingDto getAllComment( int bookId, int pageNumber) {
        List<Comment> comments= commentRepository.findAllBy();
        comments=comments.stream().filter(cmt -> cmt.getBookId()==bookId).collect(Collectors.toList());

        int numberAllRow=comments.size();

        int totalPage=numberAllRow/numberRowPerPage+1;
        CommentPagingDto commentPagingDto=new CommentPagingDto();
        commentPagingDto.setCurrentPage(pageNumber);
        commentPagingDto.setTotalPage(totalPage);
        commentPagingDto.setAllRow(numberAllRow);
        int endIndex=0;
        int startIndex=(pageNumber-1)*numberRowPerPage;
        if(startIndex+numberRowPerPage<=numberAllRow) {
            endIndex=startIndex+numberRowPerPage;
        }else {
            endIndex=numberAllRow;
        }
        List<Comment> cmt=new ArrayList<>();
        for(int i=startIndex; i<endIndex;i++) {
            cmt.add(comments.get(i));
        }
        commentPagingDto.setComments(cmt);
        commentPagingDto.setNumberRowCurrentpage(cmt.size());
        return commentPagingDto;

    }

    @Override
    public Comment createComment(Comment p) {
        Comment cmt= commentRepository.save(p);
        return cmt;
    }
}
