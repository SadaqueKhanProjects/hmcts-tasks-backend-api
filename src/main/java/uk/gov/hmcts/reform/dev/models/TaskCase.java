package uk.gov.hmcts.reform.dev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Domain model representing a task/case entry.
 * Mirrors ExampleCase but adds dueDate and enum-based status.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskCase {
    private int id;
    private String caseNumber; // Optional human-friendly ref
    private String title; // Required
    private String description; // Optional
    private Status status; // PENDING, IN_PROGRESS, DONE
    private LocalDateTime createdDate; // Set on create
    private LocalDateTime dueDate; // Optional
}