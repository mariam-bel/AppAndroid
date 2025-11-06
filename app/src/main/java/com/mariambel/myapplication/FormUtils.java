package com.mariambel.myapplication;

import com.google.android.material.textfield.TextInputLayout;

import org.mindrot.jbcrypt.BCrypt;

public class FormUtils {
    public boolean isTILEmpty(TextInputLayout textInputLayout){
        return String.valueOf(textInputLayout.getEditText().getText()).isEmpty();
    }

    public String getTILText(TextInputLayout textInputLayout){
        return String.valueOf(textInputLayout.getEditText().getText());
    }

    public String generateHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String candidate, String hashed) {
        return BCrypt.checkpw(candidate,hashed);
    }

    public boolean checkUser(String inputUser, String savedUser){
        return inputUser.equals(savedUser);
    }
}
