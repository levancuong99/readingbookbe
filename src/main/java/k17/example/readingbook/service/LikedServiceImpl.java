package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.entity.Liked;
import k17.example.readingbook.entity.Viewed;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import k17.example.readingbook.repository.LikedRepository;
import k17.example.readingbook.repository.ViewedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikedServiceImpl implements  LikedService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LikedRepository likedRepository;

    int numberRowPerPage=3;
    @Override
    public BookPagingDto getAllBookLikedByUser(int userId, int pageNumber) {
        List<Liked> likedList=likedRepository.findAllBy();
        List<Book> books=bookRepository.findAllBy();

        List<Integer> idBooks=new ArrayList<>();
        for(Liked v : likedList) {
            if(v.getUserId()==userId) {
                idBooks.add(v.getBookId());
            }
        }

        List<Book> bookList =new ArrayList<>();
        for(Integer id : idBooks) {
            for(Book b : books) {
                if(id==b.getBookId()) {
                    bookList.add(b);
                }
            }
        }

        List<BookDto> bookDtos=new ArrayList<>();
        List<Category> categories=categoryRepository.findAllBy();
        for(Book book : bookList) {
            int id=book.getCateId();
            for(Category cate :categories) {
                if(id== cate.getCateId()) {
                    bookDtos.add(BookMapper.toBookAndCategory(book,cate));
                }
            }
        }


        int numberAllRow=bookList.size();
        int totalPage=numberAllRow/numberRowPerPage+1;
        BookPagingDto bookPagingDto=new BookPagingDto();
        bookPagingDto.setCurrentPage(pageNumber);
        bookPagingDto.setTotalPage(totalPage);
        bookPagingDto.setAllRow(numberAllRow);
        int endIndex=0;
        int startIndex=(pageNumber-1)*numberRowPerPage;
        if(startIndex+numberRowPerPage<=numberAllRow) {
            endIndex=startIndex+numberRowPerPage;
        }else {
            endIndex=numberAllRow;
        }

        List<BookDto> books1=new ArrayList<>();
        for(int i=startIndex; i<endIndex;i++) {
            books1.add(bookDtos.get(i));
        }
        bookPagingDto.setBooks(books1);
        bookPagingDto.setNumberRowCurrentpage(numberRowPerPage);
        return bookPagingDto;
    }

    @Override
    public void addLiked(int userId, int bookId) {
        Liked l=new Liked();
        l.setUserId(userId);
        l.setBookId(bookId);
        likedRepository.save(l);
    }

    @Override
    public Boolean isLikedByUser(int userId, int bookId) {
        List<Liked> likedList=likedRepository.findAllBy();
        for(Liked l : likedList) {
            if(l.getUserId()==userId && l.getBookId()==bookId) {
                return true;
            }
        }
        return false;
    }


}
