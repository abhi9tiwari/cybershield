package dev.group.cybershield.common.global;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ResponseMessage {
    private Timestamp landingTime;
    private Timestamp responseTime;
    private String message;
    private long status;
    private long httpStatus;
}
