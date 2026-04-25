package com.aleksandr.ticketaisystem.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service // service to call Hugging Face API
public class HuggingFaceService {

    @Value("${huggingface.api.url}")
    private String apiUrl; // API endpoint

    @Value("${huggingface.api.key}")
    private String apiKey; // API token

    @Value("${huggingface.model}")
    private String model; // model name

    private final RestTemplate restTemplate = new RestTemplate(); // http client

    // send text to AI and get answer
    public String analyzeText(String text) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(apiKey); // auth with token
            headers.setContentType(MediaType.APPLICATION_JSON);

            // prompt for AI
            String prompt = """
                    Analyze this user comment and return ONLY JSON:
                    {
                      "shouldCreateTicket": true,
                      "title": "...",
                      "category": "bug/account/billing/feature/other",
                      "priority": "low/medium/high",
                      "summary": "..."
                    }

                    Comment: %s
                    """.formatted(text);

            // req body
            Map<String, Object> body = Map.of(
                    "model", model,
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)
                    ),
                    "max_tokens", 200
            );

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

            // send req to API
            ResponseEntity<String> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    request,
                    String.class
            );

            return response.getBody(); // return AI response

        } catch (Exception e) {
            // fallback if API fails
            System.out.println("Hugging Face API failed: " + e.getMessage());
            return "AI unavailable, local logic used";
        }
    }
}