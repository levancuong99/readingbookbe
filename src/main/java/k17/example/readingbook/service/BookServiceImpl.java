package k17.example.readingbook.service;

import k17.example.readingbook.entity.Book;
import k17.example.readingbook.entity.Category;
import k17.example.readingbook.entity.Post;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.dto.BookPagingDto;
import k17.example.readingbook.model.mapper.BookMapper;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsUpdateBook;
import k17.example.readingbook.repository.BookRepository;
import k17.example.readingbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements  BookService{
    private static int numberRowPerPage=6;

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
        Book newBook=bookRepository.findByBookId(id);
        BookDto bookDto=new BookDto();
        newBook.setBookName(param.getBookName());
        newBook.setDescription(param.getDescription());
        newBook.setImgBook(param.getImgBook());
        newBook.setLinkBook(param.getLinkBook());
        newBook.setAuthorName(param.getAuthorName());
        newBook.setAuthorProfile(param.getAuthorProfile());
        newBook.setCateId(param.getCateId());
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
    public void increaseLike(int id) {
        Book book=bookRepository.findByBookId(id);
        book.setNumberLike(book.getNumberLike()+1);
        bookRepository.save(book);
    }

    @Override
    public BookPagingDto getAllBookPaging(int pageNumber) {
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
        bookPagingDto.setNumberRowCurrentpage(books1.size());
        return bookPagingDto;
    }

    @Override
    public BookPagingDto getAllBookBestViewerPaging(int pageNumber) {
        List<Book> books=bookRepository.findAllBy();
        Collections.sort(books, Comparator.comparingInt(Book::getNumberView).reversed());
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
        bookPagingDto.setNumberRowCurrentpage(books1.size());
        return bookPagingDto;
    }

    @Override
    public BookPagingDto getAllBookBestLikerPaging(int pageNumber) {
        List<Book> books=bookRepository.findAllBy();
        Collections.sort(books, Comparator.comparingInt(Book::getNumberLike).reversed());
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
        bookPagingDto.setNumberRowCurrentpage(books1.size());
        return bookPagingDto;
    }


    @Override
    public BookPagingDto getAllBookSearch(int cateId,String string, int pageNumber) {
        if(string.equals("$")) {

            string="";
            System.out.println("dava:"+string);
        };

        List<Book> books=bookRepository.findAllBy();
        String finalString = string;
        System.out.println(finalString);
        books=books.stream().filter(b -> b.getBookName().toLowerCase().contains(finalString.toLowerCase()) || b.getAuthorName().toLowerCase().contains(finalString.toLowerCase())).collect(Collectors.toList());
        List<Category> categories=categoryRepository.findAllBy();
        List<BookDto> bookDtos=new ArrayList<>();
        for(Book book : books) {
            int id=book.getCateId();
            for(Category cate :categories) {
                if(cateId!=0) {
                    if (id == cate.getCateId() && id == cateId) {
                        bookDtos.add(BookMapper.toBookAndCategory(book, cate));
                    }
                }else {
                    if (id == cate.getCateId()) {
                        bookDtos.add(BookMapper.toBookAndCategory(book, cate));
                    }
                }
            }
        }
        int numberAllRow=bookDtos.size();
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
        bookPagingDto.setNumberRowCurrentpage(books1.size());
        return bookPagingDto;
    }
}
