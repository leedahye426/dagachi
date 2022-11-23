package kitri.dagachi.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "member_certificates")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberCertificates {

    @Id
    @GeneratedValue
    @Column(name = "row_num")
    private Long rowNum;

    @Column(name = "member_id")
    private Long id;

    @Column(name = "certificate_name")
    private String certificateName;

    @Column(name = "certificate_issuer")
    private String certificateIssuer;

    @Column(name = "issued_date")
    private String issuedDate;

}
