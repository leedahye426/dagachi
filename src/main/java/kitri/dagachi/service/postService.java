package kitri.dagachi.service;

import kitri.dagachi.model.Post;
//import kitri.dagachi.repository.FileRepository;
import kitri.dagachi.model.PostTags;
import kitri.dagachi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class postService {

        @Autowired
        private final PostRepository postRepository;

        //글 작성 처리
        public void register(Post post,String[] tag) {


            postRepository.save(post);


            Long postingId = post.getPostingId();
            for (int i = 0; i < tag.length; i++) {
                PostTags postTags = new PostTags();
                postTags.setTag(tag[i]);
                postTags.setPostingId(postingId);
                postRepository.savaposttag(postTags);

            }
        }



//        public void detail(String companyName,String postingTitle, String postingContent)
//            {
//                Post post = new Post();
//                post.setCompanyName(companyName);
//                post.setPostingTitle(postingTitle);
//                post.setPostingContent(postingContent);
//                postRepository.detail(companyName,postingContent,postingTitle);
//            }




        //리스트처리
        public List<Post> posting(){


                return postRepository.findAll();

        }

    public Post findOne(Long postingId) {
        return postRepository.findOne(postingId);
    }


    public List<PostTags> tag(){


        return postRepository.findAllt();

    }




       // 특정게시물 불러오기
//        public Post detail(){
//
//
//            return "
//        }




//        @Transactional
//        public void savePost(PostForm postForm) {
//                postRepository.save(postForm);
//
//        }
//
//        public List<PostForm> findPostings() {
//                return postRepository.findAll();
//        }






























//        public void upload(Long Posting_ID, String Company_Name,String Posting_Title,String Posting_Content,String Upload_Date, String Member_ID) {
//                PostForm postForm = postRepository.findAll();
//
//        }


//        public List<PostForm> upload() {
//                return postRepository.findAll();
//        }





}



//// @Transactional
//        public Long post(String Company_Name , String Posting_Title, String Posting_Content, String Upload_Date, String Member_Id) {
////     Post post = postRepository.findName(Company_Name);
////     Post post = postRepository.findTitle(Posting_Title);
////     Post post = postRepository.findContent(Posting_Content);
////     Post post = postRepository.findOneDate(Upload_Date);
////     Post post = postRepository.findID(Member_Id);
//
//                Post post = Post.createPost();
//                postRepository.save(post);
//                return post.getId();
// }

//        public List<Post> findOne {return PostRepository.findAllByString();}
//@Transactional
// public void CreatePost(){
//
// }



