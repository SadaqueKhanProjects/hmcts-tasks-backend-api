package uk.gov.hmcts.reform.dev.service;

import uk.gov.hmcts.reform.dev.models.Status;
import uk.gov.hmcts.reform.dev.models.TaskCase;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    TaskCase create(String title, String description, Status status, LocalDateTime dueDate, String caseNumber);

    List<TaskCase> getAll();

    TaskCase getById(int id);

    TaskCase updateStatus(int id, Status newStatus);

    void delete(int id);
}