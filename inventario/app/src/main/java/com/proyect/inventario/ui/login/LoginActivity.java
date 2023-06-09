package com.proyect.inventario.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.LoaderManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.proyect.inventario.R;
import com.proyect.inventario.ui.login.LoginViewModel;
import com.proyect.inventario.ui.login.LoginViewModelFactory;
import com.proyect.inventario.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<D> {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

        binding = ActivityLoginBinding.inflate ( getLayoutInflater () );
        setContentView ( binding.getRoot () );

        loginViewModel = new ViewModelProvider ( this, new LoginViewModelFactory () )
                .get ( LoginViewModel.class );

        final EditText usernameEditText = binding.mEmailView;
        final EditText passwordEditText = binding.mPasswordView;
        final Button loginButton = binding.mEmailSignInButton;
        final ProgressBar loadingProgressBar = binding.loading;
        @SuppressLint("WrongViewCast") EditText mEmailView=(AutoCompleteTextView)findViewById ( R.id.mEmailView );
        populateAutoComplete();

        passwordEditText.setOnEditorActionListener ( new TextView.OnEditorActionListener () {
            @Override
            public boolean onEditorAction ( TextView textView, int id, KeyEvent keyEvent ) {
                if(id==R.id.mEmailSignInButton|| id==EditorInfo.IME_NULL){
                    attemptLogin();
                    return true;
                }
                return false;
            }
        } );

        Button mEmailSignInButton=(Button)findViewById ( R.id.mEmailSignInButton );
        mEmailSignInButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                attemptLogin ();
            }
        } );


        TextView registForm=(TextView)findViewById ( R.id.registForm );
        registForm.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View view ) {
                Integer ir=new Integer ( LoginActivity.this,Registro.class );
                startActivity ( ir );

            }
        } );

        loginViewModel.getLoginFormState ().observe ( this, new Observer<LoginFormState> () {
            @Override
            public void onChanged ( @Nullable LoginFormState loginFormState ) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled ( loginFormState.isDataValid () );
                if (loginFormState.getUsernameError () != null) {
                    usernameEditText.setError ( getString ( loginFormState.getUsernameError () ) );
                }
                if (loginFormState.getPasswordError () != null) {
                    passwordEditText.setError ( getString ( loginFormState.getPasswordError () ) );
                }
            }
        } );

        loginViewModel.getLoginResult ().observe ( this, new Observer<LoginResult> () {
            @Override
            public void onChanged ( @Nullable LoginResult loginResult ) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility ( View.GONE );
                if (loginResult.getError () != null) {
                    showLoginFailed ( loginResult.getError () );
                }
                if (loginResult.getSuccess () != null) {
                    updateUiWithUser ( loginResult.getSuccess () );
                }
                setResult ( Activity.RESULT_OK );

                //Complete and destroy login activity once successful
                finish ();
            }
        } );

        TextWatcher afterTextChangedListener = new TextWatcher () {
            @Override
            public void beforeTextChanged ( CharSequence s, int start, int count, int after ) {
                // ignore
            }

            @Override
            public void onTextChanged ( CharSequence s, int start, int before, int count ) {
                // ignore
            }

            @Override
            public void afterTextChanged ( Editable s ) {
                loginViewModel.loginDataChanged ( usernameEditText.getText ().toString (),
                        passwordEditText.getText ().toString () );
            }
        };
        usernameEditText.addTextChangedListener ( afterTextChangedListener );
        passwordEditText.addTextChangedListener ( afterTextChangedListener );
        passwordEditText.setOnEditorActionListener ( new TextView.OnEditorActionListener () {

            @Override
            public boolean onEditorAction ( TextView v, int actionId, KeyEvent event ) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login ( usernameEditText.getText ().toString (),
                            passwordEditText.getText ().toString () );
                }
                return false;
            }
        } );

        loginButton.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick ( View v ) {
                loadingProgressBar.setVisibility ( View.VISIBLE );
                loginViewModel.login ( usernameEditText.getText ().toString (),
                        passwordEditText.getText ().toString () );
            }
        } );
    }

    private void populateAutoComplete () {
       

    }

    private boolean mayRequestContacts () {
        return false;
    }

    private void attemptLogin () {
    }

    private void updateUiWithUser ( LoggedInUserView model ) {
        String welcome = getString ( R.string.welcome ) + model.getDisplayName ();
        // TODO : initiate successful logged in experience
        Toast.makeText ( getApplicationContext (), welcome, Toast.LENGTH_LONG ).show ();
    }

    private void showLoginFailed ( @StringRes Integer errorString ) {
        Toast.makeText ( getApplicationContext (), errorString, Toast.LENGTH_SHORT ).show ();
    }


}