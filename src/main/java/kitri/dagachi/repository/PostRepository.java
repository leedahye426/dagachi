package kitri.dagachi.repository;

import kitri.dagachi.controller.PostForm;
import kitri.dagachi.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    private final EntityManager em;

//    private PostRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(PostForm postForm) {
        em.persist(postForm);
    }

//    public post save

//    public PostForm findOne(String Company_Name){return em.find(PostForm.class, id);}

    public Post findName(String Company_Name) {
        return em.find(Post.class, Company_Name);
    }

    public Post findTitle(String Posting_Title) {
        return em.find(Post.class, Posting_Title);
    }

    public Post findContent(String Posting_Content) {
        return em.find(Post.class, Posting_Content);
    }

    public Post findOneDate(String Upload_Date) {  return em.find(Post.class, Upload_Date);}

    public Post findID(Integer Member_ID) {
        return em.find(Post.class, Member_ID);
    }

//            LocalDateTime localdate=LocalDateTime.now();
//            DateTimeFormatter  Date = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");


//    DateTimeFormatter DateTumeFormatter ;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));





    public List<PostForm> findAll() {
        return em.createQuery("select Company_Name from POSTING_BOARD", PostForm.class)
                .getResultList();
    }

    public List<PostForm> findByName(String Company_name) {
        return em.createQuery("select Company_Name from POSTING_BOARD p where p.name = :name", PostForm.class)
                .setParameter("Company_name", Company_name)
                .getResultList();
    }



}




