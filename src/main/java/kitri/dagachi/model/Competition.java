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
    @Column(name = "poster_org_name")
    private String posterOrgName;
    @Column(name = "poster_saved_name")
    private String posterSavedName;
    @Column(name = "poster_saved_path")
    private String posterSavedPath;
    @Column(name = "banner_org_name")
    private String bannerOrgName;
    @Column(name = "banner_saved_name")
    private String bannerSavedName;
    @Column(name = "banner_saved_path")
    private String bannerSavedPath;
    private String writer;
    @Column(name = "member_id")
    private Long memberId;


    public Competition(){}
}
