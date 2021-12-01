package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
