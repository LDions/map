package com.ruowei.service.dto;

import lombok.Data;

@Deprecated
@Data
public class ChainCodeRequestDTO {

    private Operation chaincode_operation;

    @Data
    public static class Operation {
        private String chaincodeId;

        private String operation;

        private String functionName;

        private String args;

        public Operation(String chaincodeId, String operation, String functionName, String args) {
            this.chaincodeId = chaincodeId;
            this.operation = operation;
            this.functionName = functionName;
            this.args = args;
        }
    }
}
