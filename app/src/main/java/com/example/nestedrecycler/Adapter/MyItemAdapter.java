package com.example.nestedrecycler.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nestedrecycler.Interface.ItemClickListener;
import com.example.nestedrecycler.R;
import com.example.nestedrecycler.model.ItemData;
import com.example.nestedrecycler.model.ItemGroup;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private Context context;
    private List<ItemData> itemDatalist;


    public MyItemAdapter(Context context, List<ItemData> itemDatalist) {
        this.context = context;
        this.itemDatalist = itemDatalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.event_list_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final  MyViewHolder holder,final int position) {
        holder.txt_item_title.setText((itemDatalist.get(position).getName()));

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);
        Picasso.get().load(itemDatalist.get(position).getImage()).networkPolicy(NetworkPolicy.OFFLINE).into(holder.img_item, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(itemDatalist.get(position).getImage()).into(holder.img_item);
                    }
                });

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClickListener(View view, int position) {
                        Toast.makeText(context, itemDatalist.get(position).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    

    @Override
    public int getItemCount() {
        return (itemDatalist != null ? itemDatalist.size() : 0) ;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_item_title;
        ImageView img_item;
        
        ItemClickListener itemClickListener;
        
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_item_title = (TextView)itemView.findViewById(R.id.event_name);
            img_item = (ImageView) itemView.findViewById(R.id.event_icon);
            
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            itemClickListener.onItemClickListener(view,getAdapterPosition());
        }
    }


}
