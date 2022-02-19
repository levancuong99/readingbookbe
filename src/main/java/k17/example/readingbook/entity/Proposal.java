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
@Table(name = "proposal")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prop_id")
    private Integer propId;

    @Column(name = "book_name")
    private String bookNameProp;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created_at")
    private Date createdAt;
}
