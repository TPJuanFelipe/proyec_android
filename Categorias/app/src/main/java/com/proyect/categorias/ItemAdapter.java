package com.proyect.categorias;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    private OnItemClickListener itemClickListener;

    public ItemAdapter(List<Item> itemList) {
        this.itemList = itemList;
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
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView cantidadTextView;
        TextView agotadoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            cantidadTextView = itemView.findViewById(R.id.cantidadTextView);
            agotadoTextView = itemView.findViewById(R.id.agotadoTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        Item item = itemList.get(position);
                        itemClickListener.onItemClick(item);
                    }
                }
            });
        }

        public void bind(Item item) {
            nombreTextView.setText(item.getNombre());
            cantidadTextView.setText(String.valueOf(item.getCantidad()));
            agotadoTextView.setText(item.isAgotado() ? "Disponible" : "No disponible");
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
}

