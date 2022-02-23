package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookDto getBookById(int id);
    BookPagingDto getAllBookSearch(int cateId,String string,int pageNumber);
    List<BookDto> getAllBook();
    BookDto updateBookById(ParamsUpdateBook param,int id);
    BookDto createBook(ParamsCreateBook param);
    void deleteBookById(int id);
    void increaseView(int id);
    void increaseLike(int id);
    void decreaseLike(int id);
    BookPagingDto getAllBookNewestPaging(int pageNumber);
    BookPagingDto getAllBookPaging(int id);
    BookPagingDto getAllBookBestViewerPaging(int id);
    BookPagingDto getAllBookBestLikerPaging(int id);
}
