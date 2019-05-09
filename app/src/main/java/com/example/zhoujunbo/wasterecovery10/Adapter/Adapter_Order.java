package com.example.zhoujunbo.wasterecovery10.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zhoujunbo.wasterecovery10.R;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Body;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Foot;
import com.example.zhoujunbo.wasterecovery10.mode.Order_Head;

import java.util.List;

public class Adapter_Order extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<Object> data;
    private View headerView;
    private int ITEM_HEADER = 1,ITEM_CONTENT = 2,ITEM_FOOTER = 3;

    public Adapter_Order(Context context,List<Object>data){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == ITEM_HEADER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_order_head, parent, false);
            return new Adapter_Order.MyViewHolderHeader(view);
        }else if(viewType == ITEM_CONTENT){
            view = LayoutInflater.from(context).inflate(R.layout.item_order_body, parent, false);
            return new Adapter_Order.MyViewHolderContent(view);
        }else if(viewType == ITEM_FOOTER){
            view = LayoutInflater.from(context).inflate(R.layout.item_order_foot, parent, false);
            return new Adapter_Order.MyViewHolderFooter(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolderHeader){
            Order_Head datas = (Order_Head) data.get(position);
            ((MyViewHolderHeader) holder).order_ID.setText(datas.getID());
            ((MyViewHolderHeader) holder).order_time.setText(datas.getTime());
            ((MyViewHolderHeader) holder).order_collectorname.setText(datas.getCollectorName());
            ((MyViewHolderHeader) holder).order_collectornum.setText(datas.getCollectorNum());
            ((MyViewHolderHeader) holder).order_custmername.setText(datas.getCustmerName());
            ((MyViewHolderHeader) holder).order_custmernum.setText(datas.getCustmerNum());

            if(datas.getState().equals("0")){
                ((MyViewHolderHeader) holder).order_state.setText("待接单");
            }else if(datas.getState().equals("1")){
                ((Adapter_Order.MyViewHolderHeader) holder).order_state.setText("待取货");
            }else if(datas.getState().equals("2")){
                ((Adapter_Order.MyViewHolderHeader) holder).order_state.setText("待确认");
            }else if(datas.getState().equals("3")){
                ((Adapter_Order.MyViewHolderHeader) holder).order_state.setText("交易完成");
            }

        }else if(holder instanceof MyViewHolderContent) {
            Order_Body datas = (Order_Body) data.get(position);
            ((MyViewHolderContent) holder).goods.setText(datas.getGoods());
            ((MyViewHolderContent) holder).count.setText(datas.getCount()+"千克");
            ((MyViewHolderContent) holder).price.setText("¥" + datas.getPrice()+"元");

        }else if(holder instanceof MyViewHolderFooter) {
            Order_Foot datas = (Order_Foot) data.get(position);
            ((MyViewHolderFooter) holder).whatsmore.setText(datas.getWhatsmore() + "");
            ((MyViewHolderFooter) holder).Total.setText(datas.getTotal());
            if(datas.getState().equals("3")){
                ((MyViewHolderFooter) holder).cancel.setClickable(false);
                ((MyViewHolderFooter)holder).cancel.setVisibility(View.GONE);
            }
            ((MyViewHolderFooter) holder).cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            ((MyViewHolderFooter) holder).seein.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    @Override
    public int getItemCount() {
        int count = (data == null ? 0 : data.size());
        if(headerView != null){
            count++;
        }
        return count;
    }

    public int getItemViewType(int position) {
        if(data.get(position) instanceof Order_Head) {
            return ITEM_HEADER;
        }else if(data.get(position) instanceof Order_Body){
            return ITEM_CONTENT;
        }else if(data.get(position) instanceof Order_Foot){
            return ITEM_FOOTER;
        }
        return ITEM_CONTENT;
    }

    public void removeData() {
        data.removeAll(data);
        this.notifyItemRangeChanged(0, data.size());
    }

    public class MyViewHolderHeader extends RecyclerView.ViewHolder {
        TextView order_custmernum,order_custmername,order_collectorname,order_collectornum;
        TextView order_state,order_time,order_ID;
        public MyViewHolderHeader(View view) {
            super(view);
            order_time=(TextView)view.findViewById(R.id.order_time) ;
            order_ID=(TextView)view.findViewById(R.id.order_ID) ;
            order_custmername=(TextView)view.findViewById(R.id.order_custmername);
            order_custmernum=(TextView)view.findViewById(R.id.order_custmernum);
            order_collectorname=(TextView)view.findViewById(R.id.order_collectorname);
            order_collectornum=(TextView)view.findViewById(R.id.order_collectornum);
            order_state=(TextView)view.findViewById(R.id.order_state);
        }
    }

    public class MyViewHolderContent extends RecyclerView.ViewHolder {
        TextView goods,count,price;
        public MyViewHolderContent(View view) {
            super(view);
            goods=(TextView)view.findViewById(R.id.goods);
            price=(TextView)view.findViewById(R.id.price);
            count=(TextView)view.findViewById(R.id.count);
        }
    }

    public class MyViewHolderFooter extends RecyclerView.ViewHolder {
        TextView whatsmore,Total;
        Button cancel,seein;
        public MyViewHolderFooter(View view) {
            super(view);
            whatsmore=(TextView)view.findViewById(R.id.order_whatsmore);
            Total=(TextView)view.findViewById(R.id.Total);
            cancel=(Button)view.findViewById(R.id.cancel);
            seein=(Button)view.findViewById(R.id.seein);
        }
    }

    public View getHeaderView(){
        return headerView;
    }

}
