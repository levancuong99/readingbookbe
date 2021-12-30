package k17.example.readingbook.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginDto {
    private String token;
    private int userId;
    private int roleId;
}
