package com.valven.task.service;

import com.valven.task.entity.Commit;
import com.valven.task.entity.Patch;
import com.valven.task.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CommitService {

    @Autowired
    private CommitRepository commitRepository;

    @Autowired
    private GitHubPatchService gitHubPatchService;

    @Autowired
    private GitLabPatchService gitLabPatchService;


    public Page<Commit> findAll(Pageable pageable) {
        return commitRepository.findAll(pageable);
    }
    public Page<Commit> findCommitsByAuthor(String authorName, Pageable pageable) {
        return commitRepository.findByAuthorName(authorName, pageable);
    }


    public Commit findBySha(String sha) {
        return commitRepository.findBySha(sha);
    }

    public void saveCommitWithPatches(Commit commit) {

        if (commit.getPatches() == null) {
            commit.setPatches(new ArrayList<>());
        }

        List<String> patchList = fetchPatchContents(commit.getSha(), commit.getPlatform());

        patchList.forEach((patchContent) -> {
            Patch patch = new Patch();
            patch.setPatchContent(patchContent);
            patch.setCommit(commit);
            commit.getPatches().add(patch);
        });
        commitRepository.save(commit);
    }


    private List<String> fetchPatchContents(String sha, String platform) {

        List<String> patchContent = null;

        if (platform == "GitHub") {

            patchContent = gitHubPatchService.getPatches(sha);
        } else {

            patchContent = gitLabPatchService.getPatches(sha);
        }

        return patchContent;
    }


}
