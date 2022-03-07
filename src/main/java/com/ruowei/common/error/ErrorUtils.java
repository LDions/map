package com.ruowei.common.error;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/*
 *
 * @author 刘东奇
 * @date 2019/9/6
 */
public class ErrorUtils {

    public static String getNotFoundMessage(String entityName,String id){
        String message="找不到ID为 "+id+" 的"+entityName+"。";
        return message;
    }

    public static String getAlreadyExistMessage(String entityName,String id){
        String message="ID为 "+id+" 的"+entityName+"已存在。";
        return message;
    }

//    public static String getNotFoundMessage(String entityName,Map<String,String> queryAndConditionMap,Map<String,String> queryOrConditionMap){
//        String message="找不到";
//        Boolean isFirst=true;
//        if(queryAndConditionMap!=null&&queryAndConditionMap.size()>0){
//            if(!isFirst){
//                message+="且"
//            }
//        }
//        for (Map.Entry<String, String> entry : queryConditionMap.entrySet()) {
//            if(StringUtils.isNotEmpty(entry.getKey())){
//                message+=entry.getKey();
//                message+="=";
//            }
//            System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
//        }
//        String message="找不到ID为 "+id+" 的"+entityName;
//        return message;
//    }
}
