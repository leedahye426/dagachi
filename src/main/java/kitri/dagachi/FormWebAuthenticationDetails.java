package kitri.dagachi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Component
@Getter
public class FormWebAuthenticationDetails extends WebAuthenticationDetails {

    private String roleType;
//    private String email;
//    private String passwd;

    public FormWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        roleType = request.getParameter("ROLE");
//        email = request.getParameter("email");
//        passwd = request.getParameter("passwd");

    }

}
