package k17.example.readingbook.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {
    private Integer bookId;
    private String bookName;
    private String description;
    private String imgBook;
    private String linkBook;
    private Integer numberView;
    private String authorName;
    private String authorProfile;
    private String cateName;
}
