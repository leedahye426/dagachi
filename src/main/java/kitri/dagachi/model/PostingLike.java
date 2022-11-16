package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="posting_like")
@Getter @Setter

public class PostingLike {



    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "posing_id")
    private Long postingId;

    @Column(name = "member_id")
    private Long memberId;

//    @Column(name = "likeCheck")
//    private Long likeCheck;

}
