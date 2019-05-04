package com.example.zhoujunbo.wasterecovery10.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoujunbo.wasterecovery10.R;

import static com.mob.MobSDK.getContext;

public class ChooseGoodsUti implements View.OnClickListener {
    Context context;
    AlertDialog dialog;
    ChooseGoodsInterface goodsInterface;
    CheckBox metal,paper,plastic,cloth;
    CheckBox kg5,kg10,kg15,kg20;
    String type="",count="0",price="0";
    TextView preview_price,tvCancel,tvSure;

    public void createDialog(Context context,ChooseGoodsInterface goodsInterface){
        this.context=context;
        this.goodsInterface=goodsInterface;

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.dialog_choose_goods, null);
        dialog = new AlertDialog.Builder(context).setView(linearLayout).create();
        dialog.show();

        //初始化
        metal=(CheckBox)linearLayout.findViewById(R.id.metal);
        paper=(CheckBox) linearLayout.findViewById(R.id.paper);
        plastic=(CheckBox) linearLayout.findViewById(R.id.plastic);
        cloth=(CheckBox) linearLayout.findViewById(R.id.cloth);
        kg5=(CheckBox) linearLayout.findViewById(R.id.kg5);
        kg10=(CheckBox) linearLayout.findViewById(R.id.kg10);
        kg15=(CheckBox) linearLayout.findViewById(R.id.kg15);
        kg20=(CheckBox) linearLayout.findViewById(R.id.kg20);
        preview_price=(TextView)linearLayout.findViewById(R.id.preview_price);
        tvCancel=(TextView)linearLayout.findViewById(R.id.tvCancel);
        tvSure=(TextView)linearLayout.findViewById(R.id.tvSure);
        tvCancel.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        metal.setOnClickListener(this);
        paper.setOnClickListener(this);
        plastic.setOnClickListener(this);
        cloth.setOnClickListener(this);
        kg5.setOnClickListener(this);
        kg10.setOnClickListener(this);
        kg15.setOnClickListener(this);
        kg20.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.metal:
                paper.setChecked(false);
                plastic.setChecked(false);
                cloth.setChecked(false);
                metal.setChecked(true);
                type=metal.getTag().toString();
                break;
            case R.id.paper:
                metal.setChecked(false);
                plastic.setChecked(false);
                cloth.setChecked(false);
                paper.setChecked(true);
                type=paper.getTag().toString();
                break;
            case R.id.plastic:
                paper.setChecked(false);
                metal.setChecked(false);
                cloth.setChecked(false);
                plastic.setChecked(true);
                type=plastic.getTag().toString();
                break;
            case R.id.cloth:
                paper.setChecked(false);
                metal.setChecked(false);
                plastic.setChecked(false);
                cloth.setChecked(true);
                type=cloth.getTag().toString();
                break;
            case R.id.kg5:
                kg10.setChecked(false);
                kg15.setChecked(false);
                kg20.setChecked(false);
                kg5.setChecked(true);
                count=kg5.getTag().toString();
                break;
            case R.id.kg10:
                kg5.setChecked(false);
                kg15.setChecked(false);
                kg20.setChecked(false);
                kg10.setChecked(true);
                count=kg10.getTag().toString();
                break;
            case R.id.kg15:
                kg10.setChecked(false);
                kg5.setChecked(false);
                kg20.setChecked(false);
                kg15.setChecked(true);
                count=kg15.getTag().toString();
                break;
            case R.id.kg20:
                kg10.setChecked(false);
                kg15.setChecked(false);
                kg5.setChecked(false);
                kg20.setChecked(true);
                count=kg20.getTag().toString();
                break;
            case R.id.tvCancel:
                dialog.dismiss();
                break;
            case R.id.tvSure:
                if(count.equals("0") || type.equals("")){
                    Toast.makeText(getContext(),"请选择回收品种类与规格数量",Toast.LENGTH_SHORT).show();break;}
                    dialog.dismiss();
                    price=setprice(type,count);
                    toCurrent(type,count);
                    goodsInterface.sure(type,count,price);
                    type="";count="0";
                    break;
        }
        preview_price.setText(setprice(type,count));
    }

    private void toCurrent(String type, String count) {
        switch (type){
            case "rd_metal":type="金属类";break;
            case "rd_paper":type="纸类";break;
            case "rd_plastic":type="塑料类";break;
            case "rd_cloth":type="纺织类";break;
        }
        switch (count){
            case "5":count="5-10千克";break;
            case "10":count="10-15千克";break;
            case "15":count="15-20千克";break;
            case "20":count="20千克以上";break;
        }
        this.type=type;
        this.count=count;
    }

    private String setprice(String type, String count) {
        int show;
        String price=preview_price.getText().toString();
        if(count.equals("0")&&type.equals(""))return price;
       switch (type){
           case "rd_metal":
               show=Integer.parseInt(count);
               show=4*show;
               price=Integer.toString(show);
               break;
           case "rd_paper":
               show=Integer.parseInt(count);
               show=2*show;
               price=Integer.toString(show);
               break;
           case "rd_plastic":
               show=Integer.parseInt(count);
               show=3*show;
               price=Integer.toString(show);
               break;
           case "rd_cloth":
               show=Integer.parseInt(count);
               show=3*show;
               price=Integer.toString(show);
               break;
       }
       return price;
    }

}
