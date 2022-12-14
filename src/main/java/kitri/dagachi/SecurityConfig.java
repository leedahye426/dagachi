package kitri.dagachi;

import kitri.dagachi.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.ProviderManagerBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.access.AccessDeniedHandler;
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

@Configuration(proxyBeanMethods = false) // ?????????
@EnableWebSecurity // ????????? security ????????? ???????????? ???
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@RequiredArgsConstructor
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    MemberService memberService;
    AuthenticationManager authenticationManager;
    AuthenticationDetailsSource authenticationDetailsSource; // ???????????? ???????????? ??????
//    AuthProvider authProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authProvider);
//    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_PER\nROLE_ADMIN > ROLE_ENT");
        return roleHierarchy;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(memberService);
        authenticationManager = authenticationManagerBuilder.build();

        http.csrf().disable(); // csrf ?????? ????????????
        http.headers().frameOptions().sameOrigin(); // ?????? ????????? ??? X-Frame-Options ?????????(PDF viewer)

        http.authorizeRequests() // authorizeRequests() : ???????????? ????????? HttpServletRequest??? ??????
                .antMatchers("/members/login, /members/join/**").anonymous()

                .antMatchers("/project/personal/project_list",
                                        "/post/personal/post_list",
                                        "/competition/competition_list").permitAll()

                .antMatchers("/project/personal/detail/**",
                                        "/project/personal/search",
                                        "/post/personal/post_detail/**").hasAnyRole("PER", "ENT")

                .antMatchers("/project/enterprise/**",
                                        "/post/enterprise/**",
                                        "/post/admin/post_approve_detail/**",
                                        "/post/admin/delete/**").hasRole("ENT")

                .antMatchers("/members/resumeChk", "/members/resumeEdit",
                        "/project/personal/**", "/project/admin/project_approve_detail/**",
                        "/post/personal/**").hasRole("PER")

                .antMatchers("/competition/admin/**",
                                        "/project/admin/**",
                                        "/post/admin/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
            .and()
                .authenticationManager(authenticationManager)
                .formLogin()
                .loginPage("/members/login") // ????????? ?????? ????????? ?????????
                .usernameParameter("email") // loginForm?????? ID??? name?????? ??????
                .passwordParameter("passwd") // loginForm?????? PASSWORD??? name?????? ??????
                .loginProcessingUrl("/members/login") // ????????? Form Action url
//                .authenticationDetailsSource(authenticationDetailsSource)
//                .defaultSuccessUrl("/", true) // ????????? ?????? ??? ?????? ?????????
//                .failureForwardUrl("/members/login?error=true") // ????????? ?????? ??? ????????? ?????????
                .successHandler(new AuthenticationSuccessHandler() { // ????????? ?????? ?????????
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {

                        System.out.println("request.getParameter(\"ROLE\") : " + request.getParameter("ROLE"));

                        System.out.println("??????????????? ??????");

                        // ?????? ?????? ??????
                        HttpSession session = request.getSession(false);
                        if(session != null) {
                            System.out.println("???????????? ??????");
                            session.removeAttribute((WebAttributes.AUTHENTICATION_EXCEPTION));
                        }

                        String prevPage = (String) request.getSession().getAttribute("prevPage");
                        String uri = "/"; // defaultURI
//
                        if(prevPage != null) {
                            uri = prevPage;
                            request.getSession().removeAttribute("prevPage");
                        }

                        // Security??? ????????? ????????? ?????? ???????????? ?????? ???????????? URI ????????? ????????? ??????
                        RequestCache requestCache = new HttpSessionRequestCache();
                        System.out.println("requestCache : " + requestCache);
                        System.out.println("requestCache.getRequest(request,response) : " + requestCache.getRequest(request,response));
                        SavedRequest savedRequest = requestCache.getRequest(request, response);
                        System.out.println("savedRequest : " + savedRequest);
//                        System.out.println("savedRequest.getRedirectUrl() : " + savedRequest.getRedirectUrl());
//                        System.out.println("savedRequest.getHeaderNames() : " + savedRequest.getHeaderNames());
//                        System.out.println("savedRequest.getCookies() : " + savedRequest.getCookies());

                        if(savedRequest != null) {
                            System.out.println("???????????????");
                            uri = savedRequest.getRedirectUrl();
                            requestCache.removeRequest(request, response);
                        }

                            response.sendRedirect(uri);

                    }
                })
                .failureHandler(new AuthenticationFailureHandler() { // ????????? ?????? ?????????
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        AuthenticationException exception) throws IOException, ServletException {

                        System.out.println("???????????? : " + exception);

                        String email = request.getParameter("email");
                        System.out.println("email : " + email);
                        UserDetails user = memberService.loadUserByUsername(email);

                        System.out.println("?????? ????????? ??????");
                        HttpSession session = request.getSession();

                        if (exception instanceof InternalAuthenticationServiceException) {
                            System.out.println("?????? ????????? ??????");
                            session.setAttribute("failMsg", "?????? ????????? ????????? ????????? ????????? ????????? ??? ????????????.");
                        }
                        else if(exception instanceof BadCredentialsException) {
                            System.out.println("????????? ?????? ???????????? ??????");
                            session.setAttribute("failMsg", "????????? ?????? ??????????????? ???????????? ????????????");
                        }

                        response.sendRedirect("/members/login?error");
                    }
                })
//                .permitAll() // ????????? ?????? ????????? ????????? ?????? ??????
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

                        System.out.println("?????? ??????");

                        response.setContentType("text/html; charset=euc-kr");
                        PrintWriter out = response.getWriter();
                        out.println("<script>" +
                                    "alert('???????????? ????????? ??????????????????.');" +
                                    "location.href='/members/login';" +
                                    "</script>");
                        out.flush();

                    }
                })
            .and()
                .sessionManagement()
                .invalidSessionUrl("/")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/");

        return http.build();
    }

}
