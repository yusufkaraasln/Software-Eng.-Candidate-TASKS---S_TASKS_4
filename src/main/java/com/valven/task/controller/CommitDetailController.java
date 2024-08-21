package com.valven.task.controller;

import com.valven.task.entity.Commit;
import com.valven.task.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/commit")
public class CommitDetailController {

    @Autowired
    private CommitService commitService;

    @GetMapping("/{sha}")
    public String getCommitDetail(@PathVariable String sha, Model model) {
        Commit commit = commitService.findBySha(sha);

        model.addAttribute("commit", commit);
        return "commit";
    }
}
