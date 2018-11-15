package net.lll0.utils.time;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;

import net.lll0.viewlib.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PickerUtils {
    private TimePickerView mDateTimePickerView;
    private OptionsPickerView mOtherPickerView;


    public void initCustomTimePicker(String titleText, Calendar selectDate,
                                     Activity context) {
        Calendar startDate = Calendar.getInstance();
        initCustomTimePicker(titleText, startDate, selectDate, null, context);
    }

    /**
     * 开始时间为当天
     *
     * @param titleText
     * @param onTimeSelectListener 返回监听
     * @param context
     */
    public void initCustomTimePicker(String titleText, Calendar selectDate,
                                     final MyOnTimeSelectListener onTimeSelectListener, Activity context) {
        Calendar startDate = Calendar.getInstance();
        initCustomTimePicker(titleText, startDate, selectDate, onTimeSelectListener, context);
    }

    /**
     * 日期时间选择器配置
     */
    public void initCustomTimePicker(String titleText, Calendar startDate, Calendar selectDate,
                                     final MyOnTimeSelectListener onTimeSelectListener, Activity context) {
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */

        Calendar endDate = Calendar.getInstance();
        endDate.set(TimeUtil.getCurrentYear() + 1, TimeUtil.getCurrentMonth(), TimeUtil.getDay());

        //时间选择器 ，自定义布局
        mDateTimePickerView = new TimePickerBuilder(context, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mDateTimePickerView.dismiss();
                if (onTimeSelectListener != null) {
                    onTimeSelectListener.timeSelect(date, v);
                }
            }
        })
                .setDate(selectDate)
                .setRangDate(startDate, endDate)//设置始末时间范围
//                .setLayoutRes(R.layout.pickerview_layout, v -> {
//                    //TODO
//                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "点", "分", "秒")
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setOutSideCancelable(false)
                .setSubmitText("确认")    //确定按钮文字
                .setCancelText("取消")    //取消按钮文字
                .setTitleText(titleText) //标题
                .setSubCalSize(14)       //确定和取消文字大小
                .setTitleSize(16)        //标题文字大小
                .setDividerColor(ContextCompat.getColor(context, R.color.view_FFF5F5F9))
                .setTitleColor(ContextCompat.getColor(context, R.color.view_FF333333))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.view_FF999999))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.view_FF999999))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)   //标题背景颜色 Night mode
                .setBgColor(Color.WHITE)        //滚轮背景颜色 Night mode
                .setContentTextSize(16)         //滚轮文字大小
                .isCenterLabel(false)           //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setOutSideCancelable(false)    //点击外部dismiss default true
                .isDialog(false)                //是否显示为对话框样式
                .build();
//                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
//                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
//                .gravity(Gravity.RIGHT)// default is center
        mDateTimePickerView.show();
    }

    /**
     * 不联动的多级选项
     */
    public void initNoLinkOptionsPicker(String titleText,  Activity context,
                                        List firstList, List secondList, List thirdList, boolean isLinkage,
                                        final OptionsSelect optionsSelect) {
        mOtherPickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                optionsSelect.optionsSelect(options1, options2, options3);
            }
        })
                .setSubmitText("确认")//确定按钮文字
                .setCancelText("取消")//取消按钮文字
                .setTitleText(titleText)//标题
                .setSubCalSize(14)//确定和取消文字大小
                .setTitleSize(16)//标题文字大小
                .setTitleColor(ContextCompat.getColor(context, R.color.view_FF333333))//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(context, R.color.view_FF333333))//确定按钮文字颜色
                .setCancelColor(ContextCompat.getColor(context, R.color.view_FF999999))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)   //标题背景颜色 Night mode
                .setBgColor(Color.WHITE)        //滚轮背景颜色 Night mode
                .setContentTextSize(26)         //滚轮文字大小
//                .setLabels("", "", "")        //设置选择的三级单位
                .isCenterLabel(false)           //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(false)    //点击外部dismiss default true
                .isDialog(false)                //是否显示为对话框样式
                .isRestoreItem(false)           //切换时是否还原，设置默认选中第一项。
                .setLineSpacingMultiplier(1.4f)//设置两横线之间的间隔倍数
                .build();
        if (isLinkage) {//联动
            mOtherPickerView.setPicker(firstList, secondList, thirdList);
        } else {
            mOtherPickerView.setNPicker(firstList, secondList, thirdList);
        }
        mOtherPickerView.show();
    }

    /**
     * @author curry
     * 自定义回调
     */
    public interface OptionsSelect {
        void optionsSelect(int options1, int options2, int options3);
    }


    public interface MyOnTimeSelectListener {
        void timeSelect(Date date, View v);

    }
}