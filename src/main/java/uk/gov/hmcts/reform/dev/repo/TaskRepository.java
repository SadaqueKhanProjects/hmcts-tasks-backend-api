package uk.gov.hmcts.reform.dev.repo;

import uk.gov.hmcts.reform.dev.models.TaskCase;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    TaskCase save(TaskCase task);
    List<TaskCase> findAll();
    Optional<TaskCase> findById(int id);
    void deleteById(int id);
}