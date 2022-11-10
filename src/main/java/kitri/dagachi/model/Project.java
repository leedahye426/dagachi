package kitri.dagachi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity(name="project_board")
@Getter @Setter
//@EntityListeners(AuditingEntityListener.class)
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long project_id;
    private String team_name;
    private String saved_path;
    private LocalDateTime upload_date;
    private Long member_id;
    private String project_title;
    private String org_name;
    private String saved_name;
    private String project_content;


    public Project() {};
//
//    @Builder
    public Project(Long project_id, String team_name, String saved_path, Long member_id, String project_title, String org_name, String saved_name) {
        this.project_id = project_id;
        this.team_name = team_name;
        this.saved_path = saved_path;
        this.member_id = member_id;
        this.project_title = project_title;
        this.org_name = org_name;
        this.saved_name = saved_name;
    }
}
