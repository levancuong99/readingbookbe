package k17.example.readingbook.model.dto;

import k17.example.readingbook.entity.Book;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookPagingDto {
    List<BookDto> books;
    int totalPage;
    int currentPage;
    int numberRowCurrentpage;
    int allRow;
}
