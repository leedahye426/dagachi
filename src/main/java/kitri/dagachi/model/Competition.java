package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long competitionId;
    private String competitionTitle;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String url;
    private String content;
    private String host;
    private String orgName;
    private String savedName;
    private String savedPath;
    private Long memberId;
}
