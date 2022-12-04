package kitri.dagachi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String uploadPath = "/image/**";
    private String defaultResourcePath = "file:///D:/test/default/";
    private String profileResourcePath = "file:///D:/test/profile/";
    private String portfolioResourcePath = "file:///D:/test/portfolio/";
    private String posterResourcePath = "file:///D:/test/poster/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("webconfig 실행");
        //요청 url이 /upload/로 시작될 경우 D:/test/poster로 요청을 전달
        registry.addResourceHandler(uploadPath)
                .addResourceLocations(defaultResourcePath)
                .addResourceLocations(profileResourcePath)
                .addResourceLocations(portfolioResourcePath)
                .addResourceLocations(posterResourcePath);
    }
}
