package com.example.jpm.librarydemo.tools.util;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.bigkoo.pickerview.listener.CustomListener;
import com.example.jpm.librarydemo.tools.base.ResUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PickViewUtil {

    public static TimePickerView tpvCustomTime;
    private static OptionsPickerView tpvOptions = null;

    public static void initCustomTimePicker(final int type, final Context context,
                                            final onTimeSelectListener listener) {
        boolean[] booleans = null;
        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        boolean flag = true;
        switch (type) {
            case 1:
                booleans = new boolean[]{true, true, true, false, false, false};
                break;
            case 3:
                booleans = new boolean[]{true, true, false, false, false, false};
                break;
            case 5:
                booleans = new boolean[]{true, false, false, false, false, false};
                flag = false;
                break;
        }
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        tpvCustomTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                listener.onTimeSelect(date, type);
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
                /*.gravity(Gravity.RIGHT)// default is center*/
                .isCyclic(true)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setLayoutRes(ResUtil.getLayoutId(context, "pickerview_custom_time"), new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = v.findViewById(ResUtil.getId(context, "tv_finish"));
                        ImageView ivCancel = v.findViewById(ResUtil.getId(context, "iv_cancel"));
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tpvCustomTime.returnData();
                                tpvCustomTime.dismiss();
                                tpvCustomTime = null;
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tpvCustomTime.dismiss();
                                tpvCustomTime = null;
                            }
                        });
                    }
                })
                .setContentSize(18)
                .setType(booleans)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(flag) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .build();
        tpvCustomTime.show();
    }

    public static void initOptionPicker(final int i, Context context, final onTimeSelectListener listener,
                                        final List<String> list1, final List<String> list2, List<String> list3) {//条件选择器初始化

        /**
         * 注意 ：如果是三级联动的数据(省市区等)，请参照 JsonDataActivity 类里面的写法。
         */
        String type = "";
        boolean cycle = true;
        int secondoption = -1;
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        int year = selectedDate.get(Calendar.YEAR) - 2010;
        int month = selectedDate.get(Calendar.MONTH);
        Log.i("lxurl", list1.get(1));
        switch (i) {
            case 2:
                type = "周";
                cycle = true;
                secondoption = selectedDate.get(Calendar.WEEK_OF_YEAR);
                break;
            case 4:
                type = "季度";
                cycle = false;
                if (month >= 1 && month <= 3) {
                    secondoption = 0;
                } else if (month > 3 && month <= 6) {
                    secondoption = 1;
                } else if (month > 6 && month <= 9) {
                    secondoption = 2;
                } else {
                    secondoption = 3;
                }

                break;
        }


        tpvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String describeString = "";
                switch (i) {
                    case 2:
                        describeString = list1.get(options1) + "年第"
                                + list2.get(options2) + "周" + "周报";
                        break;
                    case 4:
                        describeString = list1.get(options1) + "年"
                                + list2.get(options2) + "季度" + "季报";
                        break;
                }
                listener.onObjectSelect(describeString, i);
                tpvOptions = null;
            }
        })
                .setTitleText("时间选择")
                .setContentTextSize(20)//设置滚轮文字大小
                .setDividerColor(Color.LTGRAY)//设置分割线的颜色
                .setSelectOptions(year, secondoption)//默认选中项
                .setBgColor(Color.WHITE)
                .setTitleBgColor(Color.DKGRAY)
                .setTitleColor(Color.LTGRAY)
                .setCancelColor(Color.YELLOW)
                .setSubmitColor(Color.YELLOW)
                .setTextColorCenter(Color.LTGRAY)
                .setCyclic(true, cycle, true)
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setLabels("年", type, "")
                .setBackgroundId(0x66000000) //设置外部遮罩颜色
                .build();
        tpvOptions.setNPicker(list1, list2, null);
        tpvOptions.show();
    }

    public static interface onTimeSelectListener {
        void onTimeSelect(Date date, int type);

        void onObjectSelect(String describe, int type);
    }
}