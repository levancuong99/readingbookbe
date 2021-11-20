package k17.example.readingbook.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamCreateUser {

    private String fullName;

    private String email;

    private String password;

    private String address;

    private Date dateOfBirth;

    private Boolean gender;

    private Integer roleId;

}
