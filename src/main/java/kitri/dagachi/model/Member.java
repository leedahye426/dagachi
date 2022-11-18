package kitri.dagachi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
    
@Entity
@Getter @Setter
@Table(name = "members")

@NoArgsConstructor
@AllArgsConstructor
@Builder
//@AllArgsConstructor
public class Member implements UserDetails {
                                // UserDetails : 사용자의 정보를 담는 인터페이스
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_role")
    private String ROLE;

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


    @Override // 계정의 권한 목록을 리턴
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(this.ROLE));
        return auth;
    }

    @Override // 계정의 고유한 값을 리턴( ex : DB PK값, 중복이 없는 이메일 값)
    public String getUsername() {
        return email;
    }

    @Override // 계정의 만료 여부 리턴 ( default : true (만료 안됨))
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정의 잠김 여부 리턴 ( default : true (잠기지 않음))
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀번호 만료 여부 리턴 ( default : true (만료 안됨))
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정의 활성화 여부 리턴 ( default : true (활성화 됨))
    public boolean isEnabled() {
        return true;
    }

}

