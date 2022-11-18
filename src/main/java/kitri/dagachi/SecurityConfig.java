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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration(proxyBeanMethods = false) // 빈등록
@EnableWebSecurity // 스프링 security 지원을 가능하게 함
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig{

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
                .antMatchers("/members/**").hasRole("ENT")
                .antMatchers("/project/enterprise/**",
                                        "/post/enterDetail/**",
                                        "/post/personalDetail/**").authenticated()
                .anyRequest().permitAll()
//                .antMatchers("/").permitAll()// 특정한 경로를 지정
//                .anyRequest().authenticated()
            .and()
                .authenticationManager(authenticationManager)
                .formLogin()
                .loginPage("/members/login") // 사용자 정의 로그인 페이지
                .usernameParameter("email") // loginForm에서 ID의 name값과 일치
                .passwordParameter("passwd") // loginForm에서 PASSWORD의 name값과 일치
                .loginProcessingUrl("/members/login") // 로그인 Form Action url
                .failureForwardUrl("/members/login") // 로그인 실패 후 페이지 포워딩
                .defaultSuccessUrl("/", true) // 로그인 성공 후 이동 페이지
                .permitAll() // 사용자 정의 로그인 페이지 권한 설정
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
