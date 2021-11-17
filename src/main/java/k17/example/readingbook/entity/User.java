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
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="gender")
    private Boolean gender;

    @Column(name="img_avt")
    private String img_avt;

    @Column(name="img_face")
    private String img_face;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    private Date updatedAt;

    @Column(name = "is_delete", columnDefinition = "boolean default false")
    private Boolean isDelete;

    @Column(name = "role_id")
    private Integer roleId;

}
