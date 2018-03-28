package com.prolancer.essaymarker.Controller;


import com.prolancer.essaymarker.db.model.FileDetail;
import com.prolancer.essaymarker.model.view.CommonMessage;
import com.prolancer.essaymarker.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * A controller to test file upload and download
 * should move function to other controller (user, admin) after
 */
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model) {
        try {
            fileService.uploadFile(multipartFile);
        } catch (IllegalArgumentException exception){
            return returnMessage(model, "failed to upload file");
        }

        return returnMessage(model, "file uploaded");
    }

    private String returnMessage(Model model, String message) {
        CommonMessage commonMessage = new CommonMessage();
        commonMessage.setMessage(message);
        model.addAttribute("commonMessage", commonMessage);
        return "message";
    }
}
