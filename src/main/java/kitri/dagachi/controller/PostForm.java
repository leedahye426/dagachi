package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {

    private String companyName;

    private String postingTitle;

    private String postingContent;

    private String upload_date;
}
