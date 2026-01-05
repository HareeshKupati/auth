package com.hbk.auth.advice;

import com.hbk.auth.exception.LatestTokenNotFoundException;
import com.hbk.corefw.dto.CoreDTO;
import com.hbk.corefw.dto.DTOWrapper;
import com.hbk.corefw.dto.Error;
import com.hbk.corefw.exception.ResourceNotFoundException;
import com.hbk.corefw.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<DTOWrapper<CoreDTO>> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DTOWrapper<CoreDTO>(new Error(null,e.getMessage(), null, null,null)));
    }

}
