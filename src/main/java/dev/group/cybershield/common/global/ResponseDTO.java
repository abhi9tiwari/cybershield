package dev.group.cybershield.common.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

    private ResponseMessage responseMessage = new ResponseMessage();
    private Object responseData;
    private ErrorDTO errorDto = new ErrorDTO();
    private String type;
    private String title;
    private String state;
}
