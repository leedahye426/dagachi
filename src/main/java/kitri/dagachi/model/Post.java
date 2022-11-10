package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="posting_board")
@Getter @Setter
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
    private String upload_date;
//    public LocalDateTime Upload_Date;

    @Column(name = "member_id")
    private Long member_id;


//    public PostForm get() {
//        return "";
//    }


//    LocalDateTime now=LocalDateTime.now();
//    public DateTimeFormatter DateTumeFormatter;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));



}
