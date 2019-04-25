package com.example.zhoujunbo.wasterecovery10;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static com.example.zhoujunbo.wasterecovery10.R.id.fl_container;

public class Home_Fragment extends Fragment{
    private Button show_map,order;
    private Order_Fragment order_fragment;
    private Home_Fragment home_fragment;
    private FragmentManager manager;
    private FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.home_fragment,null);
        manager = getFragmentManager();
        Button show_map=(Button) view.findViewById(R.id.show_map);
        Button order=(Button)view.findViewById(R.id.order);
        show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent=new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order_fragment==null)order_fragment=new Order_Fragment();
                ft = manager.beginTransaction();
                //当前的fragment会被myJDEditFragment替换
                ft.replace(fl_container, order_fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }

}
