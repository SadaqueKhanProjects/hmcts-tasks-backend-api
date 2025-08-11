package uk.gov.hmcts.reform.dev.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;

public enum Status {
    PENDING,
    IN_PROGRESS,
    COMPLETED; 

    /**
     * Ensures case-insensitive deserialization and clear error handling.
     */
    @JsonCreator
    public static Status fromJson(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty");
        }

        try {
            return Status.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                "Invalid status value: '" + value + "'. " +
                "Allowed values are: " + Arrays.toString(Status.values())
            );
        }
    }
}