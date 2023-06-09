package com.proyect.nuevo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.SurfaceTexture;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.database.Cursor;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.w3c.dom.Text;

public class loginFragment2 extends Fragment {
    private EditText editTextCorreo, editTextPassword;
    private Button buttonLogin;
    Button BtnRegis;
    ImageView Info_Correo_login;
    ImageView Info_password_login;


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
        View view = inflater.inflate(R.layout.fragment_login2, container, false);



        editTextCorreo = view.findViewById(R.id.iniusuario);
        editTextPassword = view.findViewById(R.id.Inipassword );
        buttonLogin = view.findViewById(R.id.buttonLogin);
        Info_Correo_login=view.findViewById ( R.id.correo_info_login );
        Info_password_login= view.findViewById ( R.id.pasword_info_login );
        Button btnCambiar = view.findViewById ( R.id.registro );

        btnCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragmentoNuevo = new loginFragment ();

                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.conte, fragmentoNuevo);
                fragmentTransaction.commit();

            }
        });




        Info_Correo_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Consejo");
                builder.setMessage("Ingrese un correo electrónico válido:\n @gmail.com");
                builder.setPositiveButton("Cerrar", null);
                builder.show();

            }
        } );

        Info_password_login.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Consejo");
                builder.setMessage("La contraseña debe contener al menos una letra mayúscula, una letra minúscula y un carácter especial");
                builder.setPositiveButton("Cerrar", null);
                builder.show();

            }
        } );



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = editTextCorreo.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (!correo.isEmpty() && !password.isEmpty()) {
                    if (!isValidEmail(correo)) {
                        Toast.makeText(getActivity(), "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (password.length() < 8) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!isPasswordValid(password)) {
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                    if (checkUserCredentials(correo, password)) {
                        Toast.makeText(getActivity(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), MenuActivity.class);

                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });












        return view;

    }

    private boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }



    private boolean checkUserCredentials(String correo, String password) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String[] projection = {DatabaseHelper.COLUMN_CORREO, DatabaseHelper.COLUMN_PASSWORD};
        String selection = DatabaseHelper.COLUMN_CORREO + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {correo, password};

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, projection, selection, selectionArgs, null, null, null);
        boolean result = cursor.moveToFirst();
        cursor.close();
        db.close();

        return result;
    }

    private boolean isPasswordValid(String password) {
        // Verificar si la contraseña contiene al menos una letra mayúscula, una letra minúscula y un carácter especial
        String passwordPattern = "(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$";
        return password.matches(passwordPattern);
    }
}
