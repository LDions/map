package com.ruowei.config;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String ADMIN = "admin";
    public static final String DEFAULT_LANGUAGE = "zh-cn";

    public static final String DEFAULT_PASSWORD = "123456";

    public static final String COMPOST = "COMPOST";
    public static final String BIOGAS = "BIOGAS";
    public static final String LANDFILL = "LANDFILL";

    public static final String INRETURNFLOW = "INRETURNFLOW";
    public static final String OUTRETURNFLOW = "OUTRETURNFLOW";
    public static final String FIRSTMUD = "FIRSTMUD";
    public static final String SECONDMUD = "SECONDMUD";
    public static final String REFLUX = "REFLUX";
    public static final String CARADD = "CARADD";
    public static final String INRETURNPUMP = "INRETURNPUMP";
    public static final String OUTRETURNPUMP = "OUTRETURNPUMP";
    public static final String FANSTATE = "FANSTATE";

    public static final String SEWAGE = "SEWAGE";

    public static final String SEW_TREAT = "SEW_TREAT";

    public static final String SYS_ADMIN = "SYS_ADMIN";

    private Constants() {
        IdGeneratorOptions options = new IdGeneratorOptions();
        options.BaseTime = 1609459200000L;
        options.WorkerIdBitLength = 1;
        options.SeqBitLength = 3;
        YitIdHelper.setIdGenerator(options);
    }
}
