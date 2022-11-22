package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

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
    private Date startDate;
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

    public Competition(Long id, String title, Date startDate, LocalDateTime endDate, String url, String content, String host, String orgName, String savedName, String savedPath, String writer) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.url = url;
        this.content = content;
        this.host = host;
        this.orgName = orgName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.writer = writer;
    }

    public Competition(){}
}
