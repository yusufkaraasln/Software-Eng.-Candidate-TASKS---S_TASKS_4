package com.valven.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class GitLabPatchService {

    private final RestTemplate restTemplate;

    @Value("${gitlab.api.url}")
    private String apiUrl;

    @Value("${gitlab.api.token}")
    private String apiToken;


    public List<String> getPatches(String sha) {

        List<String> patchArr = null;

        String url = apiUrl + String.format("/%s/diff", sha);
        HttpHeaders headers = new HttpHeaders();
        headers.set("PRIVATE-TOKEN", apiToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
        Object responseBody = response.getBody();

        if (responseBody == null) throw new IllegalStateException("Response body is null");


        if (responseBody instanceof List) {

            List<Map<String, Object>> files = (List<Map<String, Object>>) responseBody;

            patchArr = files.stream()
                    .map(file -> (String) file.get("diff"))
                    .collect(Collectors.toList());

        } else if (responseBody instanceof Map) {

            Map<String, Object> file = (Map<String, Object>) responseBody;
            String diff = (String) file.get("diff");

            patchArr = Collections.singletonList(diff);
        }

        return patchArr;
    }


}
