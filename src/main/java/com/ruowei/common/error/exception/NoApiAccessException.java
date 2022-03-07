package com.ruowei.common.error.exception;

import com.ruowei.common.error.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NoApiAccessException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public NoApiAccessException() {
        super(ErrorConstants.DEFAULT_TYPE, "无接口访问权限", Status.FORBIDDEN);
    }
}
