package uk.gov.hmcts.reform.dev.controllers;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.status;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.api.TaskMapper;
import uk.gov.hmcts.reform.dev.api.dto.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.api.dto.TaskResponse;
import uk.gov.hmcts.reform.dev.api.dto.UpdateStatusRequest;
import uk.gov.hmcts.reform.dev.models.TaskCase;
import uk.gov.hmcts.reform.dev.service.TaskService;

@Validated
@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaskResponse> create(@Valid @RequestBody CreateTaskRequest req) {
    TaskCase created =
        taskService.create(
            req.getTitle(),
            req.getDescription(),
            req.getStatus(),
            req.getDueDate(),
            req.getCaseNumber());
    return status(HttpStatus.CREATED).body(TaskMapper.toResponse(created));
  }

  @GetMapping
  public List<TaskResponse> list() {
    return taskService.getAll().stream().map(TaskMapper::toResponse).collect(toList());
  }

  @GetMapping("/{id}")
  public TaskResponse getById(@PathVariable int id) {
    return TaskMapper.toResponse(taskService.getById(id));
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable int id) {
    taskService.delete(id);
  }

  @PatchMapping(path = "/{id}/status", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<TaskResponse> updateStatus(
      @PathVariable int id, @Valid @RequestBody UpdateStatusRequest req) {
    TaskCase updated = taskService.updateStatus(id, req.getStatus());
    return ResponseEntity.ok(TaskMapper.toResponse(updated));
  }
}