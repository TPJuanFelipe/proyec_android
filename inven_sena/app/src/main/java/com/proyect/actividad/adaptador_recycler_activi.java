package com.proyect.actividad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyect.inicioSesion_y_registro.login;
import com.proyect.inven_sena.R;

import java.util.List;

public class adaptador_recycler_activi extends RecyclerView.Adapter<adaptador_recycler_activi.ViewHolder> {
    private List<actividad_item> listar_actividad;
    private  List<login>  listar_trabajador;

    private OnItemClickListener itemClickListener;

    public adaptador_recycler_activi ( List<actividad_item> listar_actividad, List<login> listar_trabajador) {
        this.listar_actividad = listar_actividad;
        this.listar_trabajador= listar_trabajador;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.activi_layaut_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        actividad_item activi_item = listar_actividad.get(position);
        login trabaja_item = listar_trabajador.get ( position );
        holder.txt_id_actividad.setText ( String.valueOf ( activi_item.getId_trabajo () ) );
        holder.txt_trabajo_reali.setText(activi_item.getTrabajo_realizado ());
        holder.txt_fecha_reali.setText(activi_item.getFecha_realizado ());
        holder.txt_id_trabajador.setText(String.valueOf(activi_item.getId_trabajador ()));
        holder.txt_nombre_trabajador.setText ( trabaja_item.getNombre_tbj () );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(activi_item);
                    itemClickListener.onItemClick ( trabaja_item );
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listar_actividad.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id_actividad;
        TextView txt_trabajo_reali;
        TextView txt_fecha_reali;
        TextView txt_id_trabajador;
        TextView txt_nombre_trabajador;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id_actividad= itemView.findViewById(R.id.txt_id_actividad);
            txt_trabajo_reali= itemView.findViewById ( R.id.txt_trabajo_reali );
            txt_fecha_reali = itemView.findViewById ( R.id.txt_fecha_reali );
            txt_id_trabajador = itemView.findViewById ( R.id.txt_id_trabajador );
            txt_nombre_trabajador = itemView.findViewById ( R.id.txt_nombre_trabajador );

        }
    }

    public interface OnItemClickListener {
        void onItemClick(actividad_item actividad_item);
        void onItemClick(login trabaja_item);

    }
}
