package kitri.dagachi.repository;


import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.model.PostingLike;
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

        //날짜 자동입력
        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); //이 작업 필수!! String 해줘야지 DB에 에러 없이 들어감. 아니면 타입안맞는다 오류 뜸
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


    //승인의 또 다른 방법
//    public Post approve(Post post)
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
//        return post;
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
        return em.createQuery("select p from posting_board p where (p.companyName) Like ('%'||:keyword||'%')",Post.class) //like 변수 형식 어려움! 중요! Model에 있는 값 아니고 keyword 맞음!
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Post> findApprove()
    {
        return em.createQuery("select p from posting_board p where p.approve ='Y'")
                .getResultList();
    }


    public List<PostTags> findByTag(Long postingId)
    {
        return  em.createQuery("select pt from posting_tags pt where pt.postingId =:postingId")
                .setParameter("postingId", postingId)
                .getResultList();

    }

    public List<Post> findOrderByLike(int limit) {
        return em.createQuery("select p from posting_board p order by p.cnt desc")
                .setMaxResults(limit)
                .getResultList();
    }


    public void cnt(Long postingId, Long cnt) {

        Post post = findOne(postingId);
        Long like = (Long) em.createQuery("select p.cnt from posting_board p where p.postingId = :postingId")
                .setParameter("postingId", postingId)
                .getSingleResult();
       post.setCnt(like+cnt);

        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUploadDate(formatedNow);

       em.persist(post);
       em.flush();

    }

}









