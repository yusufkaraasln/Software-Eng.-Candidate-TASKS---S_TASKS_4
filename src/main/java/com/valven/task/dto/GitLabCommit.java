package com.valven.task.dto;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GitLabCommit {

    private String id;

    @JsonProperty("short_id")
    private String shortId;

    @JsonProperty("created_at")
    private String createdAt;

    private String title;

    private String message;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_email")
    private String authorEmail;

    @JsonProperty("committer_name")
    private String committerName;

    @JsonProperty("committer_email")
    private String committerEmail;

    @JsonProperty("committed_date")
    private OffsetDateTime committedDate;

    @JsonProperty("web_url")
    private String webUrl;

    @JsonProperty("parent_ids")
    private List<String> parentIds;
}
