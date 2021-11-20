package k17.example.readingbook.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParamAdminUpdateUser {
    private String fullName;
    private String address;
    private Integer roleId;
}
