package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<ItemsRV> itemList;
    private LayoutInflater mInflater;
    private Context context;
    public RecyclerViewAdapter(List<ItemsRV> itemList, Context context){
        this.context= context;
        this.mInflater=LayoutInflater.from(context);
        this.itemList= itemList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= mInflater.inflate(R.layout.fila, null);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }
    public void setItemList(List<ItemsRV> itemList){
        this.itemList=itemList;
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public static class  MyViewHolder extends RecyclerView.ViewHolder{
        Button btnItem;
        TextView txtItem;
        ImageView imgItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtItem = itemView.findViewById(R.id.textoCompra1rv);
            btnItem = itemView.findViewById(R.id.buttonPilaDeMonedas1rv);
            imgItem = itemView.findViewById(R.id.imageView3rv);
        }
        public void bind(ItemsRV item) {
            txtItem.setText(item.getText());
            btnItem.setText(item.getButtonText());
            imgItem.setImageResource(item.getImagen());

        }
    }
    }

