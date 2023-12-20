package com.hiro.studynoteschatgpt.servicies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.hiro.studynoteschatgpt.records.StudyNotesRequest;
import com.hiro.studynoteschatgpt.records.StudyNotesResponse;

import reactor.core.publisher.Mono;

@Service
public class StudyNotesServiceChatGPT {

    private WebClient webClient;
    
    public StudyNotesServiceChatGPT(WebClient.Builder builder, @Value("${openai.api.key}") String apiKey) {
        this.webClient = builder
                .baseUrl("https://api.openai.com/v1/completions")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Authorization", String.format("Bearer %s", apiKey))
                .build();
    }

    public Mono<StudyNotesResponse> createStudyNotes(String topic) {
        StudyNotesRequest request = createStudyRequest(topic);

        return webClient.post().bodyValue(request)
                .retrieve()
                .bodyToMono(StudyNotesResponse.class);
    }

    private StudyNotesRequest createStudyRequest(String topic) {
        String question = "Quais são os pontos chave que devo estudar sobre o seguinte assunto: " + topic;

        return new StudyNotesRequest("text-davinci-003", question, 0.3, 2000, 1.0, 0.0, 0.0);
    }

}