package kitri.dagachi.controller;

import kitri.dagachi.service.EmailService;
import kitri.dagachi.service.MemberService;
import kitri.dagachi.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("members/")
@RequiredArgsConstructor
public class ApiController {

    @Autowired
    private final EmailService emailService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RedisService redisService;
    private final MemberService memberService;

    // 회원가입 폼으로부터 이메일 데이터 전달
    @PostMapping("join/emailConfirm")
    @ResponseBody
    public String emailConfirm(@RequestParam("email") String email)  throws Exception {
        System.out.println("전달 받은 이메일 : " + email);
        String code = emailService.sendSimpleMessage(email);
        System.out.println("전송한 code : " + code);

        redisService.setData(email, code, 3);
        System.out.println("setData 성공");
        System.out.println(redisService.getData(email));
        return null; // code가 반환 시 평문으로 노출됨

    }

    // 서버 단에서 인증일치를 수행
    @PostMapping("join/codeConfirm")
    @ResponseBody
    public int authCode(@RequestParam("email") String email,
                        @RequestParam("authCode") String authCode) {

        System.out.println("전달한 코드 : " + emailService.ePw);
        System.out.println("redisService.getData(email) : " + redisService.getData(email));
        System.out.println("authCode : " + authCode);

        try {
            if(redisService.getData(email).equals(authCode)) return Integer.parseInt(authCode);
        }catch (NullPointerException e) {
            return 0;
        }

        return 0;
    }
}
