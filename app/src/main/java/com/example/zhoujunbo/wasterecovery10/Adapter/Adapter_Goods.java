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
import com.example.zhoujunbo.wasterecovery10.mode.Goods;

import java.util.List;

public class Adapter_Goods extends RecyclerView.Adapter<Adapter_Goods.ViewHolder> implements View.OnClickListener {

    private List<Goods> goods_items;//数据源
     Context context;//上下文
    int adjust=0;

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();//getTag()获取数据
        if(position<=0){
            position=0;
        }
        remove(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView add_goods,add_count,add_price;
        Button add_delete;
        public ViewHolder(@NonNull View view) {
            super(view);
            add_goods=(TextView)view.findViewById(R.id.add_goods);
            add_count=(TextView)view.findViewById(R.id.add_count);
            add_price=(TextView)view.findViewById(R.id.add_price);
            add_delete=(Button)view.findViewById(R.id.add_delete);

            add_delete.setOnClickListener(Adapter_Goods.this);
        }
    }


    public Adapter_Goods(List<Goods> list_items,Context context){
        goods_items=list_items;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Goods goodsitem=goods_items.get(position);
        holder.add_goods.setText(goodsitem.getGoods());
        holder.add_count.setText(goodsitem.getCount());
        holder.add_price.setText(goodsitem.getPrice());

        holder.add_delete.setTag(position);

    }

    @Override
    public int getItemCount() {
        return goods_items.size();
    }

    public void remove(int position){

        goods_items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,goods_items.size()-position);//通知数据与界面重新绑定
    }

    public  void add(Goods data) {
        int position=getItemCount();
        goods_items.add(position,data);
        notifyItemInserted(position);
    }

    public void change(int position,Goods data) {

        goods_items.remove(position);

        goods_items.add(position,data);

        notifyItemChanged(position);

    }



}
