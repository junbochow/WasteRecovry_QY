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
import com.example.zhoujunbo.wasterecovery10.mode.History;

import java.util.List;

public class Adapter_History extends RecyclerView.Adapter<Adapter_History.ViewHolder> implements View.OnClickListener  {
    private List<History> history_items;//数据源
    Context context;//上下文

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();//getTag()获取数据
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView order_custmernum,order_custmername,order_collectorname,order_collectornum;
        TextView order_state,order_time,order_ID,order_whatsmore;
        TextView goods1,count1,price1,goods2,count2,price2,goods3,count3,price3,goods4,count4,price4;
        Button seein;
        public ViewHolder(@NonNull View view) {
            super(view);
            order_time=(TextView)view.findViewById(R.id.order_time) ;
            order_ID=(TextView)view.findViewById(R.id.order_ID) ;
            order_custmername=(TextView)view.findViewById(R.id.order_custmername);
            order_custmernum=(TextView)view.findViewById(R.id.order_custmernum);
            order_collectorname=(TextView)view.findViewById(R.id.order_collectorname);
            order_collectornum=(TextView)view.findViewById(R.id.order_collectornum);


            seein=(Button)view.findViewById(R.id.seein);
            order_state=(TextView)view.findViewById(R.id.order_state);
            order_whatsmore=(TextView)view.findViewById(R.id.order_whatsmore);

            seein.setOnClickListener(Adapter_History.this);
        }
    }

    public Adapter_History(List<History> list_items,Context context){
        history_items=list_items;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_History.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_head,parent,false);
        Adapter_History.ViewHolder holder =new Adapter_History.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_History.ViewHolder holder, int position) {
        History historyitem=history_items.get(position);
        holder.order_collectorname.setText(historyitem.getCollectorName());
        holder.order_collectornum.setText(historyitem.getCollectorNum());
        holder.order_custmername.setText(historyitem.getCustmerName());
        holder.order_custmernum.setText(historyitem.getCustmerNum());
        holder.order_ID.setText(historyitem.getID());
        holder.order_state.setText(historyitem.getState());
        holder.order_time.setText(historyitem.getTime());
        if(!historyitem.getWhatsmore().equals(null)) {
            holder.order_whatsmore.setText(historyitem.getWhatsmore());
        }
        if(!historyitem.getGoods1().equals(null)) {
            holder.goods1.setText(historyitem.getGoods1());
            holder.count1.setText(historyitem.getCount1());
            holder.price1.setText(historyitem.getPrice1());
        }
        if(!historyitem.getGoods2().equals(null)) {
            holder.goods2.setText(historyitem.getGoods2());
            holder.count2.setText(historyitem.getCount2());
            holder.price2.setText(historyitem.getPrice2());
        }
        if(!historyitem.getGoods3().equals(null)) {
            holder.goods3.setText(historyitem.getGoods3());
            holder.count3.setText(historyitem.getCount3());
            holder.price3.setText(historyitem.getPrice3());
        }
        if(!historyitem.getGoods4().equals(null)) {
            holder.goods4.setText(historyitem.getGoods4());
            holder.count4.setText(historyitem.getCount4());
            holder.price4.setText(historyitem.getPrice4());
        }
        holder.seein.setTag(position);

    }

    @Override
    public int getItemCount() {
        return history_items.size();
    }

    public  void add(History data) {
        int position=getItemCount();
        history_items.add(position,data);
        notifyItemInserted(position);
    }

    public void addData(List<History> data) {
        if(history_items.size()<data.size()) {
            history_items.addAll(data);
        }
        this.notifyItemRangeChanged(0, history_items.size());
    }

    public void removeData(){
        history_items.removeAll(history_items);
        this.notifyItemRangeChanged(0, history_items.size());
    }

}
