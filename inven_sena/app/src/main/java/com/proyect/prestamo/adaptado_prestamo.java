package com.proyect.prestamo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.categoria.categoria_item;
import com.proyect.herramienta.herramienta_item;
import com.proyect.inven_sena.R;

import java.util.List;


public class adaptado_prestamo extends RecyclerView.Adapter<com.proyect.prestamo.adaptado_prestamo.ViewHolder> {
    private List<herramienta_item> listar_item_herra;
    private List<categoria_item> listar_item_catego;
    private com.proyect.vista_cate_y_herra.adaptador_recycler_cateYherra.OnItemClickListener itemClickListener;

    public adaptado_prestamo ( List<herramienta_item> listar_item_herra, List<categoria_item> listar_item_catego) {
        this.listar_item_herra = listar_item_herra;
        this.listar_item_catego = listar_item_catego;
    }

    public void setOnItemClickListener( com.proyect.vista_cate_y_herra.adaptador_recycler_cateYherra.OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public com.proyect.prestamo.adaptado_prestamo.ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.layout_prestamo, parent, false);
        return new com.proyect.prestamo.adaptado_prestamo.ViewHolder (view);
    }

    @Override
    public void onBindViewHolder( @NonNull com.proyect.prestamo.adaptado_prestamo.ViewHolder holder, int position) {
        herramienta_item item = listar_item_herra.get(position);
        categoria_item item_catego = listar_item_catego.get ( position );
        holder.txt_nombre_categoria.setText(item_catego.getCategorias ());
        holder.txt_nombre_herra.setText (item.getNombre () );
        holder.txt_descrip_herra.setText ( item.getDescripcion () );
        holder.txt_id_herra.setText(String.valueOf(item.getId_herra ()));
        holder.txt_cantidad_herra.setText(String.valueOf(item.getCantidad ()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(item);
                    itemClickListener.onItemClick ( item_catego );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listar_item_herra.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_id_herra;
         txt_cantidad_herra;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_herra = itemView.findViewById ( R.id.txt_id_herra );
            txt_nombre_categoria = itemView.findViewById(R.id.txt_nombre_catego);
            txt_nombre_herra = itemView.findViewById ( R.id.txt_nombre_herra );
            txt_cantidad_herra = itemView.findViewById ( R.id.txt_cantidad_herra );
            txt_descrip_herra = itemView.findViewById ( R.id.txt_descrip_herra );

        }
    }

    public interface OnItemClickListener {
        void onItemClick(herramienta_item item);
        void onItemClick(categoria_item item);
    }
}