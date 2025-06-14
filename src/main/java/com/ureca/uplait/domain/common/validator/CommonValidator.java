package com.ureca.uplait.domain.common.validator;

import com.ureca.uplait.global.exception.GlobalException;
import com.ureca.uplait.global.response.ResultCode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class CommonValidator {

    public void validateAllIdsExist(List<Long> ids, List<?> entities, ResultCode errorCode) {
        if (entities.size() != ids.size()) {
            throw new GlobalException(errorCode);
        }
    }

    public void validateNotDuplicated(boolean exists, ResultCode errorCode) {
        if (exists) {
            throw new GlobalException(errorCode);
        }
    }

    public <T> T getOrThrow(Supplier<Optional<T>> supplier, ResultCode errorCode) {
        return supplier.get().orElseThrow(() -> new GlobalException(errorCode));
    }
}
