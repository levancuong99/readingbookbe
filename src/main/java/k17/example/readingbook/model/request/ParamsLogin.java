package k17.example.readingbook.model.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ParamsLogin {
    private String email;
    private String password;
}
