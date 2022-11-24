package kitri.dagachi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="posting_board")
@Getter @Setter
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

//    @Column(name="file_path")
//    private String filePath;
//
//    @Column(name="file_name")
//    private String fileName;

//    @Builder
//    public Post(Long postingId, String companyName, String postingTitle, String postingContent, String path, String fileName) {
//        this.postingId = postingId;
//        this.companyName = companyName;
//        this.postingTitle = postingTitle;
//        this.postingContent = postingContent;
//        this.path = path;
//        this.fileName = fileName;
////        this.origName = origName;
////        this.savedNm = savedNm;
////        this.savedPath = savedPath;
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
