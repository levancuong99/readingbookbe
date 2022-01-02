package k17.example.readingbook.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="viewed")
public class Viewed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "viewed_id")
    private Integer viewedId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name="book_id")
    private Integer bookId;
}
