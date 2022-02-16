package k17.example.readingbook.model.dto;

import k17.example.readingbook.entity.Post;
import k17.example.readingbook.entity.Proposal;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostPagingDto {
    List<Post> posts;
    int totalPage;
    int currentPage;
    int numberRowCurrentpage;
    int allRow;
}
