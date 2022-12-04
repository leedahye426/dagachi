package kitri.dagachi.repository;

import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.model.PostingLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import java.util.List;


@RequiredArgsConstructor
@Repository
public class PostLikeRepository {


    private final EntityManager em;


    public PostingLike findMID(Long memberID) {
        return em.find(PostingLike.class, memberID);
    }

    public PostingLike findPID(Long postingId) { return  em.find(PostingLike.class, postingId);}


    @Query
    public PostingLike findLike(Long postingId, Long memberId)
            {
                return  (PostingLike) em.createQuery("select pl from posting_like pl where pl.postingId = : postingId and pl.memberId = : memberId")
                        .setParameter("postingId",postingId)
                        .setParameter("memberId",memberId)
                        .getSingleResult();

    }


    public List<PostingLike> LikeAll(Long memberId) {
        return em.createQuery("select pl from posting_like pl where pl.memberId =:memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }


    public Post findById(Long postingId) {
        return (Post) em.createQuery("select p from posting_board p where p.postingId = :postingId")
                .setParameter("postingId", postingId)
                .getSingleResult();
    }


    public PostTags findByOne(Long postingId) {
        return (PostTags) em.createQuery("select pt from posting_tags pt where pt.postingId = :postingId")
                .setParameter("postingId", postingId)
                .getSingleResult();
    }


    @Transactional
    public void del(PostingLike postinglike)
    {

        em.remove(postinglike);
        em.flush();

        System.out.println("삭제?");


    }

    @Query
    public Long likecnt(Long postingId, Long memberId)
    {
        return  (Long) em.createQuery("select count(pl) from posting_like pl where pl.postingId = : postingId and pl.memberId = : memberId")
                .setParameter("postingId",postingId)
                .setParameter("memberId",memberId)
                .getSingleResult();

    }


    public Long cnt(Long postingId)
    {
       return (Long)em.createQuery("select pl from posting_like pl where pl.postingId =:postingId")
               .setParameter("postingId",postingId)
               .getSingleResult();


    }



//    public Post findApprove(Long postingId, String approve)
//    {
//        return  (Post) em.createQuery("select p from posting_board p where p.postingId = : postingId and p.approve = : approve")
//                .setParameter("postingId",postingId)
//                .setParameter("approve",approve)
//                .getSingleResult();
//
//    }





}



