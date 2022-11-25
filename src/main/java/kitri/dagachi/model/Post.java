package kitri.dagachi.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="posting_board")
@Getter @Setter
@NoArgsConstructor
//@RequiredArgsConstructor

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
//    public LocalDateTime Upload_Date;

    @Column(name = "member_id")
    private Long memberId;



//    private Long fileId;




//    public void setFileId(Long fileId) {
//        this.fileId = fileId;
//    }
//
//    public Long getFileId() {
//        return fileId;
//    }

//    @Column(name="file_path")
//    private String filePath;
//
//    @Column(name="file_name")
//    private String fileName;

//    @Builder
//    public Post(Long postingId, String companyName, String postingTitle, String postingContent, String uploadDate, Long fileId) {
//        this.postingId = postingId;
//        this.companyName = companyName;
//        this.postingTitle = postingTitle;
//        this.postingContent = postingContent;
//        this.uploadDate = uploadDate;
//        this.fileId = fileId;
//
////
//    }




//    @Column(name = "posting_tag")
//    private String tag;

//    public PostForm get() {
//        return "";
//    }


//    LocalDateTime now=LocalDateTime.now();
//    public DateTimeFormatter DateTumeFormatter;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));



}
