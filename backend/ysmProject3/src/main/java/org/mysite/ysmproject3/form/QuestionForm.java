package org.mysite.ysmproject3.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String text;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String name;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String password;
}
