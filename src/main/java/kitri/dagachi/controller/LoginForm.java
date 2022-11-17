package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class LoginForm {

    @NotEmpty(message = "이름은 필수입력 항목 입니다.")
    private String email;

    @NotEmpty(message = "패스워드는 필수입력 항목 입니다.")
    private String passwd;

    @NotNull(message = "회원구분은 필수입력 항목 입니다.")
    private String ROLE;
}
