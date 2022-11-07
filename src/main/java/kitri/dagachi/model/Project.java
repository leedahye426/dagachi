package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="project_board")
@Getter @Setter
//@Table(name="project_board")
public class Project {

    @Id
    @GeneratedValue
    private Long project_id;
    private String team_name;
    private String file_route;
    private String upload_date;
    private Long member_id;
    private String project_title;

//    public Project() {};
//
//    public Project(Long project_id, String project_title, String team_name, String file_route, String upload_date, Long member_id) {
//        this.project_id = project_id;
//        this.project_title = project_title;
//        this.team_name = team_name;
//        this.file_route = file_route;
//        this.upload_date = upload_date;
//        this.member_id = member_id;
//    }

}
