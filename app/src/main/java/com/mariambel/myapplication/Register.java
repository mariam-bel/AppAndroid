package com.mariambel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.BitmapCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {


    /*
    * addtextchangelistener
    */

    TextInputLayout registerTILUsername, registerTILPassword, registerTILEmail, registerTILconfirmPass;
    EditText name, password, confirmPass, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button registerButton = findViewById(R.id.registerButton);
        registerTILUsername = findViewById(R.id.registerTILuserName);
        registerTILPassword = findViewById(R.id.registerTILpassword);
        registerTILconfirmPass = findViewById(R.id.registerTILpasswordConfirmation);
        registerTILEmail = findViewById(R.id.registerTILemail);
        name = findViewById(R.id.registerUserName);
        password = findViewById(R.id.registerPassword);
        confirmPass = findViewById(R.id.registerPasswordConfirmation);
        email = findViewById(R.id.registerEmail);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPref.edit();

        FormUtils formUtils = new FormUtils();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean canContinue = true;
                if (validateInput()) {
                    editor.putString("name",String.valueOf(registerTILUsername.getEditText().getText()));
                    editor.putString("email",String.valueOf(registerTILEmail.getEditText().getText()));
                    editor.putString("password", formUtils.generateHashedPassword(formUtils.getTILText(registerTILPassword)));
                    editor.apply();
                    Intent intentRegister = new Intent(Register.this, MainActivity.class);
                    startActivity(intentRegister);

                }
                if (formUtils.isTILEmpty(registerTILUsername)){
                    registerTILUsername.setErrorEnabled(true);
                    registerTILUsername.setError("Empty name");
                    canContinue = false;
                }

                
            }
        });
    }
    public boolean validateInput() {
        String username = name.getText().toString();
        String pass = password.getText().toString();
        String confirmpass = confirmPass.getText().toString();
        String emailPattern = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        Pattern p = Pattern.compile(emailPattern);
        String email = String.valueOf(registerTILEmail.getEditText().getText());
        Matcher m = p.matcher(email);
        boolean seguir;
        if (TextUtils.isEmpty(username)) {
            registerTILUsername.setError("Introduce a name");
            seguir = false;
        } else {
            registerTILUsername.setError(null);
            seguir = true;
        }
        if (TextUtils.isEmpty(pass)) {
            registerTILPassword.setError("Introduce a password");
            seguir = false;
        } else {
            registerTILPassword.setError(null);
            seguir = true;
        }
        if(TextUtils.isEmpty(email) || !m.find()) {
            registerTILEmail.setError("Introduce an email correctly");
        } else{
            seguir = true;
        }
        if (TextUtils.isEmpty(confirmpass) || !pass.equals(confirmpass)){
            registerTILconfirmPass.setError("Introduce confirmation correctly");
            seguir = false;
        }else {
            seguir = true;
        }
        return seguir;
    }
}