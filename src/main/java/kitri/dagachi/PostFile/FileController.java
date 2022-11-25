package kitri.dagachi.File;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class FileController {




    private final FileService fileService;


    @GetMapping("/enter/upload")
    public String testUploadForm() {

        return "/post/enter_register";
    }

    @PostMapping("/test")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("files") List<MultipartFile> files) throws IOException {


        fileService.saveFile(file);


        return "/post/enterPosting";
    }
}

