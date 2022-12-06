//package kitri.dagachi;
//
//import kitri.dagachi.model.Member;
//import kitri.dagachi.service.MemberService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
////@RequiredArgsConstructor
//@Component
//@NoArgsConstructor
//public class AuthProvider implements AuthenticationProvider {
//
//    @Autowired
//    private MemberService memberService;
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public AuthProvider(BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        System.out.println("authentication : " + authentication);
//        System.out.println("authentication.getDetails() : " + authentication.getDetails());
//        System.out.println("authentication.getCredentials() : " + authentication.getCredentials());
//
//        System.out.println("인증실행");
//
//        String email = authentication.getName();
//        String passwd = authentication.getCredentials().toString();
//
//        System.out.println("email : " + email);
//        System.out.println("passwd : " + passwd);
//
//        Member member = (Member) memberService.loadUserByUsername(email);
//
//        System.out.println("email : " + email);
//        System.out.println("passwd : " + passwd);
//        System.out.println("member : " + member);
//        System.out.println("member.getPassword() : " + member.getPassword());
////        System.out.println("passwordEncoder.matches(passwd, member.getPassword()) : " + passwordEncoder.matches(passwd, member.getPassword()));
//
//        System.out.println("authentication.getCredentials().toString() : " + authentication.getCredentials().toString());
//
//            if(!email.equals(member.getEmail())) {
//                throw new BadCredentialsException("에러발견1");
//            }
//            else if(!bCryptPasswordEncoder.matches(passwd, member.getPassword())) {
//                throw new BadCredentialsException("에러발견2" + passwd);
//            }
//
//        System.out.println("authentication.getCredentials().toString() : " + authentication.getCredentials().toString());
//
//        return new UsernamePasswordAuthenticationToken(email, passwd, member.getAuthorities());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
