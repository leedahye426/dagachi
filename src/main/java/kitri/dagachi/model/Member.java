package kitri.dagachi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_code")
    private Long code;

    @Column(name = "member_name")
    private String name;

    @JsonIgnore
    @Column(name = "member_password")
    private String password;

    @Column(name = "member_email")
    private String email;

    @Column(name = "member_date")
    private LocalDateTime joinDate;

    @Column(name = "business_number")
    private String businessNum;

    @Column(name = "member_addr")
    private String addr;
    // 최종 로그인 일시

}

