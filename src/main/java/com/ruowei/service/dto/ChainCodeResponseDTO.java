package com.ruowei.service.dto;

import lombok.Data;

@Deprecated
@Data
public class ChainCodeResponseDTO {

    private Boolean success;

    private String message;

    private String result;
}
