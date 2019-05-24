package com.example.zhoujunbo.wasterecovery10;

import android.content.SharedPreferences;
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

import com.example.zhoujunbo.wasterecovery10.Adapter.Adapter_Order;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Body;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Summary;
import com.example.zhoujunbo.wasterecovery10.util.NetUilts;
import com.example.zhoujunbo.wasterecovery10.util.OrderDataHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class History_Fragment extends Fragment {

    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;
    Adapter_Order adapter;
    private List<Object> mAllOrderList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history__fragment, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_history);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if(mAllOrderList==null) {
            initHisItems();
        }
        adapter= new Adapter_Order(getActivity(),mAllOrderList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.removeData();
//                        DoHistoryPost();
                        initHisItems();
                        adapter.notifyDataSetChanged();
                    }
                },100);
            }
        });

        return view;
    }



    private void DoHistoryPost() {
        SharedPreferences sp = getActivity().getSharedPreferences("Token", 0);
        final String token=sp.getString("token","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //String 转 Json
                JSONObject jsonParam = new JSONObject();
                try {
                    jsonParam.put("token", token);
                    jsonParam.put("command", 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //json串转string类型
                String data = String.valueOf(jsonParam);
                NetUilts.DoPost(data,"history","");
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
        Order_Summary order_summary1=new Order_Summary();
        Order_Body order_body1=new Order_Body();
        Order_Body order_body2=new Order_Body();
        Order_Body order_body3=new Order_Body();
        order_summary1.setID("213213123124");
        order_summary1.setCollectorName("张三");
        order_summary1.setCollectorNum("15823124932");
        order_summary1.setCustmerName("周博");
        order_summary1.setCustmerNum("15957124170");
        order_summary1.setState("0");
        order_summary1.setTime("2019年5月7日 16：30");
        order_summary1.setTotal("42");
        order_summary1.setWhatsmore("无");
        order_body1.setGoods("金属类");
        order_body1.setCount("3");
        order_body1.setPrice("10");
        order_body2.setGoods("塑料类");
        order_body2.setCount("6");
        order_body2.setPrice("20");
        order_body3.setGoods("纸类");
        order_body3.setCount("5");
        order_body3.setPrice("12");
        List<Order_Body> order_bodyList=new ArrayList<>();
        order_bodyList.add(order_body1);
        order_bodyList.add(order_body2);
        order_bodyList.add(order_body3);

        order_summary1.setOrder_bodies(order_bodyList);
        List<Order_Summary> order_summaryList=new ArrayList<>();
        order_summaryList.add(order_summary1);

        mAllOrderList.addAll(OrderDataHelper.getDataAfterHandle(order_summaryList));
    }

}


