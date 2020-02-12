package com.example.nestedrecycler.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nestedrecycler.R;
import com.example.nestedrecycler.model.ItemData;
import com.example.nestedrecycler.model.ItemGroup;

import java.util.List;

public class MyItemGroupAdaper extends RecyclerView.Adapter<MyItemGroupAdaper.MyViewHolder> {

    private Context context;

    private List<ItemGroup> datalist;

    public MyItemGroupAdaper(Context context, List<ItemGroup> datalist) {
        this.context = context;
        this.datalist = datalist;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.layout_group,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        holder.item_title.setText(datalist.get(position).getHeaderTitle());
        List<ItemData> itemData = datalist.get(position).getListItem();

        MyItemAdapter itemListAdapter = new MyItemAdapter(context,itemData);
        holder.recycler_view_item_list.setHasFixedSize(true);
        holder.recycler_view_item_list.setLayoutManager(new GridLayoutManager(context,2));
        holder.recycler_view_item_list.setAdapter(itemListAdapter);

        holder.recycler_view_item_list.setNestedScrollingEnabled(true);

        holder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Button More"+holder.item_title.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (datalist != null ? datalist.size() :0);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_title;
        RecyclerView recycler_view_item_list;
        Button btn_more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_title = (TextView)itemView.findViewById(R.id.itemTitle);
            btn_more = (Button) itemView.findViewById(R.id.button_more);
            recycler_view_item_list = (RecyclerView) itemView.findViewById(R.id.recycler_view_list);

        }
    }

}
