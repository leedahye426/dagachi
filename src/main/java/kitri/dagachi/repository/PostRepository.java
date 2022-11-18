package kitri.dagachi.repository;

import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.model.PostingLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
        LocalDateTime uploadDate = LocalDateTime.now();
        String formatedNow = uploadDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        post.setUploadDate(formatedNow);


        System.out.println("post.getPostingId() : " + post.getPostingId());
        System.out.println("post.getCompanyName() : " + post.getCompanyName());
        System.out.println("post.getPostingTitle() : " + post.getPostingTitle());
        System.out.println("post.getPostingContent() : " + post.getPostingContent());
        System.out.println("post.getUpload_date() : " + post.getUploadDate());

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

//    public void detail(String companyName, String postingTitle, String postingContent)
//    {
//        em.persist(companyName);
//    }


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

    public Post findOneDate(LocalDateTime Upload_Date) {
        return em.find(Post.class, Upload_Date);
    }

    public Post findID(Integer Member_ID) {
        return em.find(Post.class, Member_ID);
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

//    public Page<Post> findAll(int pageRequest){
//
//        return em.createQuery(select )
//    }

//    public List<PostTags> findAllt() {
//        return em.createQuery("select p from posting_board p", PostTags.class)
//                .getResultList();
//    }

    public void delete(Long postingId) {

        Post post = findOne(postingId);
        System.out.println("================" + post);
        em.remove(post);
        em.flush();

    }


//    public int delete(Long postingId) {
//       int result= em.createQuery("delete from posting_board p where p.postingId = :postingId")
//                .setParameter("postingId",postingId).executeUpdate();
//         return result;
//    }


}




