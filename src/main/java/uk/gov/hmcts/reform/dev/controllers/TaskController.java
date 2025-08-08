public class TaskController {

}
package uk.gov.hmcts.reform.dev.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.reform.dev.api.dto.CreateTaskRequest;
import uk.gov.hmcts.reform.dev.api.dto.UpdateStatusRequest;
import uk.gov.hmcts.reform.dev.models.TaskCase;
import uk.gov.hmcts.reform.dev.service.TaskService;

import java.util.List;

/**
 * REST API for TaskCase management.
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskCase> create(@Valid @RequestBody CreateTaskRequest req) {
        TaskCase created = service.create(
                req.getTitle(),
                req.getDescription(),
                req.getStatus(),
                req.getDueDate(),
                req.getCaseNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<TaskCase> list() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TaskCase getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/status")
    public TaskCase updateStatus(@PathVariable int id, @Valid @RequestBody UpdateStatusRequest req) {
        return service.updateStatus(id, req.getStatus());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        service.delete(id);
    }
}