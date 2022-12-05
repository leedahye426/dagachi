package kitri.dagachi;

import lombok.Getter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

@Getter
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    private final String roleType;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        System.out.println("부가인증실행");
        roleType = request.getParameter("ROLE");
    }

}
