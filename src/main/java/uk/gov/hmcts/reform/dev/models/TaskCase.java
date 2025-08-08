package uk.gov.hmcts.reform.dev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskCase {

    private int id;
    private String caseNumber; // Optional task reference ID
    private String title; // Required
    private String description; // Optional
    private String status; // e.g., pending, in-progress, done
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;
}