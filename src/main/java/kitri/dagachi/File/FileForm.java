package kitri.dagachi.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
//@Table(name = "")
@Entity
@Getter
@Setter
public class FileForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="posting_id")
    private Long id;

    private String origName;

    private String savedNm;

    private String savedPath;

    @Builder
    public FileForm(Long id, String origName, String savedNm, String savedPath) {
        this.id = id;
        this.origName = origName;
        this.savedNm = savedNm;
        this.savedPath = savedPath;
    }
}
