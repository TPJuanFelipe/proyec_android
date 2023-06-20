package com.proyect.categorias;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adactador_recyclerview extends RecyclerView.Adapter<adactador_recyclerview.ViewHolder> {
    private List<Items_Categorias> listar_items;
    private OnItemClickListener itemClickListener;

    public adactador_recyclerview(List<Items_Categorias> listar_items) {
        this.listar_items = listar_items;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Items_Categorias item = listar_items.get(position);
        holder.tv_categoria.setText(item.getCategorias());
        holder.tv_Id_categoria.setText(String.valueOf(item.getId_categoria()));
        holder.tv_identifi_admin.setText(String.valueOf(item.getIdentifi_admin()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listar_items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Id_categoria;
        TextView tv_categoria;
        TextView tv_identifi_admin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_categoria = itemView.findViewById(R.id.tv_categorias);
            tv_Id_categoria = itemView.findViewById(R.id.tv_id_categoria);
            tv_identifi_admin = itemView.findViewById(R.id.tv_identifi_Admin);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Items_Categorias item);
    }
}
