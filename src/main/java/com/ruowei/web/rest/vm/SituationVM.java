package com.ruowei.web.rest.vm;

import lombok.Data;

import java.util.List;

@Data
public class SituationVM {

    private String target;

    private List<SituationAnalysisVM> values;

}
