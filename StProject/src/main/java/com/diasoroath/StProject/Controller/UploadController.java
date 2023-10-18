package com.diasoroath.StProject.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.*;


@RestController
public class UploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPhoto(@RequestBody Map<String, Object> payload) throws IOException {
        String base64Photo = (String) payload.get("photoByte64");
        String userId = (String) payload.get("userID");
        String reportId = (String) payload.get("reportID");


        String pythonServiceUrl = "http://localhost:5000/process";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestPayload = new HashMap<>();
        requestPayload.put("photoByte64", base64Photo);
        requestPayload.put("userID", userId);
        requestPayload.put("reportID", reportId);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestPayload, headers);
        ResponseEntity<String> response = new RestTemplate().exchange(
                pythonServiceUrl, HttpMethod.POST, request, String.class);

        System.out.println(response);

        return response;
    }
}



