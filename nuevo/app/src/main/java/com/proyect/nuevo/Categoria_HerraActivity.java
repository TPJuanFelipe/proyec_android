package com.proyect.nuevo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
public class Categoria_HerraActivity extends AppCompatActivity {

    VideoView Vv;

    List<ListElement> elements;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.categoriaactivity );

        init();







    }
    public  void init(){
        elements= new ArrayList<> ();

        elements.add(new ListElement("#FF512727", "Manuales", "Exceso", "Activo", "Mecanicas_HerraActivity"));
        elements.add(new ListElement("#FF512727", "semiautomáticas", "Excaso", "Activo", "Semiautomaticas_HerraActivity"));
        ListAdapter listAdapter= new ListAdapter ( elements, this, new ListAdapter.OnItemClickListener () {
            @Override
            public void onItemClick ( ListElement item ) {
                item_Cate_ir(item);
            }
        } );
        RecyclerView recyclerView= findViewById ( R.id.item_catego );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );
        recyclerView.setAdapter ( listAdapter );

    }

    public void item_Cate_ir(ListElement item) {
        String activityType = item.getActivityType();

        Intent intent;
        if (activityType.equals("Mecanicas_HerraActivity")) {
            intent = new Intent(this, Semiautomaticas_HerraActivity.class);

        } 
        else if (activityType.equals("Semiautomaticas_HerraActivity")) {
            intent = new Intent(this, Semiautomaticas_HerraActivity.class);
        }
        else {
            // Actividad predeterminada si el tipo no coincide con ninguno
            intent = new Intent(this, Categoria_HerraActivity.class);
        }

        startActivity(intent);
    }
    private void InfoMontaje() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informacion");
        builder.setMessage("Estas herramientas representan el grupo llamado accesorios para montaje, se denominan de esa manera por el simple hecho de utilizarlas sobre otros objetos.");
        builder.setPositiveButton("Cerrar", null);
        builder.show();


    }


}