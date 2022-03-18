package k17.example.readingbook.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "description")
    private String description;
    @Column(name = "img_book")
    private String imgBook;
    @Column(name = "link_book")
    private String linkBook;
    @Column(name = "number_view")
    private Integer numberView;
    @Column(name = "number_like")
    private Integer numberLike;
    @Column(name = "author_name")
    private String authorName;
    @Column(name = "profile_author")
    private String authorProfile;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "cate_id")
    private Integer cateId;
}
