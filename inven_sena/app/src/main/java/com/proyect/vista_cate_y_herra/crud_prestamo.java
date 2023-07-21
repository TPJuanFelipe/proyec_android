package com.proyect.vista_cate_y_herra;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyect.inven_sena.R;

public class crud_prestamo extends Fragment {


    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        View view= inflater.inflate ( R.layout.fragment_crud_prestamo, container, false );


        return view;
    }
}