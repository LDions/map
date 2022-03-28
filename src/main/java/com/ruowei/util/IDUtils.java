package com.ruowei.util;

public class IDUtils {

    private final static IdWorker idWorker = new IdWorker(1, 0);

    /**
     * 生成全局唯一编号
     * @return
     */
    public static String codeGenerator(){
        long id= idWorker.nextId();
        String orderCode=String.valueOf(id);
        return orderCode;
    }
}
