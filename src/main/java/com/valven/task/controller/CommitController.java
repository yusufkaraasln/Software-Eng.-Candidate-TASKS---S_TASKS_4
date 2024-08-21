package com.valven.task.controller;

import com.valven.task.entity.Commit;
import com.valven.task.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class CommitController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/")
    public RedirectView redirectToCommitList() {
        return new RedirectView("/commit-list");
    }


    @GetMapping("/commit-list")
    public String getAllCommits(@RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "10") int size,
                                Model model) {
        Page<Commit> commitPage = commitService.findAll(PageRequest.of(page, size));
        model.addAttribute("commitPage", commitPage);
        return "commits";
    }
}
