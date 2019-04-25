package com.example.zhoujunbo.wasterecovery10;

import android.content.Intent;
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
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class Mine_Fragment extends Fragment {

    private List<Mine> mine_items=new ArrayList<>();
    private List<Mine> mine_items2=new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_fragment,container,false);
        if(mine_items.size()==0) initItems();
        if(mine_items2.size()==0) initItems2();
        RecyclerView recyclerView =  (RecyclerView) view.findViewById(R.id.recycler_mine);
        RecyclerView recyclerView2 =  (RecyclerView) view.findViewById(R.id.recycler_mine2);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView2.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        LinearLayoutManager layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        Adapter adapter=new Adapter(mine_items);
        Adapter adapter2=new Adapter(mine_items2);
        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        return view;

    }

    private void initItems(){

            Mine address=new Mine("个人信息",R.drawable.mine_address);
            mine_items.add(address);
            Mine order=new Mine("历史订单",R.drawable.mine_order);
            mine_items.add(order);
            Mine coins=new Mine("积分管理",R.drawable.mine_coins);
            mine_items.add(coins);

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
