package k17.example.readingbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer commentId;
    private Integer userId;
    private Integer bookId;
    private String content;
}
