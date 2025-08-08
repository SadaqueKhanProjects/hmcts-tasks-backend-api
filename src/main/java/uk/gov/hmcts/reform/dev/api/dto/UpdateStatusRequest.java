package uk.gov.hmcts.reform.dev.api.dto;

import jakarta.validation.constraints.NotNull;
import uk.gov.hmcts.reform.dev.models.Status;

/**
 * Payload for updating only the status field.
 */
public class UpdateStatusRequest {
    @NotNull
    private Status status;

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}