package k17.example.readingbook.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParamUpdatePost {
    private Integer postId;
    private String title;
    private String content;
    private String imgPost;
}
