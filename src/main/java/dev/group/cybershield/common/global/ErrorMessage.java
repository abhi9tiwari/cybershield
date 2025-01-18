package dev.group.cybershield.common.global;

import java.sql.Time;
import java.sql.Timestamp;

public class ErrorMessage {

    private String serviceUrl;
    private String message;
    private String messageDetails;
    private Timestamp landingTime;
    private Time responseTime;
    private Integer status;
}
