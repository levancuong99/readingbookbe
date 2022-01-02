package k17.example.readingbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="liked")
public class Liked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liked_id")
    private Integer likedId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "book_id")
    private Integer bookId;
}
