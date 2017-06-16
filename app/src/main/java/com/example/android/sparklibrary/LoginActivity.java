package com.example.android.sparklibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.sparklibrary.Storage.Storage;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.et_username);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        Storage.listTipovi();
        Storage.ListCLanova();
        Storage.listAutora();
//        Storage.GetAllKnjige();

        SetLoginCredentials();
    }


    public void loginClicked(View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if(username.equals("admin") && password.equals("password")) {
            System.out.println("Login uspjesan");
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        } else {
            System.out.println("Login neuspjesan");
            Toast.makeText(this, "Pogresan username ili sifra", Toast.LENGTH_LONG).show();
        }
    }

    public void SetLoginCredentials(){
        etUsername.setText("admin");
        etPassword.setText("password");
    }
    public void registerClicked(View v) {
        System.out.println("Register clicked");
    }
}
