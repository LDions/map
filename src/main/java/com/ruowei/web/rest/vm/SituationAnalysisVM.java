package com.ruowei.web.rest.vm;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;

@Data
public class SituationAnalysisVM {

    private BigDecimal value;

    private Calendar time;
}
