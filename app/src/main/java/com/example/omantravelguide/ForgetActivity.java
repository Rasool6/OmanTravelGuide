package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class ForgetActivity extends AppCompatActivity {
EditText emailForgot_edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        emailForgot_edt=findViewById(R.id.emailForgot);
    }

    public void nextbtn(View view) {
        String str_email=emailForgot_edt.getText().toString().trim();
        if (TextUtils.isEmpty(str_email)){
            emailForgot_edt.setError("Empty Field..!");
            return;
        }else
        if (!isValidEmailId(emailForgot_edt.getText().toString().trim())) {
            emailForgot_edt.setError("Enter valid email address");
            // Snackbar.make(parent_view, "Enter valid email address", Snackbar.LENGTH_SHORT).show();
            return;
        }else {

            startActivity(new Intent(ForgetActivity.this,ResetPasswordActivity.class)
            .putExtra("key",str_email));
            finish();

        }

    }

    private boolean isValidEmailId(String email) {
      /*  return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches(); */

        //    return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        String emailinput = email;

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (emailinput.matches(emailPattern)) {
//            Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
            return true;
        } else {
//            Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

}