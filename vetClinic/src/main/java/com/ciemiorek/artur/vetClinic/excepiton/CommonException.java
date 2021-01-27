package com.ciemiorek.artur.vetClinic.excepiton;

import com.ciemiorek.artur.vetClinic.common.ConstErrorMsg;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CommonException extends RuntimeException {
    private ConstErrorMsg constErrorMsg;

}
