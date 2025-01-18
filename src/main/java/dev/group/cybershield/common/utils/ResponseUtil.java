package dev.group.cybershield.common.utils;

import dev.group.cybershield.common.constants.Constants;
import dev.group.cybershield.common.global.ResponseDTO;
import dev.group.cybershield.entity.Questions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ResponseUtil {
    public static ResponseEntity<ResponseDTO> sendResponse(Object object, Timestamp landingTime, HttpStatus status,
                                                           long actualStatus, String type, String title){
        ResponseDTO response = new ResponseDTO();
        response.getResponseMessage().setLandingTime(landingTime);
        response.getResponseMessage().setResponseTime(Timestamp.valueOf(LocalDateTime.now()));
        response.getResponseMessage().setMessage(Constants.SUCCESS);
        response.getResponseMessage().setStatus(0);
        response.getResponseMessage().setHttpStatus(actualStatus);
        response.setTitle(title);
        response.setType(type);
        response.setResponseData(object);

        return new ResponseEntity<ResponseDTO>(response, status);
    }

    public static ResponseEntity<ResponseDTO> sendResponse(HttpHeaders headers, Object object, Timestamp landingTime, HttpStatus status,
                                                           long actualStatus, String type, String title){
        ResponseDTO response = new ResponseDTO();
        response.getResponseMessage().setLandingTime(landingTime);
        response.getResponseMessage().setResponseTime(Timestamp.valueOf(LocalDateTime.now()));
        response.getResponseMessage().setMessage(Constants.SUCCESS);
        response.getResponseMessage().setStatus(0);
        response.getResponseMessage().setHttpStatus(actualStatus);
        response.setTitle(title);
        response.setType(type);
        response.setResponseData(object);
        response.setState(title);

        return new ResponseEntity<ResponseDTO>(response, headers, status);
    }

    public static ResponseEntity<ResponseDTO> sendErrorResponse(String errorCode, String errorMessage, Timestamp landingTime, HttpStatus status,
                                                           long actualStatus, String type, String title){
        ResponseDTO errorResponse = new ResponseDTO();
        errorResponse.getResponseMessage().setLandingTime(landingTime);
        errorResponse.getResponseMessage().setResponseTime(Timestamp.valueOf(LocalDateTime.now()));
        errorResponse.getResponseMessage().setMessage(Constants.FAILURE);
        errorResponse.getErrorDto().setErrorCode(errorCode);
        errorResponse.getErrorDto().setErrorMessage(errorMessage);
        errorResponse.getResponseMessage().setStatus(1);
        errorResponse.getResponseMessage().setHttpStatus(actualStatus);

        return new ResponseEntity<ResponseDTO>(errorResponse, status);
    }

}
