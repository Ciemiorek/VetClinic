package com.ciemiorek.artur.vetClinic.excepiton;


import com.ciemiorek.artur.vetClinic.common.ConstErrorMsg;

public class CommonConflictException extends CommonException{
    public CommonConflictException(ConstErrorMsg constErrorMsg) {
        super(constErrorMsg);
    }
}
