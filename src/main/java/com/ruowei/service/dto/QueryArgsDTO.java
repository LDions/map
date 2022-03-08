package com.ruowei.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Deprecated
@Data
public class QueryArgsDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private SelectorDTO selector;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> fields;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Map<String, String>> sort;

    @Data
    public static class SelectorDTO {
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String docType;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> documentCode;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private Long enterpriseId;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> enterpriseName;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private String industryCode;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> accYear;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> accMonth;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> reporterName;

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Map<String, String> reportTime;
    }
}
