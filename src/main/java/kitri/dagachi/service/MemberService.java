package kitri.dagachi.service;

//import kitri.dagachi.SecurityConfig;
import kitri.dagachi.controller.ResumeForm;
import kitri.dagachi.model.Member;
import kitri.dagachi.repository.AuthRepository;
import kitri.dagachi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.*;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {


    private final MemberRepository memberRepository;
    private final AuthRepository authRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ResumeForm resumeForm;


    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        System.out.println("member : " + member);

        return member.getId();
    }


    // 로그인 매칭V1
//    public boolean loginMatch(String email, String passwd, Long code) {
//
//        if(!email.equals(memberRepository.findByEmails(email))) {
//            System.out.println("이메일 매칭시작");
//            System.out.println("memberRepository.findByEmails(email) : " + memberRepository.findByEmails(email));
//            return false;
//        }
//        else if(!bCryptPasswordEncoder.matches(passwd, memberRepository.findByPasswd(email))) {
//            System.out.println("패스워드 매칭시작");
//            System.out.println("memberRepository.findByPasswd(email) : " + memberRepository.findByPasswd(email));
//            return false;
//        }
//        else if(!code.equals(memberRepository.findByCode(email))) {
//            System.out.println("코드 매칭시작");
//            System.out.println("memberRepository.findByCode(email) : " + memberRepository.findByCode(email));
//            return false;
//        }
//
//        System.out.println("전부 매칭 완료");
//
//        return true;
//    }

    // 로그인 매칭 V2
//    public Member loginService(String email, String passwd, String ROLE) {
//        System.out.println("로그인 매칭 확인");
//        System.out.println("ROLE : " + ROLE);
//        return memberRepository.findByLoginInfo(email)
//                .filter(m -> bCryptPasswordEncoder.matches(passwd, m.getPassword()))
//                .filter(m -> m.getROLE().equals(ROLE))
//                .orElse(null);
//    }

    public List<Member> findmembers(Long project_id) {
        return memberRepository.findByProjectId(project_id);
    }

    public Member findOneByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public void updateEmailById(Long id, String addr, String addrDetail) {
        Member member = memberRepository.findById(id);
        member.setAddr(addr);
        member.setAddrDetail(addrDetail);
    }


    // 로그인 매칭 V3
    // 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로챔.
    // username DB 유무 학인 및 password 처리는 알아서 함.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // email을 통한 멤버 정보 조회
        Optional<Member> member = authRepository.findByEmail(email);

        // isPresent() 객체를 사용하여 객체 존재여부를 확인 후에 가져오도록 함
        if(member.isPresent()) {
            // 있다면 user 정보를 가져옴
            Member user = member.get();

            System.out.println("=========start=========");
            // 인증된 유저 정보 빌드
            Member authUser = Member.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .ROLE(user.getROLE())
                    .businessNum(user.getBusinessNum())
                    .addr(user.getAddr())
                    .joinDate(user.getJoinDate())
                    .build();
            System.out.println("authUser : " + authUser);
            System.out.println("authUser id: " + authUser.getId());
            System.out.println("authUser name: " + authUser.getName());
            System.out.println("authUser email: " + authUser.getEmail());
            System.out.println("authUser passwd: " + authUser.getPassword());
            System.out.println("authUser role: " + authUser.getROLE());
            System.out.println("authUser business: " + authUser.getBusinessNum());
            System.out.println("authUser addr: " + authUser.getAddr());
            System.out.println("authUser date: " + authUser.getJoinDate());

            return authUser;
        }
        return null;
    }

}
