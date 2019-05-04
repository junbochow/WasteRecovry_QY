package com.example.zhoujunbo.wasterecovery10;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoujunbo.wasterecovery10.Adapter.Adapter_Goods;
import com.example.zhoujunbo.wasterecovery10.mode.Goods;
import com.example.zhoujunbo.wasterecovery10.util.ChooseCityInterface;
import com.example.zhoujunbo.wasterecovery10.util.ChooseCityUtil;
import com.example.zhoujunbo.wasterecovery10.util.ChooseDateInterface;
import com.example.zhoujunbo.wasterecovery10.util.ChooseDateUtil;
import com.example.zhoujunbo.wasterecovery10.util.ChooseGoodsInterface;
import com.example.zhoujunbo.wasterecovery10.util.ChooseGoodsUti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Order_Fragment extends Fragment {
     private Context mContext;
     Button submit_order,click_add;
     TextView book_time,add_city;
     EditText add_name,add_postname,add_num;
    private List<Goods> goods_items=new ArrayList<>();
    RecyclerView recyclerView;

     Calendar calendar = Calendar.getInstance();
     int year = calendar.get(Calendar.YEAR);
     int month = calendar.get(Calendar.MONTH)+1;
     int day = calendar.get(Calendar.DAY_OF_MONTH);
     String Date ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, null);
        submit_order = (Button) view.findViewById(R.id.submit_order);
        click_add=(Button)view.findViewById(R.id.click_add);
        book_time = (TextView) view.findViewById(R.id.book_time);
        add_city=(TextView)view.findViewById(R.id.add_city) ;
        add_name=(EditText)view.findViewById(R.id.add_name);
        add_num=(EditText)view.findViewById(R.id.add_num);
        add_postname=(EditText)view.findViewById(R.id.add_postnum);
        final RecyclerView recyclerView =  (RecyclerView) view.findViewById(R.id.recycler_goods);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final Adapter_Goods adapter=new Adapter_Goods(goods_items,this.getActivity());
        recyclerView.setAdapter(adapter);

        click_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ChooseGoodsUti goodsUtil=new ChooseGoodsUti();
                goodsUtil.createDialog(getActivity(), new ChooseGoodsInterface() {
                    @Override
                    public void sure(String type,String count,String price) {
                        Goods data=new Goods(type,count,price);
                        adapter.add(data);
                    }
                });
            }
        });
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNotBlank()) {
                    showconfirm();
                }
            }
        });
        book_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateDialog();
            }
        });
        add_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseCityDialog();
            }
        });
        return view;
    }


    public void chooseDateDialog() {
        final ChooseDateUtil dateUtil = new ChooseDateUtil();
        int[] oldDateArray = {0, 8, 00};//默认显示的时间  可以根据业务需要自己去设置
        dateUtil.createDialog(getActivity(), oldDateArray, new ChooseDateInterface() {
            @Override
            public void sure(int[] newDateArray) {
                 int Min = newDateArray[2];
                 day=day+newDateArray[0];
                 switch (Min){
                     case 0:Min=0;break;
                     case 1:Min=30;break;
                 }
                 Date =year+"年"+month+"月"+day+"日  ";
                book_time.setText(Date + tsDay(newDateArray[0]) + "     " + newDateArray[1] + "时" + Min + "分");
            }
        });
    }
    public void chooseCityDialog() {
        final ChooseCityUtil cityUtil = new ChooseCityUtil();
        String[] oldCityArray = add_city.getText().toString().split("-");
        if(oldCityArray[1].equals("市")){
            oldCityArray[0]="浙江";
            oldCityArray[1]="杭州";
            oldCityArray[2]="西湖";
        }
        cityUtil.createDialog(getActivity(), oldCityArray, new ChooseCityInterface() {
            @Override
            public void sure(String[] newCityArray) {
                add_city.setText(newCityArray[0] + "-" + newCityArray[1] + "-" + newCityArray[2]);
            }
        });
    }
    private String tsDay(int i) {
        String day = null;
        if(i==0){day="今天";}
        if(i==1){day="明天";}
        if(i==2){day="后天";}
        return day;
    }
    private boolean isNotBlank() {
        String name = add_name.getText().toString();
        String num = add_num.getText().toString();
        String postname = add_postname.getText().toString();
        String city = add_city.getText().toString();
        String time = book_time.getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(mContext, "联系人不能为空", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(isMatchLength(num, 11)
                && isMobileNO(num))) {
            Toast.makeText(mContext, "请输入正确的手机号码！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (city.equals("选择省-市-区")) {
            Toast.makeText(mContext, "请选择所在城市！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (postname.isEmpty()) {
            Toast.makeText(mContext, "请填写详细地址！", Toast.LENGTH_SHORT).show();
            return false;
        }else if(time.equals("")){
            Toast.makeText(mContext, "请选择预约时间！", Toast.LENGTH_SHORT).show();
            return false;
        }else return true;
    }


    //判断一个字符串的位数
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }
    //验证手机格式
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }
    //确认框
    private void showconfirm(){
        final AlertDialog.Builder alterDiaglog = new AlertDialog.Builder(getActivity());
        alterDiaglog.setTitle("确认订单");
        alterDiaglog.setMessage("是否确认提交");
        alterDiaglog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"取消",Toast.LENGTH_SHORT).show();
            }
        });
        alterDiaglog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"确认",Toast.LENGTH_SHORT).show();
            }
        });

        alterDiaglog.show();
    }
}

