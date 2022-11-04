package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name="project_board")
public class Project {

    @Id
    private Long project_id;

    private String team_name;
    private String file_route;
    private String upload_date;
    private Long member_id;

    public Project(Long project_id, String team_name, String file_route, String upload_date, Long member_id) {
        this.project_id = project_id;
        this.team_name = team_name;
        this.file_route = file_route;
        this.upload_date = upload_date;
        this.member_id = member_id;
    }

}
