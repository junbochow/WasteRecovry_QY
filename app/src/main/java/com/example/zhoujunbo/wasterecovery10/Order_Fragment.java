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
import android.widget.Toast;

public class Order_Fragment extends Fragment {
    private Button submit_order;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.order_fragment,null);
        Button submit_order=(Button)view.findViewById(R.id.submit_order);
        submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
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
}
