package kitri.dagachi.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PostFile {

    @Id

    @Column(name = "posting_id")
    private Long PostingId;


    @Column(name="file_name")
    private String fileName;


    @Column(name="file_path")
    private  String filePath;

    public PostFile(PostFile postfile) {

        this.fileName = postfile.fileName;
        this.filePath = postfile.filePath;
        this.PostingId = postfile.PostingId;

    }
}
