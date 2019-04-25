package com.example.zhoujunbo.wasterecovery10;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Mine> mine_items;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView itemName;

        public ViewHolder(@NonNull View view) {
            super(view);
            itemImage=(ImageView)view.findViewById(R.id.icon_mine);
            itemName=(TextView) view.findViewById(R.id.name_mine);
        }
    }

    public Adapter(List<Mine> list_items){
         mine_items=list_items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ViewHolder holder =new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mine mineitem=mine_items.get(position);
        holder.itemImage.setImageResource(mineitem.getImageId());
        holder.itemName.setText(mineitem.getName());

    }

    @Override
    public int getItemCount() {
        return mine_items.size();
    }

}
