package kitri.dagachi.model;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="posting_board")
@Getter @Setter
@NoArgsConstructor

public class Post {

    @Id
    @GeneratedValue
    @Column(name = "posting_id")
    private Long postingId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "posting_title")
    private String postingTitle;

    @Column(name = "posting_content")
    private String postingContent;

    @Column(name = "upload_date")
    private String uploadDate;


    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "approve")
    private String approve = "N";

    @Column(name = "like_count")
    private Long cnt = 0L;


}

