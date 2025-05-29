package com.ureca.uplait.global.exception;

import com.ureca.uplait.global.response.ResultCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GlobalException extends RuntimeException {
    private final ResultCode resultCode;
}
