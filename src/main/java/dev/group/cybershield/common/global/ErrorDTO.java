package dev.group.cybershield.common.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDTO {
    private Object responseMessage;
    private String errorCode;
    private String errorMessage;
    private Timestamp landingTime;
    private Timestamp responseTime;
    private Integer status;
}
