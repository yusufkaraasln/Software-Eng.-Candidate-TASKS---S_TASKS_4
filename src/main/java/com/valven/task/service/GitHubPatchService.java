package com.valven.task.service;

import com.valven.task.entity.Patch;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GitHubPatchService {

    private final RestTemplate restTemplate;

    @Value("${github.api.url}")
    private String apiUrl;

    @Value("${github.api.token}")
    private String apiToken;


    public List<String> getPatches(String sha) {
        String url = apiUrl + "/" + sha;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();

        if (responseBody == null) {
            throw new IllegalStateException("Response body is null");
        }

        List<Map<String, Object>> files = (List<Map<String, Object>>) responseBody.get("files");


        if (files == null) {
            return Collections.emptyList();
        }

        return files.stream()
                .map(file -> (String) file.get("patch"))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
