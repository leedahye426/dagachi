package kitri.dagachi;

import kitri.dagachi.model.Member;
import kitri.dagachi.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration(proxyBeanMethods = false) // 빈등록
@EnableWebSecurity // 스프링 security 지원을 가능하게 함
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {

    @Autowired
    MemberService memberService;
    AuthenticationManager authenticationManager;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberService);
        authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable(); // csrf 기능 비활성화
        http.headers().frameOptions().sameOrigin(); // 동일 도메인 내 X-Frame-Options 활성화(PDF viewer)

        http.authorizeRequests() // authorizeRequests() : 시큐리티 처리에 HttpServletRequest를 이용
                .antMatchers("/members").anonymous()
                .antMatchers("/project/enterprise/**",
                        "/post/ent/enterDetail/**",
                        "/project/enterprise/**",
                        "/project/enterprise_project_list").hasAnyRole("ENT", "ADMIN")
                .antMatchers("/project/personal/**",
                        "/post/person/**").hasAnyRole("PER", "ADMIN")
                .antMatchers("/post/enter/enter_post").permitAll()
                .antMatchers("/members/login").permitAll() // 무한루프 방지(사용자 정의 로그인 페이지 권한 설정)
                .anyRequest().permitAll()
//                .antMatchers("/").permitAll()// 특정한 경로를 지정
//                .anyRequest().authenticated()
            .and()
                .authenticationManager(authenticationManager)
                .formLogin()
                .loginPage("/members/login") // 사용자 정의 로그인 페이지
                .usernameParameter("email") // loginForm에서 ID의 name값과 일치
                .passwordParameter("passwd") // loginForm에서 PASSWORD의 name값과 일치
//                .defaultSuccessUrl("/", true) // 로그인 성공 후 이동 페이지
                .loginProcessingUrl("/members/login") // 로그인 Form Action url
//                .failureForwardUrl("/members/login?error=true") // 로그인 실패 후 페이지 포워딩
                .successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 이벤트 핸들러
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        System.out.println("인증성공");
                        System.out.println("request : " + request);

                        response.sendRedirect("/");
                    }

                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest request,
//                                                        HttpServletResponse response,
//                                                        AuthenticationException exception) throws IOException, ServletException {
//                        System.out.println("인증실패");
//
//                        response.sendRedirect("/members/login?error=true");
//                    }
//                })
//                .permitAll() // 사용자 정의 로그인 페이지 권한 설정
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
                .exceptionHandling()
                .accessDeniedPage("/");
//            .and()
//                .sessionManagement()
//                .invalidSessionUrl("/")
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//                .expiredUrl("/");

        return http.build();
    }

}
