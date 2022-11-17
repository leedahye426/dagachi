package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "project_like")
@Getter @Setter
public class ProjectLike {
    @Id
    @GeneratedValue
    private Long row_num;

    private Long project_id;

    private Long member_id;
}
