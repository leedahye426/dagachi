package kitri.dagachi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private int id;

    @Column(name = "member_code")
    private int code;

    @Column(name = "member_name")
    private String name;

    @JsonIgnore
    @Column(name = "member_password")
    private String passwd;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_date")
    private String joinDate;

    @Column(name = "business_number")
    private String businessNum;

}

