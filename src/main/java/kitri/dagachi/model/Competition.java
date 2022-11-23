package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "competition_board")
@Setter @Getter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "competition_id")
    private Long id;
    @Column(name = "competition_title")
    private String title;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    private String url;
    private String content;
    private String host;
    @Column(name = "org_name")
    private String orgName;
    @Column(name = "saved_name")
    private String savedName;
    @Column(name = "saved_path")
    private String savedPath;
    private String writer;
    @Column(name = "member_id")
    private Long memberId;


    public Competition(){}
}
