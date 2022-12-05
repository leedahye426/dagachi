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
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

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
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_PER\nROLE_ADMIN > ROLE_ENT");
        return roleHierarchy;
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
                .antMatchers("/competition/detail/2123").hasRole("PER")
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
                .successHandler(new AuthenticationSuccessHandler() { // 로그인 성공 핸들러
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        System.out.println("성공핸들러 작동");

                        // 에러 세션 제거
                        HttpSession session = request.getSession(false);
                        if(session != null) {
                            System.out.println("에러세션 삭제");
                            session.removeAttribute((WebAttributes.AUTHENTICATION_EXCEPTION));
                        }

                        // Security가 요청을 가로챈 경우 사용자가 원래 요청했던 URI 정보를 저장한 객체
                        RequestCache requestCache = new HttpSessionRequestCache();
                        System.out.println("requestCache : " + requestCache);
                        System.out.println("requestCache.getRequest(request,response) : " + requestCache.getRequest(request,response));
                        SavedRequest savedRequest = requestCache.getRequest(request, response);
                        System.out.println("savedRequest : " + savedRequest);
//                        System.out.println("savedRequest.getRedirectUrl() : " + savedRequest.getRedirectUrl());
//                        System.out.println("savedRequest.getHeaderNames() : " + savedRequest.getHeaderNames());
//                        System.out.println("savedRequest.getCookies() : " + savedRequest.getCookies());

                        if(savedRequest != null) {
                            System.out.println("리다이렉트");
                            String uri = savedRequest.getRedirectUrl();
                            requestCache.removeRequest(request, response);
                            response.sendRedirect(uri);
                        }
                        else {
                            response.sendRedirect("/");
                        }
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() { // 로그인 실패 핸들러
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {

                        System.out.println("에러타입 : " + exception);

                        String email = request.getParameter("email");
                        System.out.println("email : " + email);
                        UserDetails user = memberService.loadUserByUsername(email);

                        System.out.println("실패 핸들러 작동");
                        HttpSession session = request.getSession();

                        if (exception instanceof InternalAuthenticationServiceException) {
                            System.out.println("내부 시스템 문제");
                            session.setAttribute("failMsg", "내부 시스템 문제로 로그인 요청을 처리할 수 없습니다.");
                        }
                        else if(exception instanceof BadCredentialsException) {
                            System.out.println("아이디 또는 비밀번호 틀림");
                            session.setAttribute("failMsg", "아이디 또는 비밀번호가 일치하지 않습니다");
                        }

                        response.sendRedirect("/members/login?error");
                    }
                })
//                .permitAll() // 사용자 정의 로그인 페이지 권한 설정
            .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request,
                                       HttpServletResponse response,
                                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

                        response.sendRedirect("/denied");
                    }
                })
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request,
                                         HttpServletResponse response,
                                         AuthenticationException authException) throws IOException, ServletException {

                        System.out.println("권한 필요");

                        response.setContentType("text/html; charset=euc-kr");
                        PrintWriter out = response.getWriter();
                        out.println("<script>" +
                                    "alert('로그인 필요한 서비스입니다.');" +
                                    "location.href='/members/login';" +
                                    "</script>");
                        out.flush();

                    }
                });
//            .and()
//                .sessionManagement()
//                .invalidSessionUrl("/")
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true)
//                .expiredUrl("/");

        return http.build();
    }

}
