package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.entity.Viewed;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import k17.example.readingbook.repository.ViewedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewedServiceImpl implements  ViewedService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ViewedRepository viewedRepository;
    int numberRowPerPage=1;

    @Override
    public BookPagingDto getAllBookViewedByUser(int userId, int pageNumber) {
        List<Viewed> viewedList=viewedRepository.findAllBy();
        List<Book> books=bookRepository.findAllBy();

        List<Integer> idBooks=new ArrayList<>();
        for(Viewed v : viewedList) {
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
        if(startIndex+1<=numberAllRow) {
            endIndex=startIndex+numberRowPerPage;
        }else {
            endIndex=numberAllRow;
        }

        List<BookDto> books1=new ArrayList<>();
        for(int i=startIndex; i<endIndex;i++) {
            books1.add(bookDtos.get(i));
        }
        bookPagingDto.setBooks(books1);
        bookPagingDto.setNumberRowCurrentpage(books1.size());
        return bookPagingDto;
    }

    @Override
    public void addViewed(int idUser, int idBook) {
        Viewed v=new Viewed();
        v.setUserId(idUser);
        v.setBookId(idBook);
        viewedRepository.save(v);
    }
}
