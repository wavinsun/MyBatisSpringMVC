package cn.mutils.web.mybatis.common.page;

import org.apache.ibatis.binding.MapperMethod;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

/**
 * Created by wenhua.ywh on 2016/11/21.
 */
public class InterceptorUtil {

    public static <T> Page<T> getPageParam(Object parameter) {
        if (parameter instanceof Page) {
            return (Page<T>) parameter;
        } else if (parameter instanceof MapperMethod.ParamMap) {
            for (Map.Entry<String, ?> entry : ((MapperMethod.ParamMap<?>) parameter).entrySet()) {
                Object value = entry.getValue();
                if (value instanceof Page) {
                    return (Page<T>) value;
                }
            }
        }
        return null;
    }

    public static int queryTotalCount(String sql, Connection connection) throws Throwable {
        String countSql = "select count(1) c from ( " + sql + " ) t";
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            statement = connection.prepareStatement(countSql);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (statement != null) {
                statement.close();
            }
        }
        return 0;
    }

    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                break;
            } catch (Exception e) {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
            }
        }
        return field;
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        Field field = getField(obj, fieldName);
        if (field == null) {
            return null;
        }
        try {
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
        Field field = getField(obj, fieldName);
        if (field == null) {
            return;
        }
        try {
            field.set(obj, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
