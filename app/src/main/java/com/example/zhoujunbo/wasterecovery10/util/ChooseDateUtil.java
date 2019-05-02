package com.example.zhoujunbo.wasterecovery10.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.zhoujunbo.wasterecovery10.R;

import java.lang.reflect.Field;
import java.util.Calendar;

import static android.app.PendingIntent.getActivity;
import static com.mob.MobSDK.getContext;


/**
 * huoqiang
 *
 * 2016.10.29
 */
public class ChooseDateUtil implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    Context context;
    AlertDialog dialog;
    ChooseDateInterface dateInterface;
    NumberPicker npDay, npHour, npMin;
    TextView tvCancel, tvSure;
    int[] newDateArray = new int[3];

    public void createDialog(Context context, int[] oldDateArray, ChooseDateInterface dateInterface) {
        this.context = context;
        this.dateInterface = dateInterface;
        newDateArray[0] = oldDateArray[0];
        newDateArray[1] = oldDateArray[1];
        newDateArray[2] = oldDateArray[2];
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_choose_date, null);
        dialog = new AlertDialog.Builder(context).setView(linearLayout).create();
        dialog.show();
//        Window window = dialog.getWindow();
//        window.setContentView(R.layout.dialog_choose_date);
        //初始化控件
        tvCancel = (TextView) linearLayout.findViewById(R.id.tvCancel);
        tvSure = (TextView) linearLayout.findViewById(R.id.tvSure);
        tvCancel.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        npDay = (NumberPicker) linearLayout.findViewById(R.id.npDay);
        npHour = (NumberPicker) linearLayout.findViewById(R.id.npHour);
        npMin = (NumberPicker) linearLayout.findViewById(R.id.npMin);
        //设置选择器最小值、最大值
        npHour.setMinValue(8);
        npHour.setMaxValue(22);
        //设置选择器初始值
        npDay.setValue(oldDateArray[0]);
        npHour.setValue(oldDateArray[1]);
        npMin.setValue(oldDateArray[2]);
        //设置监听
        npDay.setOnValueChangedListener(this);
        npHour.setOnValueChangedListener(this);
        npMin.setOnValueChangedListener(this);
        //去除分割线
        setNumberPickerDividerColor(npDay);
        setNumberPickerDividerColor(npHour);
        setNumberPickerDividerColor(npMin);
        //设置字体颜色
//        setNumberPickerTextColor(npYear, Color.parseColor("#1BC47A"));
        setNumberPickerTextColor(npDay, context.getResources().getColor(R.color.black));
        setNumberPickerTextColor(npHour, context.getResources().getColor(R.color.black));
        setNumberPickerTextColor(npMin, context.getResources().getColor(R.color.black));
        //设置不可编辑 不可滚动
        npDay.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        npHour.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        npMin.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        npHour.setWrapSelectorWheel(false);

        //日期：设置选择器最小值、最大值、初始值
        String[] npcelectDay = new String[3];//初始化日数组
        npcelectDay[0]="今天";npcelectDay[1]="明天";npcelectDay[2]="后天";//日数组填充数据
        npDay.setDisplayedValues(npcelectDay);//设置选择器数据、默认值
        npDay.setMinValue(getMinDay());
        npDay.setMaxValue(npcelectDay.length-1);
        for (int i = 0; i < npcelectDay.length; i++) {
            if (npcelectDay[i].equals(newDateArray[0])) {
                npDay.setValue(i);
            }
            //设置分钟选择器
            String[] npcelectMin = new String[2];
            npcelectMin[0]="0";
            npcelectMin[1]="30";
            npMin.setDisplayedValues(npcelectMin);
            npMin.setMinValue(0);
            npMin.setMaxValue(npcelectMin.length-1);

        }
    }

    private int getMinDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour>=22) {
            return 1;
        }
        else return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvCancel:
                dialog.dismiss();
                break;
            case R.id.tvSure:
                dialog.dismiss();
                dateInterface.sure(newDateArray);
                break;
        }
    }

    //选择器选择值监听
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.npDay:
                newDateArray[0] = newVal;
                break;
            case R.id.npHour:
                newDateArray[1] = newVal;
                break;
            case R.id.npMin:
                newDateArray[2] = newVal;
                break;
        }
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.text_yellow)));// pf.set(picker, new Div)
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        boolean result = false;
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    result = true;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
