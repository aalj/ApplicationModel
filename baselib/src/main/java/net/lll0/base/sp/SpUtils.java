package net.lll0.base.sp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by liangjun on 2018/11/12
 * SharedPreferences 操作类
 */
public class SpUtils {

    private static SpUtils spUtils;
    private final String fileName = "data";


    private SharedPreferences sharedPreferences;

    private SpUtils() {
    }

    public SpUtils instance() {
        if (spUtils == null) {
            synchronized (SpUtils.class) {
                if (spUtils == null) {
                    spUtils = new SpUtils();
                }
            }
        }
        return spUtils;
    }

    /**
     * 创建sharePreferences 实例
     *
     * @param context
     * @return
     */
    private SharedPreferences getSharedPreferences(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public boolean saveInt(Context context, String key, int value) {
        getSharedPreferences(context);
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    public boolean saveString(Context context, String key, String value) {
        getSharedPreferences(context);
        return sharedPreferences.edit().putString(key, value).commit();
    }

    public boolean saveFloat(Context context, String key, float value) {
        getSharedPreferences(context);
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    public boolean saveBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context);
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    public boolean remove(Context context, String key) {
        getSharedPreferences(context);
        return sharedPreferences.edit().remove(key).commit();
    }

    public int getInt(Context context, String key) {
        getSharedPreferences(context);
        return sharedPreferences.getInt(key, 0);
    }

    public String getString(Context context, String key) {
        getSharedPreferences(context);
        return sharedPreferences.getString(key, "");
    }

    public float getFloat(Context context, String key) {
        getSharedPreferences(context);
        return sharedPreferences.getFloat(key, 0F);
    }

    public boolean getBoolean(Context context, String key) {
        getSharedPreferences(context);
        return sharedPreferences.getBoolean(key, false);
    }

}
