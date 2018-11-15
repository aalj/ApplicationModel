package net.lll0.utils.time;

import android.content.Context;
import android.support.annotation.DimenRes;

/**
 * Created by liangjun on 2018/11/14
 */
public class DimenUtils {
    /**
     * 获取dp 对应的 数值 直接舍去小数
     *
     * @param id
     * @return
     */
    public static int getDimenInt(Context context, @DimenRes int id) {
        return context.getResources().getDimensionPixelOffset(id);
    }
}
