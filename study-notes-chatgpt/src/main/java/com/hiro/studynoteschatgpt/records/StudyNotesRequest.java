package com.hiro.studynoteschatgpt.records;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record StudyNotesRequest(String model, String prompt,
        Double temperature, Integer maxTokens, Double topP,
        Double frequencyPenalty, Double presencePenalty) {
        
}