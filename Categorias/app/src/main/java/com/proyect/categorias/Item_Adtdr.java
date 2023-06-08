package com.proyect.categorias;



import android.content.ClipData;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Item_Adtdr extends RecyclerView.Adapter<Item_Adtdr.ViewHolder> {
    private List<Items_Categorias> ListItem;
    private ItemClicklist itemClickListener;

    public Item_Adtdr (List<Items_Categorias> ListItem){this.ListItem= ListItem;}

    public void setItemClickListener ( ItemClicklist itemClickList ) {
        this.itemClickListener =itemClickList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup parent, int viewType ) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_adtdr, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder ( @NonNull ViewHolder holder, int position ) {

        Items_Categorias item = ListItem.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount () {
        return ListItem.size ();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombre ;

        public ViewHolder (@NonNull View itemView){
            super( itemView);
            tvNombre= itemView.findViewById ( R.id.nombre_item_cate );
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        Items_Categorias item = ListItem.get(position);
                        itemClickListener.onItemClick(item);
                    }
                }
            });


        }

        public  void bind(Items_Categorias item){
            tvNombre.setText ( item.getNombre () );
        }

    }

   public  interface ItemClicklist{


       void onItemClick ( Items_Categorias item );
   }
}