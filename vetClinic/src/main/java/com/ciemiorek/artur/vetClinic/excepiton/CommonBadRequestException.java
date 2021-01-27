package com.ciemiorek.artur.vetClinic.excepiton;


import com.ciemiorek.artur.vetClinic.common.ConstErrorMsg;

public class CommonBadRequestException extends CommonException {

    public CommonBadRequestException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
