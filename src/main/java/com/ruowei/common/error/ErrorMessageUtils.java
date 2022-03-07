package com.ruowei.common.error;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 错误提示语构建工具
 * @author 刘东奇
 * @date 2019/9/6
 */
public class ErrorMessageUtils {

    public static String getNotFoundMessage(String entityName, String id){
        entityName= StringUtils.trimToEmpty(entityName);
        id= StringUtils.trimToEmpty(id);
        String message="找不到ID为 "+id+" 的"+entityName+"。";
        return message;
    }

    public static String getAlreadyExistMessage(String entityName, String id){
        entityName= StringUtils.trimToEmpty(entityName);
        id= StringUtils.trimToEmpty(id);
        String message="ID为 "+id+" 的"+entityName+"已存在。";
        return message;
    }

    public static String getAlreadyExistMessageByAndMap(String entityName, Map<String,String> map){

        String message="";
        Boolean isFirst=true;
        if(map != null && map.size() > 0){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(!isFirst){
                    message+=" 且 ";
                }
                if(StringUtils.isNotEmpty(entry.getKey())){
                    message += entry.getKey();
                    message += "=";
                }
                message += entry.getValue();
                isFirst = false;
            }
        }
        message += "的" + entityName + "已存在";
        return message;
    }

    public static String getAlreadyExistMessageByOrMap(String entityName, Map<String,String> map){
        String message="";
        Boolean isFirst=true;
        if(map != null && map.size() > 0){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if(!isFirst){
                    message+=" 或 ";
                }
                if(StringUtils.isNotEmpty(entry.getKey())){
                    message += entry.getKey();
                    message += "=";
                }
                message += entry.getValue();
                isFirst = false;
            }
        }
        message += "的" + entityName + "已存在";
        return message;
    }

    public static String getDataInvalidMessage(String entityName, String id,String expectData,String actualData){
        entityName= StringUtils.trimToEmpty(entityName);
        id= StringUtils.trimToEmpty(id);
        String message="ID为 "+id+" 的"+entityName+"数据错误。期望数据："+expectData+"|实际数据："+actualData;
        return message;
    }
}
