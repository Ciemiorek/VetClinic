package com.ciemiorek.artur.vetClinic.api.response;

import com.ciemiorek.artur.vetClinic.api.type.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class BasicResponse {

    private String responseMsg;
    private String errorCode;
    private String errorMsg;
    private ResponseStatus status;

    public BasicResponse(String responseMsg) {
        this.responseMsg = responseMsg;
        this.status = ResponseStatus.SUCCESS;
    }

    public BasicResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.status = ResponseStatus.ERROR;
    }


    public static BasicResponse of(String responseMsg) {
        return new BasicResponse(responseMsg);
    }

    public static BasicResponse ofError(String errorCode, String errorMsg){
        return new BasicResponse(errorCode,errorMsg);
    }
}
