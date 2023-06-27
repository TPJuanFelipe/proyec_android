package com.proyect.herramientas_inv;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adactador_Herra  extends RecyclerView.Adapter<adactador_Herra.ViewHolder> {
    private List<Item_Herramienta> listar_item_herra;
    private OnItemClickListener onItemClickListener;

    public adactador_Herra ( List<Item_Herramienta> listar_item_herra ) {
        this.listar_item_herra = listar_item_herra;
    }

    public void setOnItemClickListener ( OnItemClickListener onItemClickListener ) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_herra, parent, false);
        return new ViewHolder(view);
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_Id_Herra;
        TextView tv_Nombre_Herra;
        TextView tv_cantidad_Herra;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Id_Herra = itemView.findViewById(R.id.tv_id_herra);
            tv_Nombre_Herra = itemView.findViewById(R.id.tv_nombre_herra);

            tv_cantidad_Herra = itemView.findViewById(R.id.tv_cantidad); // Add this line

        }
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position) {
        Item_Herramienta item = listar_item_herra.get(position);
        holder.tv_Id_Herra.setText ( String.valueOf ( item.getId_Herra () ) );
        holder.tv_Nombre_Herra.setText(String.valueOf(item.getNombre_Herra ()));

        holder.tv_cantidad_Herra.setText ( String.valueOf ( item.getCantidad_Herra () ) );


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(item);
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return listar_item_herra.size();
    }








    public interface OnItemClickListener {
        void onItemClick(Item_Herramienta item);
    }
}
