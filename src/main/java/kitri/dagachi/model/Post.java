package kitri.dagachi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter

public class Post {
    @Id
    @GeneratedValue
    @Column(name = "Post_Id")
    public Long id;


    public String Company_Name;

    public String Posting_Title;

    public String Posting_Content;


//    public Date Upload_Date;

    public Integer Member_Id;
    public Long getId() {
        return id;
    }

    public String getCompany_Name() {
        return Company_Name;
    }

    public String getPosting_Title() {
        return Posting_Title;
    }

    public String getPosting_Content() {
        return Posting_Content;
    }

//    public Date getUpload_Date() {
//        return Upload_Date;
//    }

    public Integer getMember_Id() {
        return Member_Id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setCompany_Name(String company_Name) {
        Company_Name = company_Name;
    }

    public void setPosting_Title(String posting_Title) {
        Posting_Title = posting_Title;
    }

    public void setPosting_Content(String posting_Content) {
        Posting_Content = posting_Content;
    }

//    public void setUpload_Date(Date upload_Date) {
//        Upload_Date = upload_Date;
//    }

    public void setMember_Id(Integer member_Id) {
        Member_Id = member_Id;
    }




}
