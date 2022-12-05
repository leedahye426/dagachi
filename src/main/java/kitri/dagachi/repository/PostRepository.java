package kitri.dagachi.repository;

import io.lettuce.core.dynamic.annotation.Param;
//import kitri.dagachi.dto.PostDto;
//import kitri.dagachi.dto.PostFileDto;
import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.model.PostingLike;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    public void approve(Long postingId) {

        Post post = findOne(postingId);
        post.setApprove("Y");

        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUploadDate(formatedNow);


        em.persist(post);
        em.flush();
    }

    public void approveCancel(Long postingId) {

        Post post = findOne(postingId);
        post.setApprove("N");

        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUploadDate(formatedNow);


        em.persist(post);
        em.flush();
    }

//    public void approve(Post post)
//    {
//        if(post.getApprove() =="N")
//        {
//            post.setApprove("Y");
//        }
//        else
//        {
//            post.setApprove("N");
//        }
//
//
//    }

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

    public List<Post> findById(Long memberId) {
        return em.createQuery("select p from posting_board p where p.memberId =:memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }




    public List<Post> findByTitleContaining(String keyword)
    {
        return em.createQuery("select p from posting_board p where (p.companyName) Like ('%'||:keyword||'%')",Post.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Post> findApprove()
    {
        return em.createQuery("select p from posting_board p where p.approve ='Y'")
                .getResultList();
    }

    public List<Post> findOrderByLike(int limit) {
        return em.createQuery("select p from posting_board p order by p.likeCnt desc")
                .setMaxResults(limit)
                .getResultList();
    }



}









