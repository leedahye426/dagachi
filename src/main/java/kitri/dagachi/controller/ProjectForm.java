package kitri.dagachi.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class ProjectForm {

    @NotEmpty(message = "팀명은 필수 입니다")
    private String team_name;

    @NotEmpty(message = "제목은 필수 입니다")
    private String project_title;

    @NotEmpty(message = "pdf파일 첨부는 필수 입니다")
    private String file_path;


}
