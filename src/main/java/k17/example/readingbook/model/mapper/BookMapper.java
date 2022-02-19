package k17.example.readingbook.model.mapper;
import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;

import java.util.Calendar;
import java.util.Date;

public class BookMapper {
    public static BookDto toBookAndCategory (Book b, Category c) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(b.getBookId());
        bookDto.setBookName(b.getBookName());
        bookDto.setDescription(b.getDescription());
        bookDto.setImgBook(b.getImgBook());
        bookDto.setLinkBook(b.getLinkBook());
        bookDto.setNumberView(b.getNumberView());
        bookDto.setNumberLike(b.getNumberLike());
        bookDto.setAuthorName(b.getAuthorName());
        bookDto.setAuthorProfile(b.getAuthorProfile());
        bookDto.setCateName(c.getCateName());
        bookDto.setCateId(c.getCateId());
        bookDto.setCreatedAt(b.getCreatedAt());
        return bookDto;
    }
    public static Book toBook (ParamsUpdateBook b) {
        Book book = new Book();
        book.setBookId(b.getBookId());
        book.setBookName(b.getBookName());
        book.setDescription(b.getDescription());
        book.setImgBook(b.getImgBook());
        book.setLinkBook(b.getLinkBook());
        book.setAuthorName(b.getAuthorName());
        book.setAuthorProfile(b.getAuthorProfile());
        book.setCateId(b.getCateId());
        return book;
    }

    public static Book toBookCreate (ParamsCreateBook b) {
        Book book = new Book();
        book.setBookId(b.getBookId());
        book.setBookName(b.getBookName());
        book.setDescription(b.getDescription());
        book.setImgBook(b.getImgBook());
        book.setLinkBook(b.getLinkBook());
        book.setAuthorName(b.getAuthorName());
        book.setAuthorProfile(b.getAuthorProfile());
        book.setCateId(b.getCateId());
        book.setCreatedAt(new Date());
        return book;
    }

}
