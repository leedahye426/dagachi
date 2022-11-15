package kitri.dagachi.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity(name="posting_tags")
@Getter
@Setter
public class PostTags {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long row_num;

    @Column(name = "posting_id")
    private Long postingId;

    @Column(name = "posting_tag")
    private String tag;

//    @Override
//    public void addArgumentResolvers(List<handlerMethodArgumentResolver> argumentResolvers)
//    {
//        argumentResolvers.add(new pageableHandlerMethodArgumentResolver());
//    }


}
