package kitri.dagachi.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity(name="POSTING_BOARD")
@Getter
@Setter
public class PostForm {

    @Id
    @GeneratedValue
    @Column
    public Long Posting_ID;
    public String Company_Name;
    public String Posting_Title;
    public String Posting_Content;

    public String Upload_Date;

    public Integer Member_ID;


//    LocalDateTime now=LocalDateTime.now();
//    public DateTimeFormatter DateTumeFormatter;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));



}
