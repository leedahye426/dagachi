package kitri.dagachi.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostFile {

    @Id
    @Column(name = "posting_id")
    private Long id;


    @Column(name="orig_file_name")
    private String origFileName;

    @Column(name="file_name")
    private String fileName;


    @Column(name="file_path")
    private  String filePath;

    @Builder
    public PostFile(Long id, String origFileName, String fileName, String filePath) {


        this.id = id;
        this.origFileName = origFileName;
        this.fileName = fileName;
        this.filePath = filePath;

    }
}
