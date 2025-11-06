package com.mariambel.myapplication;

import static android.opengl.ETC1.isValid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    TextInputLayout loginTILUsername, loginTILPassword;
    EditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button loginButton = findViewById(R.id.loginButton);
        TextView loginTVRegister = findViewById(R.id.loginTVRegister);
        loginTILUsername = findViewById(R.id.loginTILuserName);
        loginTILPassword = findViewById(R.id.loginTILpassword);
        usuario = findViewById(R.id.loginUsuario);
        password = findViewById(R.id.loginPassword);
        FormUtils formUtils = new FormUtils();


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        String hashedPassword = sharedPref.getString("password", "");
        Log.d("hashedPassword", hashedPassword);
        String name =  sharedPref.getString("name", "EMPTY");
        String password =  sharedPref.getString("password", "EMPTY");

        long initialTime = System.currentTimeMillis();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContnue = true;

                if (validateInput()) {
                    Intent intentLogin = new Intent(Login.this, MainActivity.class);
                    startActivity(intentLogin);
                    editor.putString("name",String.valueOf(loginTILUsername.getEditText().getText()));
                    editor.putString("password",String.valueOf(loginTILPassword.getEditText().getText()));
                    editor.apply();

                }

                if (formUtils.isTILEmpty(loginTILUsername)){
                    loginTILUsername.setErrorEnabled(true);
                    loginTILUsername.setError("Name is empty");
                    canContnue = false;
                }
                if (formUtils.isTILEmpty(loginTILPassword)){
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("Password is empty");
                    canContnue = false;
                }
                if (formUtils.checkPassword(String.valueOf(loginTILPassword.getEditText().getText()), hashedPassword)){
                    loginTILPassword.setErrorEnabled(true);
                    loginTILPassword.setError("Empty password");
                }

                if (formUtils.checkUser(String.valueOf(loginTILUsername.getEditText().getText()), name)){
                    loginTILUsername.setErrorEnabled(true);
                    loginTILUsername.setError("Username not registered");
                    canContnue = false;
                }

                if (canContnue){
                    Intent intentMain = new Intent(Login.this, MainActivity.class);
                    long finalTime = System.currentTimeMillis();
                    intentMain.putExtra("tiempoInvertido", (initialTime-finalTime)/1000);
                    startActivity(intentMain);
                }
            }
        });



        loginTVRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(Login.this, Register.class);
                startActivity(intentMain);
            }
        });

    }

    public boolean validateInput() {
        String user = usuario.getText().toString();
        String pass = password.getText().toString();
        boolean seguir;
        if (TextUtils.isEmpty(user)) {
            loginTILUsername.setError("Introduce a username");
            seguir = false;
        } else {
            loginTILUsername.setError(null);
            seguir = true;
        }
        if (TextUtils.isEmpty(pass)) {
            loginTILPassword.setError("Introduce a password");
            seguir = false;
        } else {
            loginTILPassword.setError(null);
            seguir = true;
        }
        return seguir;
    }
}