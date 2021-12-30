package k17.example.readingbook.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.entity.Proposal;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.dto.PropPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements  BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BookDto getBookById(int id) {
        BookDto bookDto=new BookDto();
        Book book=bookRepository.findByBookId(id);
        List<Category> categories=categoryRepository.findAllBy();
        for(Category cate :categories) {
            if(book.getCateId()== cate.getCateId()) {
                bookDto = BookMapper.toBookAndCategory(book,cate);
                break;
            }
        }
        return bookDto;
    }



    @Override
    public List<BookDto> getAllBook() {
        List<BookDto> bookDtos=new ArrayList<>();
        List<Book> books=bookRepository.findAllBy();
        List<Category> categories=categoryRepository.findAllBy();
        for(Book book : books) {
            int id=book.getCateId();
            for(Category cate :categories) {
                if(id== cate.getCateId()) {
                    bookDtos.add(BookMapper.toBookAndCategory(book,cate));
                }
            }
        }
        return bookDtos;
    }

    @Override
    public BookDto updateBookById(ParamsUpdateBook param, int id) {
        BookDto bookDto=new BookDto();
        Book newBook=BookMapper.toBook(param);
        List<Category> categories=categoryRepository.findAllBy();
        for(Category cate :categories) {
            if(newBook.getCateId()== cate.getCateId()) {
                bookDto = BookMapper.toBookAndCategory(newBook,cate);
                bookRepository.save(newBook);
                break;
            }
        }
        return bookDto;
    }

    @Override
    public BookDto createBook(ParamsCreateBook param) {
        BookDto bookDto=new BookDto();
        Book newBook=BookMapper.toBookCreate(param);
        newBook.setNumberView(0);
        List<Category> categories=categoryRepository.findAllBy();
        for(Category cate :categories) {
            if(newBook.getCateId()== cate.getCateId()) {
                bookDto = BookMapper.toBookAndCategory(newBook,cate);
                bookDto.setNumberView(0);
                bookRepository.save(newBook);
                break;
            }
        }
        return bookDto;
    }

    @Override
    public void deleteBookById(int id) {
        Book book=bookRepository.findByBookId(id);
        bookRepository.delete(book);
    }

    @Override
    public void increaseView(int id) {
        Book book=bookRepository.findByBookId(id);
        book.setNumberView(book.getNumberView()+1);
        bookRepository.save(book);
    }

    @Override
    public BookPagingDto getAllBookPaging(int pageNumber) {
        int numberRowPerPage=5;
        List<Book> books=bookRepository.findAllBy();
        List<Category> categories=categoryRepository.findAllBy();
        List<BookDto> bookDtos=new ArrayList<>();
        for(Book book : books) {
            int id=book.getCateId();
            for(Category cate :categories) {
                if(id== cate.getCateId()) {
                    bookDtos.add(BookMapper.toBookAndCategory(book,cate));
                }
            }
        }
        int numberAllRow=books.size();
        int totalPage=numberAllRow/numberRowPerPage+1;
        BookPagingDto bookPagingDto=new BookPagingDto();
        bookPagingDto.setCurrentPage(pageNumber);
        bookPagingDto.setTotalPage(totalPage);
        bookPagingDto.setAllRow(numberAllRow);
        int endIndex=0;
        int startIndex=(pageNumber-1)*5;
        if(startIndex+5<=numberAllRow) {
            endIndex=startIndex+5;
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
    public BookPagingDto getAllBookBestViewerPaging(int pageNumber) {
        int numberRowPerPage=5;
        List<Book> books=bookRepository.findAllBy();
        Collections.sort(books, (o1, o2) -> o1.getNumberView() - o2.getNumberView());
        List<Category> categories=categoryRepository.findAllBy();
        List<BookDto> bookDtos=new ArrayList<>();
        for(Book book : books) {
            int id=book.getCateId();
            for(Category cate :categories) {
                if(id== cate.getCateId()) {
                    bookDtos.add(BookMapper.toBookAndCategory(book,cate));
                }
            }
        }
        int numberAllRow=books.size();
        int totalPage=numberAllRow/numberRowPerPage+1;
        BookPagingDto bookPagingDto=new BookPagingDto();
        bookPagingDto.setCurrentPage(pageNumber);
        bookPagingDto.setTotalPage(totalPage);
        bookPagingDto.setAllRow(numberAllRow);
        int endIndex=0;
        int startIndex=(pageNumber-1)*5;
        if(startIndex+5<=numberAllRow) {
            endIndex=startIndex+5;
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
}
