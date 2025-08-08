package uk.gov.hmcts.reform.dev.service;

import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.models.Status;
import uk.gov.hmcts.reform.dev.models.TaskCase;
import uk.gov.hmcts.reform.dev.repo.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Business logic for TaskCase.
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public TaskCase create(String title, String description, Status status, LocalDateTime dueDate, String caseNumber) {
        TaskCase task = new TaskCase();
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status);
        task.setDueDate(dueDate);
        task.setCaseNumber(caseNumber);
        task.setCreatedDate(LocalDateTime.now());
        return repo.save(task);
    }

    @Override
    public List<TaskCase> getAll() {
        return repo.findAll();
    }

    @Override
    public TaskCase getById(int id) {
        return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    }

    @Override
    public TaskCase updateStatus(int id, Status status) {
        TaskCase existing = getById(id);
        existing.setStatus(status);
        return repo.save(existing);
    }

    @Override
    public void delete(int id) {
        // ensure it exists (surfacing 404 vs silent success)
        getById(id);
        repo.deleteById(id);
    }
}