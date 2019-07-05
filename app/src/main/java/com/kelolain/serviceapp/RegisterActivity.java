package com.kelolain.serviceapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kelolain.serviceapp.SqlliteHelper.SqliteHelper;
import com.kelolain.serviceapp.User.User;




public class RegisterActivity extends AppCompatActivity {


    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonRegister;

    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();


                    if (!sqliteHelper.isEmailExists(Email)) {


                        sqliteHelper.addUser(new User(null, UserName, Email, Password));
                        Snackbar.make(buttonRegister, "Berhasil , Silahkan Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        Snackbar.make(buttonRegister, "User telah digunakan", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.tv_login);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    ImageButton backArrow = (ImageButton)findViewById(R.id.tombolKembali);

        backArrow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(RegisterActivity.this, LoginSign.class);
            startActivity(intent);
            finish();
        }
    });

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.et_email);
        editTextPassword = (EditText) findViewById(R.id.et_password);
        editTextUserName = (EditText) findViewById(R.id.et_name);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.ti_email);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.ti_password);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.ti_name);
        buttonRegister = (Button) findViewById(R.id.btn_register);

    }

    public boolean validate() {
        boolean valid = false;

        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 3) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 3) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }


        return valid;
    }
}
