package com.ruowei.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class Inflow {

    private Instant time;
    private BigDecimal in_flow;
}
