package com.ruowei.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Inflow {

    private DateTimeFormat time;
    private int in_flow;
}
