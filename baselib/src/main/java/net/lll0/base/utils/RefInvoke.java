package net.lll0.base.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by liangjun on 2019/3/17
 * 对基本的反射进行封装
 */
public class RefInvoke {


    public static Object createObject(String className) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};
        try {
            Class r = Class.forName(className);
            return createObject(r, pareTypes, pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createObject(Class clazz) {
        Class[] pareTypes = new Class[]{};
        Object[] pareValues = new Object[]{};
        return createObject(clazz, pareTypes, pareValues);
    }


    public static Object createObject(String className, Class pareType, Class pareValue) {
        Class[] classes = new Class[]{pareType};
        Object[] objects = new Object[]{pareValue};
        try {
            Class aClass = Class.forName(className);
            return createObject(aClass, classes, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Object createObject(Class clazz, Class pareType, Object pareValue) {
        Class[] classes = new Class[]{pareType};
        Object[] objects = new Object[]{pareValue};
        try {
            return createObject(clazz, classes, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static Object createObject(Class clazz, Class[] pareTypes, Object[] pareValues) {
        try {
            Constructor constructor = clazz.getDeclaredConstructor(pareTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据 类名反射获取对象
     *
     * @param className
     * @param pareTypes
     * @param pareValues
     * @return
     */
    public static Object createObject(String className, Class[] pareTypes, Object[] pareValues) {
        try {
            Class r = Class.forName(className);
            Constructor constructor = r.getDeclaredConstructor(pareTypes);
            constructor.setAccessible(true);
            return constructor.newInstance(pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射调用一个实例
     *
     * @param obj
     * @param methodName
     * @param pareTypes
     * @param pareValues
     * @return
     */
    public static Object invokeInstanceMethod(Object obj, String methodName, Class[]
            pareTypes, Object[] pareValues) {
        if (obj == null) {
            return null;
        }
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName, pareTypes);
            method.setAccessible(true);
            return method.invoke(obj, pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过反射调用静态字段
     *
     * @param className
     * @param methodName
     * @param pareTypes
     * @param pareValues
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName, Class[]
            pareTypes, Object[] pareValues) {
        try {
            Class aClass = Class.forName(className);
            Method method = aClass.getDeclaredMethod(methodName, pareTypes);
            method.setAccessible(true);
            return method.invoke(null, pareValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一个字段的名字
     *
     * @param className
     * @param obj
     * @param filedName
     * @return
     */
    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class c = Class.forName(className);
            return getFieldObject(c, obj, filedName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldObject(Class clazz, Object obj, String filedName) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 设置一个字段的名称
     *
     * @param className
     * @param obj
     * @param filedName
     * @param filedValue
     */
    public static void setFieldObject(String className, Object obj, String filedName, Object
            filedValue) {
        try {
            Class c = Class.forName(className);
            setFieldObject(c, obj, filedName, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setFieldObject(Class clazz, Object obj, String filedName, Object
            filedValue) {
        try {
            Field field = clazz.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedValue);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Object getStaticFieldObject(String className, String filedName) {
        return getFieldObject(className, null, filedName);
    }

    public static Object getStaticFieldObject(Class clazz, String filedName) {
        return getFieldObject(clazz, null, filedName);
    }

    public static void setStaticFieldObject(String classname, String filedName, Object filedVaule) {
        setFieldObject(classname, null, filedName, filedVaule);
    }

    public static void setStaticFieldObject(Class clazz, String filedName, Object filedVaule) {
        setFieldObject(clazz, null, filedName, filedVaule);
    }


}
