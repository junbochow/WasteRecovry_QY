package com.example.zhoujunbo.wasterecovery10;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoujunbo.wasterecovery10.util.ChooseDateInterface;
import com.example.zhoujunbo.wasterecovery10.util.ChooseDateUtil;

import org.w3c.dom.Text;

public class Order_Fragment extends Fragment {
    private Button submit_order,address_choose;
    private TextView time;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.order_fragment,null);
        Button submit_order=(Button)view.findViewById(R.id.submit_order);
        Button address_choose=(Button)view.findViewById(R.id.address_choose);
        TextView time=(TextView)view.findViewById(R.id.time) ;


        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        address_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent=new Intent(getActivity(),AdressActivity.class);
                startActivity(intent);
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDateDialog();
            }
        });
        return view;
    }
    private void dopostOrder (final String account){ //发送后台
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String state = NetUilts.loginofPost(account);
                getActivity().runOnUiThread(new Runnable() {//执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作
                        Toast.makeText(getActivity(), state, Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }).start();


    }
    public void chooseDateDialog() {
        final ChooseDateUtil dateUtil = new ChooseDateUtil();
        int[] oldDateArray = {2019, 01, 01};//默认显示的时间  可以根据业务需要自己去设置
        dateUtil.createDialog(getActivity(), oldDateArray, new ChooseDateInterface() {
            @Override
            public void sure(int[] newDateArray) {
                time.setText(newDateArray[0] + "年" + newDateArray[1] + "月" + newDateArray[2] + "日");
            }
        });
    }
}
