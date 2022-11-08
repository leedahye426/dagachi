package kitri.dagachi.service;

import kitri.dagachi.controller.PostForm;
//import kitri.dagachi.repository.FileRepository;
import kitri.dagachi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class postService {
        private final PostRepository postRepository;

        @Transactional
        public void savePost(PostForm postForm) {
                postRepository.save(postForm);
        }

        public List<PostForm> findPostings() {
                return postRepository.findAll();
        }

//        public void upload(Long Posting_ID, String Company_Name,String Posting_Title,String Posting_Content,String Upload_Date, String Member_ID) {
//                PostForm postForm = postRepository.findAll();
//
//        }


        public List<PostForm> upload() {
                return postRepository.findAll();
        }





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



