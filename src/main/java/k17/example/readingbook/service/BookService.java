package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    BookDto getBookById(int id);
    List<BookDto> getAllBook();
    BookDto updateBookById(ParamsUpdateBook param,int id);
    BookDto createBook(ParamsCreateBook param);
    void deleteBookById(int id);
    void increaseView(int id);
}
