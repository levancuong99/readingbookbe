package k17.example.readingbook.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParamsCreateBook {
    private Integer bookId;
    private String bookName;
    private String description;
    private String imgBook;
    private String linkBook;
    private String authorName;
    private String authorProfile;
    private Integer cateId;
}
