package k17.example.readingbook.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParamsCreateComment {
    private Integer commentId;
    private String bookId;
    private String content;
    private Integer userId;
}
