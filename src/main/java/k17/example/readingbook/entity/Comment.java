package k17.example.readingbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "img_avt")
    private String imgAvt;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private Date createdAt;

}
