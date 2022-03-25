package com.ruowei.util;

import org.apache.commons.lang3.StringUtils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringUtil {

    private static final char SEPARATOR = '_';

    /**
     * 驼峰命名法工具
     * @return
     * 		camelCase("hello_world") == "helloWorld"
     * 		capCamelCase("hello_world") == "HelloWorld"
     * 		uncamelCase("helloWorld") = "hello_world"
     */
    public static String camelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = i != 1; // 不允许第二个字符是大写
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static Boolean symbolArrayStrBlank(String s) {
        return StringUtils.isBlank(s) || "[]".equals(s);
    }

    /**
     * @param s
     * @return
     * @apiNote author: czz; 判断水务年报中 数据是否为空/-  计算时使用
     */
    public static Boolean cellEmpty(String s) {
        return StringUtils.isBlank(s) || "-".equals(s);
    }

    /**
     * @param s
     * @return
     * @apiNote author: czz; not blank[null or empty], not "-"
     */
    public static Boolean cellNotEmpty(String s) {
        return !cellEmpty(s);
    }

    /**
     * @apiNote author: zk 随机生成8位字母数字混合密码
     */
    private static final String lowStr = "abcdefghijklmnopqrstuvwxyz";
    private static final String numStr = "0123456789";

    // 随机获取字符串字符
    private static char getRandomChar(String str) {
        SecureRandom random = new SecureRandom();
        return str.charAt(random.nextInt(str.length()));
    }

    // 随机获取小写字符
    private static char getLowChar() {
        return getRandomChar(lowStr);
    }

    // 随机获取大写字符
    private static char getUpperChar() {
        return Character.toUpperCase(getLowChar());
    }

    // 随机获取数字字符
    private static char getNumChar() {
        return getRandomChar(numStr);
    }

    //指定调用字符函数
    private static char getRandomChar(int funNum) {
        switch (funNum) {
            case 0:
                return getLowChar();
            case 1:
                return getUpperChar();
            case 2:
                return getNumChar();
            default:
                return getNumChar();
        }
    }

    // 指定长度，随机生成复杂密码
    public static String getRandomPwd() {
        List<Character> list = new ArrayList<>(8);
        list.add(getLowChar());
        list.add(getUpperChar());
        list.add(getNumChar());

        for (int i = 3; i < 8; i++) {
            SecureRandom random = new SecureRandom();
            int funNum = random.nextInt(4);
            list.add(getRandomChar(funNum));
        }

        Collections.shuffle(list); // 打乱排序
        StringBuilder stringBuilder = new StringBuilder(list.size());
        for (Character c : list) {
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public static List<String> split(String str, String separator) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isNotBlank(str)) {
            String[] strs = str.split(separator);
            for (String s : strs) {
                if (StringUtils.isNotBlank(s)) {
                    list.add(s);
                }
            }
        }
        return list;
    }
}
