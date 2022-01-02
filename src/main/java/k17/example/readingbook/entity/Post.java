package k17.example.readingbook.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;
    @Column(name = "title")
    private String title;
    @Column(name="content")
    private String content;
    @Column(name = "img_post")
    private String imgPost;
    @Column(name = "created_at")
    private Date createdAt;
}
