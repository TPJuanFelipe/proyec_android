package com.proyect.nuevo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class loginFragment extends Fragment {
    private EditText editTextCorreo, editTextPassword, editTextPassword_Re, editTextUsuario, edictTectTelefono;
    private Button buttonRegister;

    ImageView info_contraseña;
    ImageView info_Correo;
    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(getActivity());
    }

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextUsuario= view.findViewById ( R.id.usuari );
        editTextCorreo = view.findViewById(R.id.regicorreo );
        editTextPassword = view.findViewById(R.id.Inipassword );
        editTextPassword_Re= view.findViewById ( R.id.inipassword2);
        buttonRegister = view.findViewById ( R.id.buttonRegister );
        info_Correo=view.findViewById ( R.id.Correo_info_registro );
        info_contraseña=view.findViewById ( R.id.Password_info_registro);



        Button Ini_sesion = view.findViewById ( R.id.ini_Sesion );

        Ini_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new loginFragment2 ();

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.conte, fragment);
                fragmentTransaction.commit();
            }
        });



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = editTextUsuario.getText().toString().trim();
                String correo = editTextCorreo.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String Re_passwrd = editTextPassword_Re.getText().toString().trim();

                // Verificar si los campos de entrada de texto no están vacíos y cumplen las restricciones
                if (!usuario.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !Re_passwrd.isEmpty()) {
                    // Verificar restricciones adicionales
                    if (password.length() <= 8 ) {
                        Toast.makeText(getActivity(), "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                    } else if (!isValidEmail(correo)) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else if (!password.equals(Re_passwrd)) {
                        Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    } else if (!isPasswordValid(password)) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    } else {
                        // Los campos cumplen todas las restricciones, realizar el registro
                        long result = insertUser(usuario, correo, password, Re_passwrd);

                        if (result != -1) {
                            Toast.makeText(getActivity(), "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            // Error en el registro
                            Toast.makeText(getActivity(), "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Al menos uno de los campos está vacío, mostrar un mensaje de error
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        info_Correo.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Consejo");
                builder.setMessage("Ingrese un correo electrónico válido:\n@gmail.com");
                builder.setPositiveButton("Cerrar", null);
                builder.show();

            }
        } );

        info_contraseña.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Consejo");
                builder.setMessage("La contraseña debe contener al menos una letra mayúscula,una letra minúscula y un carácter especial");
                builder.setPositiveButton("Cerrar", null);
                builder.show();

            }
        } );

        return view;
    }

    private long insertUser(String usuario,String correo,  String password, String Re_passwrd ) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, usuario );
        values.put(DatabaseHelper.COLUMN_CORREO, correo);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        values.put (DatabaseHelper.COLUMN_PASSWORD_RE, Re_passwrd );





        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();

        return result;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        return email.matches(emailPattern);
    }
    private boolean isPasswordValid(String password) {

        String passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$";
        return password.matches(passwordPattern);
    }



}











































































































































































































































































































































































































