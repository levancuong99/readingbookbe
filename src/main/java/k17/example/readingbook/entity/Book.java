package k17.example.readingbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bookId;
    private String bookName;
    private String description;
    private String imgBook;
    private String linkBook;
    private Integer numberView;
    private String authorName;
    private String authorProfile;
    private Integer cateId;
}
