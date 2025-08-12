package uk.gov.hmcts.reform.dev.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.gov.hmcts.reform.dev.api.NotFoundException;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private Map<String, Object> body(HttpStatus status, String error, String message, String path) {
    Map<String, Object> map = new HashMap<>();
    map.put("timestamp", OffsetDateTime.now().toString());
    map.put("status", status.value());
    map.put("error", error);
    if (message != null) map.put("message", message);
    if (path != null) map.put("path", path);
    return map;
  }

  // 404 for our domain "not found"
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(body(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), req.getRequestURI()));
  }

  // 400 for invalid arguments thrown by our code
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(body(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), req.getRequestURI()));
  }

  // 400 for bean validation (@Valid) errors
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleMethodArgNotValid(MethodArgumentNotValidException ex, HttpServletRequest req) {
    Map<String, Object> payload = body(HttpStatus.BAD_REQUEST, "Bad Request", "Validation failed", req.getRequestURI());
    Map<String, String> fieldErrors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(fe -> fieldErrors.put(fe.getField(), fe.getDefaultMessage()));
    payload.put("fieldErrors", fieldErrors);
    return ResponseEntity.badRequest().body(payload);
  }

  // 400 for javax/jakarta validation exceptions outside @Valid binding
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Map<String, Object>> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest req) {
    return ResponseEntity.badRequest()
        .body(body(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), req.getRequestURI()));
  }

  // 400 for JSON parse errors (e.g., bad enum value)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Map<String, Object>> handleNotReadable(HttpMessageNotReadableException ex, HttpServletRequest req) {
    // Keep it friendly; don’t leak long parser traces
    String message = "Malformed request body or invalid value (e.g., status).";
    return ResponseEntity.badRequest()
        .body(body(HttpStatus.BAD_REQUEST, "Bad Request", message, req.getRequestURI()));
  }
}