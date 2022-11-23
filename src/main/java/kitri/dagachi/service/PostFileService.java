package kitri.dagachi.service;

import kitri.dagachi.model.Post;
import kitri.dagachi.model.PostFile;
import kitri.dagachi.repository.PostLikeRepository;
import kitri.dagachi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class PostFileService {

    @Autowired
    private final PostRepository postRepository;
    private final PostLikeRepository postlikerepository;


//    public PostFile savefile(MultipartFile file, Long postingId) throws IOException
//    {
//        String filePath = System.getProperty("user.dir") + "/src/main/resources/static/files";
//
//        UUID uuid = UUID.randomUUID();
//
//        String fileName = uuid + "_" + file.getOriginalFilename();
//
//        File saveFile = new File(filePath,fileName);
//        file.transferTo(saveFile);
//
//        PostFile postfile = postRepository.savefile(new Post(postingId, fileName));
//
//        return new PostFile(postfile);
//    }

//    public

}
