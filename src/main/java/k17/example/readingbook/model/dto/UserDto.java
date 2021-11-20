package k17.example.readingbook.model.dto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
    private Integer userId;

    private String fullName;

    private String email;

    private String password;

    private String address;

    private Date dateOfBirth;

    private Boolean gender;

    private String img_avt;

    private String img_face;

    private Date createdAt;

    private Date updatedAt;

    private Boolean isDelete;

    private Integer roleId;
}
