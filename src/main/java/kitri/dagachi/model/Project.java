package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

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

    private Long like_cnt;
    private String approve;

//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<ProjectTag> projectTags = new ArrayList<>();
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<ProjectLike> projectLikes = new ArrayList<>();
//
//    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
//    private List<ProjectMember> projectMembers = new ArrayList<>();
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
