package com.proyect.categorias;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {
    private EditText nombreEditText;
    private EditText cantidadEditText;
    private CheckBox agotadoCheckBox;
    private ImageView agregarButton;
    private ImageView modificarButton;
    private ImageView eliminarButton;
    private Db_Categorias databaseHelper;
    private Item currentItem;
    private Spinner insert_categoria;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        nombreEditText = view.findViewById(R.id.nombreEditText);
        cantidadEditText = view.findViewById(R.id.cantidadEditText);
        agotadoCheckBox = view.findViewById(R.id.agotadoCheckBox);
        agregarButton = view.findViewById(R.id.agregarButton);
        modificarButton = view.findViewById(R.id.modificarButton);
        eliminarButton = view.findViewById(R.id.eliminarButton);
        insert_categoria = view.findViewById(R.id.categoria_Insert);
        databaseHelper = new Db_Categorias (requireContext());

        agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarItem();
            }
        });

        modificarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarItem();
            }
        });

        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoEliminar();
            }
        });

        return view;
    }

    public static ItemFragment newInstance(Item item) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putParcelable("item", item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            currentItem = getArguments().getParcelable("item");
            if (currentItem != null) {
                nombreEditText.setText(currentItem.getNombre());
                cantidadEditText.setText(String.valueOf(currentItem.getCantidad()));
                agotadoCheckBox.setChecked(currentItem.isAgotado());
                modificarButton.setVisibility(View.VISIBLE);
                eliminarButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private void agregarItem() {
        String nombre = nombreEditText.getText().toString().trim();
        int cantidad = Integer.parseInt(cantidadEditText.getText().toString());
        boolean agotado = agotadoCheckBox.isChecked();

        Item newItem = new Item();
        newItem.setNombre(nombre);
        newItem.setCantidad(cantidad);
        newItem.setAgotado(agotado);

        databaseHelper.insertItem(newItem);

        Toast.makeText(requireContext(), "Item agregado", Toast.LENGTH_SHORT).show();

        // Limpiar los campos de entrada
        nombreEditText.setText("");
        cantidadEditText.setText("");
        agotadoCheckBox.setChecked(false);
    }

    private void modificarItem() {
        if (currentItem != null) {
            String nombre = nombreEditText.getText().toString().trim();
            int cantidad = Integer.parseInt(cantidadEditText.getText().toString());
            boolean agotado = agotadoCheckBox.isChecked();

            currentItem.setNombre(nombre);
            currentItem.setCantidad(cantidad);
            currentItem.setAgotado(agotado);

            databaseHelper.updateItem(currentItem);

            Toast.makeText(requireContext(), "Item modificado", Toast.LENGTH_SHORT).show();
        }
    }

    private void eliminarItem() {
        if (currentItem != null) {
            databaseHelper.deleteItem(currentItem);

            Toast.makeText(requireContext(), "Item eliminado", Toast.LENGTH_SHORT).show();
            requireActivity().onBackPressed();
        }
    }

    private void mostrarDialogoEliminar() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Eliminar Item");
        builder.setMessage("¿Estás seguro de que quieres eliminar este item?");
        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarItem();
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}
