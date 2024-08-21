package com.valven.task.cron;

import com.valven.task.service.GitHubApiService;
import com.valven.task.service.GitLabApiService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommitCronJob {

    private final GitHubApiService gitHubApiService;
    private final GitLabApiService gitLabApiService;

    @PostConstruct
    public void init() {
        fetchCommits();
    }


    @Scheduled(cron = "0 0 0 1 * ?")
    public void fetchCommits() {
        gitHubApiService.fetchAndSaveCommits();

        gitLabApiService.fetchAndSaveCommits();

        log.info("Commit cron worked.");

    }
}
