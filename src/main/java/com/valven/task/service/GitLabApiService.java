package com.valven.task.service;

import com.valven.task.dto.GitLabCommit;
import com.valven.task.entity.Commit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GitLabApiService {

    private final CommitService commitService;
    private final RestTemplate restTemplate;

    String oneMonthAgo = OffsetDateTime.now().minusMonths(1).toString();

    @Value("${gitlab.api.url}")
    private String apiUrl;

    @Value("${gitlab.api.token}")
    private String apiToken;

    public void fetchAndSaveCommits() {
        String url = apiUrl + String.format("?since=%s", oneMonthAgo);
        var headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", apiToken);
        var entity = new HttpEntity<>(headers);

        ResponseEntity<GitLabCommit[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, GitLabCommit[].class);
        List<GitLabCommit> commits = Arrays.asList(response.getBody());

        commits.forEach(commit -> {
            Commit newCommit = new Commit();
            newCommit.setSha(commit.getId());
            newCommit.setMessage(commit.getMessage());
            newCommit.setAuthorName(commit.getAuthorName());
            newCommit.setAuthorEmail(commit.getAuthorEmail());
            newCommit.setCommittedDate(commit.getCommittedDate());
            newCommit.setPlatform("GitLab");
            newCommit.setUrl(commit.getWebUrl());

            commitService.saveCommitWithPatches(newCommit);
        });
    }
}
