package kitri.dagachi.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="project_tags")
@Getter
@Setter


public class ProjectTag {

    @Id
    @GeneratedValue
    private Long row_num;

    private Long posting_id;

    private String posting_tag;
}
