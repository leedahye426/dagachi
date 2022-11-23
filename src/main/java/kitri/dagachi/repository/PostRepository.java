package kitri.dagachi.repository;

import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.model.PostingLike;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Repository
public class PostRepository {

    private final EntityManager em;


    public void save(Post post) {
        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUploadDate(formatedNow);


        System.out.println("post.getPostingId() : " + post.getPostingId());
        System.out.println("post.getCompanyName() : " + post.getCompanyName());
        System.out.println("post.getPostingTitle() : " + post.getPostingTitle());
        System.out.println("post.getPostingContent() : " + post.getPostingContent());
//        System.out.println("post.getUpload_date() : " + post.getUploadDate());
//        System.out.println("post.path : " + post.getPath());
//        System.out.println("post.filename : " + post.getFileName());

        em.persist(post);
        em.flush();
        System.out.println("insert 동작");
    }

    public void savaposttag(PostTags postTags) {
        em.persist(postTags);
        em.flush();
    }


    public void savaLike(PostingLike postinglike) {
        em.persist(postinglike);
        em.flush();
    }

    public Post findOne(Long postingId) {
        return em.find(Post.class, postingId);
    }



    public Post findName(String companyName) {
        return em.find(Post.class, companyName);
    }

    public Post findTitle(String postingTitle) {
        return em.find(Post.class, postingTitle);
    }

    public Post findContent(String postingContent) {
        return em.find(Post.class, postingContent);
    }

    public Post findOneDate(LocalDateTime Upload_Date) {
        return em.find(Post.class, Upload_Date);
    }

    public Post findID(Long memberID) {
        return em.find(Post.class, memberID);
    }




    public Post find0ne(Long id) {
        return em.find(Post.class, id);
    }

//            LocalDateTime localdate=LocalDateTime.now();
//            DateTimeFormatter  Date = DateTimeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss");


//    DateTimeFormatter DateTumeFormatter ;
//    String formatedNow= now.format(DateTumeFormatter.ofPattern("yyyy-mm-dd hh:mm:ss"));


    public List<Post> findAll() {
        return em.createQuery("select p from posting_board p", Post.class)
                .getResultList();
    }


    public void delete(Long postingId) {

        Post post = findOne(postingId);
        System.out.println("================" + post);

        em.remove(post);
        em.flush();

    }




    public String LOGO(String path, String filename)
    {



        return (String) em.createQuery("insert into posting_board (path, filename) value('path','filename')")
                .setParameter("path", path)
                .setParameter("filename", filename)
                .getSingleResult();


    }




}









