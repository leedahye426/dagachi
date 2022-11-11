package kitri.dagachi.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름은 필수입력 항목 입니다.")
    private String name;

    @NotEmpty(message = "이메일은 필수입력 항목 입니다.")
    private String email;

    @NotEmpty(message = "인증코드는 필수입력 항목 입니다.")
    private String authCode;

    @NotEmpty(message = "패스워드는 필수입력 항목 입니다.")
    private String password;

//    @NotEmpty(message = "전화번호는 필수입력 항목 입니다.")
//    private String phoneNum;

    @NotEmpty
    private String bsNum;

    @NotEmpty
    private String addr;

}
