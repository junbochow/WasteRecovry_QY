package com.example.zhoujunbo.wasterecovery10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zhoujunbo.wasterecovery10.Adapter.Adapter;
import com.example.zhoujunbo.wasterecovery10.mode.Mine;

import java.util.ArrayList;
import java.util.List;

public class Mine_Fragment extends Fragment {

    private List<Mine> mine_items=new ArrayList<>();
    private List<Mine> mine_items2=new ArrayList<>();
    Button btn_quit;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_fragment,container,false);
        btn_quit=(Button)view.findViewById(R.id.btn_quit);
        btn_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除用户登录记录
                SharedPreferences sharedPreferences= getActivity().getSharedPreferences("Token", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        if(mine_items.size()==0) initItems();
        if(mine_items2.size()==0) initItems2();

        RecyclerView recyclerView =  (RecyclerView) view.findViewById(R.id.recycler_mine);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter=new Adapter(mine_items);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView2 =  (RecyclerView) view.findViewById(R.id.recycler_mine2);
        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        Adapter adapter2=new Adapter(mine_items2);
        recyclerView2.setAdapter(adapter2);
        return view;

    }
    private void initItems(){

            Mine address=new Mine("企业信息",R.drawable.mine_address);
            mine_items.add(address);
//            Mine order=new Mine("历史订单",R.drawable.mine_order);
//            mine_items.add(order);
//            Mine coins=new Mine("收入统计",R.drawable.mine_coins);
//            mine_items.add(coins);

    }
    private void initItems2(){

        Mine setting=new Mine("系统设置",R.drawable.mine_setting);
        mine_items2.add(setting);
        Mine suggestion=new Mine("举报反馈",R.drawable.mine_suggestion);
        mine_items2.add(suggestion);
        Mine about=new Mine("关于我们",R.drawable.mine_about);
        mine_items2.add(about);
    }
}
