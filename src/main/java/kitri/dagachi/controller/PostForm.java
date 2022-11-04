package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Getter
@Setter
public class PostForm {

    private int Posting_ID;
    private String Company_Name;
    private String Posting_Title;
    private String Posting_Content;
    private Date Upload_Date;
    private String Member_ID;

}
