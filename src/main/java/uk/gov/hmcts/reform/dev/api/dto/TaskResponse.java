package uk.gov.hmcts.reform.dev.api.dto;

import java.time.LocalDateTime;
import uk.gov.hmcts.reform.dev.models.Status;

public class TaskResponse {

  private int id;
  private String caseNumber;
  private String title;
  private String description;
  private Status status;
  private LocalDateTime createdDate;
  private LocalDateTime dueDate;

  public TaskResponse() {}

  public TaskResponse(
      int id,
      String caseNumber,
      String title,
      String description,
      Status status,
      LocalDateTime createdDate,
      LocalDateTime dueDate) {
    this.id = id;
    this.caseNumber = caseNumber;
    this.title = title;
    this.description = description;
    this.status = status;
    this.createdDate = createdDate;
    this.dueDate = dueDate;
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getCaseNumber() { return caseNumber; }
  public void setCaseNumber(String caseNumber) { this.caseNumber = caseNumber; }

  public String getTitle() { return title; }
  public void setTitle(String title) { this.title = title; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public Status getStatus() { return status; }
  public void setStatus(Status status) { this.status = status; }

  public LocalDateTime getCreatedDate() { return createdDate; }
  public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }

  public LocalDateTime getDueDate() { return dueDate; }
  public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
}