package com.example.paralelo.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paralelo.databinding.FragmentLoginBinding;

import com.example.paralelo.R;

public class LoginFragment extends Fragment {

    public LoginFragment(){}

    @Override
    public View onCreateView (  LayoutInflater inflater,
                                ViewGroup container,
                                Bundle savedInstanceState ) {

        return inflater.inflate ( R.layout.fragment_login,container,false );

    }

}