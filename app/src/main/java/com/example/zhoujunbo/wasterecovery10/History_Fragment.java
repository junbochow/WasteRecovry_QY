package com.example.zhoujunbo.wasterecovery10;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhoujunbo.wasterecovery10.Adapter.Adapter_History;
import com.example.zhoujunbo.wasterecovery10.mode.History;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class History_Fragment extends Fragment {

    private List<History> history_items = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history__fragment, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_history);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        final Adapter_History adapter = new Adapter_History(history_items,this.getActivity());
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.removeData();
                        initHisItems();
                        adapter.addData(history_items);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
            }
        });

        return view;
    }


    private void doPostHis(final String token) {
        final String id = token;
        new Thread(new Runnable() {
            @Override
            public void run() {
                //String 转 Json
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("token", id);
                    jsonParam.put("command", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json串转string类型
                String data = String.valueOf(jsonParam);
                getActivity().runOnUiThread(new Runnable() {  //执行任务在主线程中
                    @Override
                    public void run() {//就是在主线程中操作
                        initHisItems();
                    }
                });
            }

        }).start();


    }

    private void initHisItems() {
        History add1 = new History("20152213321093", "取货中", "2019-05-03 12:30", "周波", "15957124172", "陈行", "14958275492","",
                "金属类","5-10千克","40元","","","","","","","","","");
        history_items.add(add1);
        History add2 = new History("20152242321093", "已完成", "2019-05-01 15:30", "均波", "15957124172", "王行", "14958275492","",
                "金属类","5-10千克","40元","金属类","5-10千克","40元","金属类","5-10千克","40元","","","");
        history_items.add(add2);
        History add3 = new History("20152424621093", "已完成", "2019-02-03 12:30", "周均波", "15957124172", "王陈行", "14958275492","",
                "纺织类","5-10千克","40元","纸类","5-10千克","40元","塑料类","5-10千克","40元","金属类","5-10千克","40元");
        history_items.add(add3);

    }

}


