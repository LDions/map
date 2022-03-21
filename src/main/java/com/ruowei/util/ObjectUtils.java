package com.ruowei.util;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 *
 * @author ThinkGem
 * @version 2018-08-11
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    /**
     * 从源对象向目标对象复制属性，忽略源对象的null和空字符串
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null || StringUtils.isEmpty(String.valueOf(srcValue))) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 转换为Double类型
     */
    public static Double toDouble(final Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return NumberUtils.toDouble(StringUtils.trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(final Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(final Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(final Object val) {
        return toLong(val).intValue();
    }

    /**
     * 转换为Boolean类型 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }

    /**
     * 转换为字符串
     *
     * @param obj
     * @return
     */
    public static String toString(final Object obj) {
        return toString(obj, StringUtils.EMPTY);
    }

    /**
     * 如果对象为空，则使用defaultVal值
     *
     * @param obj
     * @param defaultVal
     * @return
     */
    public static String toString(final Object obj, final String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }

    /**
     * 空转空字符串（"" to "" ; null to "" ; "null" to "" ; "NULL" to "" ; "Null" to ""）
     *
     * @param val 需转换的值
     * @return 返回转换后的值
     */
    public static String toStringIgnoreNull(final Object val) {
        return ObjectUtils.toStringIgnoreNull(val, StringUtils.EMPTY);
    }

    /**
     * 空对象转空字符串 （"" to defaultVal ; null to defaultVal ; "null" to defaultVal ; "NULL" to defaultVal ; "Null" to defaultVal）
     *
     * @param val        需转换的值
     * @param defaultVal 默认值
     * @return 返回转换后的值
     */
    public static String toStringIgnoreNull(final Object val, String defaultVal) {
        String str = ObjectUtils.toString(val);
        return !"".equals(str) && !"null".equalsIgnoreCase(str.trim()) ? str : defaultVal;
    }

    /**
     * 注解到对象复制，只复制能匹配上的方法。 硕正组件用。
     *
     * @param annotation
     * @param object
     */
    public static void annotationToObject(Object annotation, Object object) {
        if (annotation != null && object != null) {
            Class<?> annotationClass = annotation.getClass();
            Class<?> objectClass = object.getClass();
            for (Method m : objectClass.getMethods()) {
                if (StringUtils.startsWith(m.getName(), "set")) {
                    try {
                        String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
                        Object obj = annotationClass.getMethod(s).invoke(annotation);
                        if (obj != null && !"".equals(obj.toString())) {
                            //							if (object == null){
                            //								object = objectClass.newInstance();
                            //							}
                            m.invoke(object, obj);
                        }
                    } catch (Exception e) {
                        // 忽略所有设置失败方法
                    }
                }
            }
        }
    }

    /**
     * 获取对象的空属性
     */
    public static String[] getNullProperties(Object src) {
        //1.获取Bean
        BeanWrapper srcBean = new BeanWrapperImpl(src);
        //2.获取Bean的属性描述
        PropertyDescriptor[] pds = srcBean.getPropertyDescriptors();
        //3.获取Bean的空属性
        Set<String> properties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : pds) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = srcBean.getPropertyValue(propertyName);
            if (propertyValue == null) {
                srcBean.setPropertyValue(propertyName, null);
                properties.add(propertyName);
            }
        }
        return properties.toArray(new String[0]);
    }

    /**
     * 是否允许更新
     * 如果非ID字段有非空值，那么可更新，否则不可更新
     *
     * @param obj
     * @return
     * @author 刘东奇
     * @date 2019/11/14
     */
    public static Boolean isUpdatable(Object obj) {
        //查询出对象所有的属性
        Field[] fields = obj.getClass().getDeclaredFields();
        //用于判断所有属性是否为空,如果参数为空则不查询
        boolean flag = false;
        for (Field field : fields) {
            //不检查 直接取值
            field.setAccessible(true);
            try {
                if (null != field.get(obj) && !"id".equals(field.getName())) {
                    //不为空
                    flag = true;
                    //当有任何一个参数不为空的时候则跳出判断直接查询
                    break;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
}
