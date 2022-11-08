package kitri.dagachi.File;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class FileRepository {

    private final EntityManager em;


    public FileForm save(FileForm fileForm) {
        em.persist(fileForm);
        return fileForm;
    }

    public FileForm findName(String origName) {
        return em.find(FileForm.class, origName);
    }

    public FileForm findTitle(String savedNm) {
        return em.find(FileForm.class, savedNm);
    }


    public FileForm findContent(String savedPath) {
        return em.find(FileForm.class, savedPath);
    }

}
