package com.ruowei.web.rest.errors;

import org.zalando.problem.Problem;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_VALIDATION = "error.validation";
    public static final URI DEFAULT_TYPE = Problem.DEFAULT_TYPE;

    private ErrorConstants() {
    }
}
