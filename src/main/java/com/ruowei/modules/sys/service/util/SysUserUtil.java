package com.ruowei.modules.sys.service.util;

import com.ruowei.config.SpringUtil;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author 刘东奇
 * @date 2019/10/22
 */
public class SysUserUtil {
    private final static String USER_CODE_REGEX = "^user\\d{5}$";
    private final static Pattern USER_CODE_PATTERN = Pattern.compile("^user");

    private static final int DEF_COUNT = 20;
    /**
     * 生成不重复的用户编码
     * @author 刘东奇
     * @date 2019/10/22
     * @param
     */
    public static String generateUserCode(){
        return UUID.randomUUID().toString();
    }

}
