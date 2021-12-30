package k17.example.readingbook.model.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParamProp {
    private String bookName;
    private String authorName;
    private String remark;
}
