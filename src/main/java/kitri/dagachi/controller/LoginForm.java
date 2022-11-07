package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "이름은 필수입력 항목 입니다.")
    private String email;

    @NotEmpty(message = "이름은 필수입력 항목 입니다.")
    private String passwd;

    @NotEmpty(message = "이름은 필수입력 항목 입니다.")
    private int codeType;
}
