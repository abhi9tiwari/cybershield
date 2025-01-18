package dev.group.cybershield.common.exception;

import dev.group.cybershield.common.global.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Utility method to build error responses
    private ResponseEntity<ErrorDTO> buildErrorResponse(String errorCode, String errorMessage, HttpStatus status) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode(errorCode);
        errorDTO.setErrorMessage(errorMessage);
        errorDTO.setLandingTime(Timestamp.from(Instant.now()));
        errorDTO.setResponseTime(Timestamp.from(Instant.now()));
        errorDTO.setStatus(status.value());

        return new ResponseEntity<>(errorDTO, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex) {
        return buildErrorResponse("BAD_REQUEST", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<ErrorDTO> handleNotFoundExceptions(Exception ex) {
        String errorMessage = (ex instanceof NoHandlerFoundException)
                ? "The requested URL was not found: " + ((NoHandlerFoundException) ex).getRequestURL()
                : ex.getMessage();

        return buildErrorResponse("NOT_FOUND", errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse(ex.getMessage());

        return buildErrorResponse("VALIDATION_ERROR", errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneralException(Exception ex) {
        return buildErrorResponse("INTERNAL_SERVER_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
