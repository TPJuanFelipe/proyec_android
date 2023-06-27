package com.proyect.appdb;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt_nombre;
    EditText edt_cantidad;
    Button btn_insertar;
    Button btn_actualizar;
    Button btn_eliminar;
    Button btn_mostrar;
    Dbsqlitees db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_nombre = findViewById(R.id.edit_nombre);
        edt_cantidad = findViewById(R.id.edit_cantidad);
        btn_insertar = findViewById(R.id.eliminar );
        btn_actualizar = findViewById(R.id.modificar);
        btn_eliminar = findViewById(R.id.eliminar);


        db = new Dbsqlitees(this);

        btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edt_nombre.getText().toString().trim();
                String cantidadString = edt_cantidad.getText().toString().trim();

                if (nombre.isEmpty() || cantidadString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    int cantidad = Integer.parseInt(cantidadString);
                    insertarItem(nombre, cantidad);
                }
            }
        });

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edt_nombre.getText().toString().trim();
                String cantidadString = edt_cantidad.getText().toString().trim();

                if (nombre.isEmpty() || cantidadString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingrese todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    int cantidad = Integer.parseInt(cantidadString);
                    actualizarItem(nombre, cantidad);
                }
            }
        });

        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edt_nombre.getText().toString().trim();

                if (nombre.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingrese el nombre del item a eliminar", Toast.LENGTH_SHORT).show();
                } else {
                    eliminarItem(nombre);
                }
            }
        });

        btn_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarItems();
            }
        });
    }

    private void insertarItem(String nombre, int cantidad) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Dbsqlitees.COLUMNA_NOMBRE, nombre);
        values.put(Dbsqlitees.COLUMNA_CANTIDAD, cantidad);

        long resultado = database.insert(Dbsqlitees.TABLA_PRODUCTOS, null, values);
        if (resultado == -1) {
            Toast.makeText(this, "Error al insertar el item", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Item insertado correctamente", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    private void actualizarItem(String nombre, int cantidad) {
        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Dbsqlitees.COLUMNA_CANTIDAD, cantidad);

        int resultado = database.update(Dbsqlitees.TABLA_PRODUCTOS, values,
                Dbsqlitees.COLUMNA_NOMBRE + " = ?", new String[]{nombre});

        if (resultado > 0) {
            Toast.makeText(this, "Item actualizado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontró el item a actualizar", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }

    private void eliminarItem(String nombre) {
        SQLiteDatabase database = db.getWritableDatabase();

        int resultado = database.delete(Dbsqlitees.TABLA_PRODUCTOS,
                Dbsqlitees.COLUMNA_NOMBRE + " = ?", new String[]{nombre});

        if (resultado > 0) {
            Toast.makeText(this, "Item eliminado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No se encontró el item a eliminar", Toast.LENGTH_SHORT).show();
        }

        database.close();
    }
    @SuppressLint("Range")
    private void mostrarItems() {
        SQLiteDatabase database = db.getReadableDatabase();

        Cursor cursor = database.query(Dbsqlitees.TABLA_PRODUCTOS,
                null, null, null, null, null, null);

        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()) {
             int id = cursor.getInt(cursor.getColumnIndex(Dbsqlitees.COLUMNA_ID));
            String nombre = cursor.getString(cursor.getColumnIndex(Dbsqlitees.COLUMNA_NOMBRE));
            int cantidad = cursor.getInt(cursor.getColumnIndex(Dbsqlitees.COLUMNA_CANTIDAD));

            stringBuilder.append("ID: ").append(id)
                    .append(", Nombre: ").append(nombre)
                    .append(", Cantidad: ").append(cantidad)
                    .append("\n");
        }

        if (stringBuilder.length() > 0) {
            Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No hay items para mostrar", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        database.close();
    }
}
