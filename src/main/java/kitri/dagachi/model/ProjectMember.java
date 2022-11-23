package kitri.dagachi.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Setter @Getter
@Entity(name = "project_members")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long row_num;
    private Long project_id;
    private Long member_id;

//    @ManyToOne
//    @JoinColumn(name = "project_id")
//    private Project project;
}
