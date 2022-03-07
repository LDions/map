package com.ruowei.common.log;

import org.apache.commons.lang3.StringUtils;

/**
 * 日志提示语构建工具
 * @author 刘东奇
 * @date 2019/9/8
 */
public class LogMessageUtils {
    public static String getEnterMessage(String methodName, Object ...varargs){
        methodName= StringUtils.trimToEmpty(methodName);
        String parameterString="";
        if(varargs != null) {
            for(Object param:varargs){
                if(param!=null){
                    parameterString+= StringUtils.trimToEmpty(param.toString());
                    parameterString+=",";
                }
            }
        }
        String message="进入方法["+methodName+"],携带参数["+parameterString+"]";
        return message;
    }

    public static String getLeaveMessage(String methodName, Object returnValue){
        methodName= StringUtils.trimToEmpty(methodName);
        String returnValueString="";
        if(returnValue != null){
            returnValueString+= StringUtils.trimToEmpty(returnValue.toString());
        }
        String message="离开方法["+methodName+"],携带返回值["+returnValueString+"]";
        return message;
    }
}
