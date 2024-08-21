package com.valven.task.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GitHubCommit {

    private String sha;

    @JsonProperty("node_id")
    private String nodeId;

    private Commit commit;

    private String url;

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("comments_url")
    private String commentsUrl;


    @Data
    public static class Commit {

        private CommitInfo author;
        private CommitInfo committer;

        private String message;


        private String url;

        @JsonProperty("comment_count")
        private int commentCount;

        @Data
        public static class CommitInfo {

            private String name;

            private String email;

            private OffsetDateTime date;
        }


    }


}

