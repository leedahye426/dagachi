package kitri.dagachi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String uploadPath = "/upload/**";
    private String portfolioResourcePath = "file:///D:/test/portfolio/";
    private String posterResourcePath = "file:///D:/test/poster/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //요청 url이 /upload/로 시작될 경우 D:/test/poster로 요청을 전달
        registry.addResourceHandler(uploadPath)
                .addResourceLocations(portfolioResourcePath)
                .addResourceLocations(posterResourcePath);
    }
}
