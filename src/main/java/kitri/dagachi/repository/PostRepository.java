package kitri.dagachi.repository;

import kitri.dagachi.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    private final EntityManager em;



    public void save(Post post) {
        LocalDateTime upload_date = LocalDateTime.now();
        String formatedNow = upload_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUpload_date(formatedNow);


        System.out.println("post.getPostingId() : " + post.getPostingId());
        System.out.println("post.getCompanyName() : " + post.getCompanyName());
        System.out.println("post.getPostingTitle() : " + post.getPostingTitle());
        System.out.println("post.getPostingContent() : " + post.getPostingContent());
        System.out.println("post.getUpload_date() : " + post.getUpload_date());

        em.persist(post);
        em.flush();
        System.out.println("insert 동작");
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

    public Post findOneDate(LocalDateTime Upload_Date) {  return em.find(Post.class, Upload_Date);}

    public Post findID(Integer Member_ID) {
        return em.find(Post.class, Member_ID);
    }

//            LocalDateTime localdate=LocalDateTime.now();
//            DateTimeFormatter  Date = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");


//    DateTimeFormatter DateTumeFormatter ;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));





    public List<Post> findAll() {
        return em.createQuery("select p from posting_board p", Post.class)
                .getResultList();
    }



//    public List<PostForm> findByName(String Company_name) {
//        return em.createQuery("select Company_Name from POSTING_BOARD p where p.name = :name", PostForm.class)
//                .setParameter("Company_name", Company_name)
//                .getResultList();
//    }



}




