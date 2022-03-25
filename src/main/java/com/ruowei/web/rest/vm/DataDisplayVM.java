package com.ruowei.web.rest.vm;

import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class DataDisplayVM {

    private Long id;

    private String groupName;

    private String name;

    private String craftName;

    private Instant dayTime;
}
