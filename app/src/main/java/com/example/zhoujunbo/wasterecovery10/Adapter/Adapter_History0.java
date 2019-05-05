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

public class Adapter_History0 extends RecyclerView.Adapter<Adapter_History0.ViewHolder> implements View.OnClickListener  {
    private List<History> history_items;//数据源
    Context context;//上下文

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();//getTag()获取数据
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView order_custmernum,order_custmername,order_collectorname,order_collectornum;
        TextView order_state,order_time,order_ID;
        Button seein;
        public ViewHolder(@NonNull View view) {
            super(view);
            order_custmername=(TextView)view.findViewById(R.id.order_custmername);
            order_custmernum=(TextView)view.findViewById(R.id.order_custmernum);
            order_collectorname=(TextView)view.findViewById(R.id.order_collectorname);
            order_collectornum=(TextView)view.findViewById(R.id.order_collectornum);
            order_state=(TextView)view.findViewById(R.id.order_state);
            order_time=(TextView)view.findViewById(R.id.order_time) ;
            order_ID=(TextView)view.findViewById(R.id.order_ID) ;

            seein=(Button)view.findViewById(R.id.seein);

            seein.setOnClickListener(Adapter_History0.this);
        }
    }

    public Adapter_History0(List<History> list_items,Context context){
        history_items=list_items;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter_History0.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        Adapter_History0.ViewHolder holder =new Adapter_History0.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_History0.ViewHolder holder, int position) {
        History historyitem=history_items.get(position);
        holder.order_collectorname.setText(historyitem.getCollectorName());
        holder.order_collectornum.setText(historyitem.getCollectorNum());
        holder.order_custmername.setText(historyitem.getCustmerName());
        holder.order_custmernum.setText(historyitem.getCustmerNum());
        holder.order_ID.setText(historyitem.getID());
        holder.order_state.setText(historyitem.getState());
        holder.order_time.setText(historyitem.getTime());

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
