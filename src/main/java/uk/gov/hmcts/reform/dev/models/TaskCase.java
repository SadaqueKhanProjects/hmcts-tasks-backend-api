package uk.gov.hmcts.reform.dev.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Domain model representing a task/case entry. Mirrors ExampleCase but adds dueDate and enum-based
 * status.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
// @formatter:off
@Table(
    name = "task_cases",
    indexes = {
      @Index(name = "idx_task_cases_status", columnList = "status"),
      @Index(name = "idx_task_cases_due_date", columnList = "due_date")
    })
// @formatter:on

public class TaskCase {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // matches SERIAL in Postgres
  private int id;

  @Column(name = "case_number", length = 64)
  private String caseNumber;

  @Column(nullable = false, length = 255)
  private String title;

  // For Postgres, TEXT is fine. For H2 tests, Hibernate maps it appropriately.
  @Column(columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING) // store enum as readable strings
  @Column(nullable = false, length = 32)
  private Status status;

  @Column(name = "created_date", nullable = false)
  private LocalDateTime createdDate;

  @Column(name = "due_date")
  private LocalDateTime dueDate;

  @PrePersist
  void onCreate() {
    if (createdDate == null) {
      createdDate = LocalDateTime.now();
    }
  }
}