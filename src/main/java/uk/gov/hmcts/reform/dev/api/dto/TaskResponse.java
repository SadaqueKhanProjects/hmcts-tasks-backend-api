package uk.gov.hmcts.reform.dev.api.dto;

import uk.gov.hmcts.reform.dev.models.Status;

import java.time.LocalDateTime;

/**
 * Outbound DTO so you can change domain without breaking API clients.
 */
public class TaskResponse {
    private int id;
    private String caseNumber;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdDate;
    private LocalDateTime dueDate;

    // getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}