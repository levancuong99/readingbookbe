package k17.example.readingbook.model.dto;

import k17.example.readingbook.entity.Comment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentPagingDto {
    List<Comment> comments;
    int totalPage;
    int currentPage;
    int numberRowCurrentpage;
    int allRow;
}
