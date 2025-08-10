package uk.gov.hmcts.reform.dev.api;

import uk.gov.hmcts.reform.dev.api.dto.TaskResponse;
import uk.gov.hmcts.reform.dev.models.TaskCase;

/**
 * Simple mapper in the web layer to avoid leaking domain out.
 */
public final class TaskMapper {
    private TaskMapper() {
    }

    public static TaskResponse toResponse(TaskCase t) {
        TaskResponse dto = new TaskResponse();
        dto.setId(t.getId());
        dto.setCaseNumber(t.getCaseNumber());
        dto.setTitle(t.getTitle());
        dto.setDescription(t.getDescription());
        dto.setStatus(t.getStatus());
        dto.setCreatedDate(t.getCreatedDate());
        dto.setDueDate(t.getDueDate());
        return dto;
    }
}