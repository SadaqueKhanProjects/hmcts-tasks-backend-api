package uk.gov.hmcts.reform.dev.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.models.Status;
import uk.gov.hmcts.reform.dev.models.TaskCase;
import uk.gov.hmcts.reform.dev.repo.SpringDataTaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

  private final SpringDataTaskRepository repo;

  public TaskServiceImpl(SpringDataTaskRepository repo) {
    this.repo = repo;
  }

  @Override
  public TaskCase create(
      String title, String description, Status status, LocalDateTime dueDate, String caseNumber) {
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
    return repo.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
  }

  @Override
  public TaskCase updateStatus(int id, Status newStatus) {
    TaskCase task = getById(id);
    task.setStatus(newStatus);
    return repo.save(task);
  }

  @Override
  public void delete(int id) {
    getById(id);
    repo.deleteById(id);
  }
}
