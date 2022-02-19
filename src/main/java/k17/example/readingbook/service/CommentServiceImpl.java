package k17.example.readingbook.service;

import k17.example.readingbook.entity.*;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.dto.CommentPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.model.request.ParamComment;
import k17.example.readingbook.model.request.ParamsCreateComment;
import k17.example.readingbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements  CommentService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentRepository commentRepository;

    int numberRowPerPage=5;



    @Override
    public CommentPagingDto getAllComment( int bookId, int pageNumber) {
        List<Comment> comments= commentRepository.findAllBy();
        comments=comments.stream().filter(cmt -> cmt.getBookId()==bookId).sorted(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o2.getCreatedAt().compareTo(o1.getCreatedAt());
            }
        }).collect(Collectors.toList());

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
        commentPagingDto.setNumberRowCurrentpage(numberRowPerPage);
        return commentPagingDto;

    }

    @Override
    public Comment createComment(ParamComment p) {
        User user=userRepository.findByUserId(p.getUserId());
        Comment cmt=new Comment();
        cmt.setBookId(p.getBookId());
        cmt.setUserId(p.getUserId());
        cmt.setContent(p.getContent());
        cmt.setCreatedAt(new Date());
        cmt.setImgAvt(user.getImg_avt());
        commentRepository.save(cmt);
        return cmt;
    }
}
