package uk.gov.hmcts.reform.dev.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;
import uk.gov.hmcts.reform.dev.models.TaskCase;

/** Thread-safe in-memory repository for fast prototyping. */
@Repository
public class InMemoryTaskRepository implements TaskRepository {

  private final Map<Integer, TaskCase> store = new ConcurrentHashMap<>();
  private final AtomicInteger sequence = new AtomicInteger(1);

  @Override
  public TaskCase save(TaskCase task) {
    if (task.getId() == 0) {
      task.setId(sequence.getAndIncrement());
    }
    store.put(task.getId(), task);
    return task;
  }

  @Override
  public List<TaskCase> findAll() {
    return new ArrayList<>(store.values());
  }

  @Override
  public Optional<TaskCase> findById(int id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public void deleteById(int id) {
    store.remove(id);
  }
}
