package com.valven.task.service;

import com.valven.task.dto.GitHubCommit;
import com.valven.task.entity.Commit;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GitHubApiService {


    private final CommitService commitService;
    private final RestTemplate restTemplate;

    String oneMonthAgo = OffsetDateTime.now().minusMonths(1).toString();

    @Value("${github.api.url}")
    private String apiUrl;

    @Value("${github.api.token}")
    private String apiToken;

    public void fetchAndSaveCommits() {
        String url = apiUrl + String.format("?since=%s", oneMonthAgo);
        var headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        var entity = new HttpEntity<>(headers);


        ResponseEntity<GitHubCommit[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, GitHubCommit[].class);
        List<GitHubCommit> commits = Arrays.asList(response.getBody());


        commits.forEach(commit -> {
            Commit newCommit = new Commit();
            newCommit.setSha(commit.getSha());
            newCommit.setMessage(commit.getCommit().getMessage());
            newCommit.setAuthorName(commit.getCommit().getAuthor().getName());
            newCommit.setAuthorEmail(commit.getCommit().getAuthor().getEmail());
            newCommit.setCommittedDate(commit.getCommit().getCommitter().getDate());
            newCommit.setPlatform("GitHub");
            newCommit.setUrl(commit.getHtmlUrl());

            commitService.saveCommitWithPatches(newCommit);
        });
    }
}
