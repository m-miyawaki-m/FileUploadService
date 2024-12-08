package com.example.fileuploadservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileViewController {

    @GetMapping("/upload")
    public String showUploadPage(Model model) {
        model.addAttribute("pageTitle", "File Upload");
        return "upload/uploadForm"; // templates/upload/upload.html を返す
    }

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("pageTitle", "File Search");
        return "search/searchForm"; // templates/search/searchForm.html を返す
    }
    
    @GetMapping("/deleteConfirmation")
    public String deleteConfirmationPage() {
        return "delete/deleteForm"; // `deleteForm.html` を返す
    }
}
