package kitri.dagachi.service;

//import kitri.dagachi.SecurityConfig;
import kitri.dagachi.model.Member;
import kitri.dagachi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        System.out.println("member : " + member);
        return member.getId();
    }

    // 비밀번호 패턴 검증


    // 로그인 매칭
//    public boolean loginMatch(String email, String encPwd) {
//
//
//
//        if(!email.equals(memberRepository.findEmail(email))) {
//            return false;
//        }
//        else if(!encPwd.equals(memberRepository.findPasswd(encPwd))) {
//            return false;
//        }
//    }

}
