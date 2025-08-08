package uk.gov.hmcts.reform.dev.api.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uk.gov.hmcts.reform.dev.models.Status;

import java.time.LocalDateTime;

/**
 * Payload for creating a new TaskCase.
 */
public class CreateTaskRequest {
    @NotBlank
    private String title;

    private String description;

    @NotNull
    private Status status; // must be a valid enum

    @FutureOrPresent(message = "dueDate must not be in the past")
    private LocalDateTime dueDate;

    private String caseNumber; // optional

    // getters/setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    public String getCaseNumber() { return caseNumber; }
    public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }
}