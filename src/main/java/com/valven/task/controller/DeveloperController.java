package com.valven.task.controller;

import com.valven.task.entity.Commit;
import com.valven.task.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/{authorName}")
    public String getCommitsByAuthor(
            @PathVariable String authorName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<Commit> commitPage = commitService.findCommitsByAuthor(authorName, PageRequest.of(page, size));
        model.addAttribute("authorName", authorName);
        String authorEmail = commitPage.getContent().isEmpty() ? "N/A" : commitPage.getContent().get(0).getAuthorEmail();
        model.addAttribute("authorEmail", authorEmail);
        model.addAttribute("commitPage", commitPage);

        return "developer";
    }
}


