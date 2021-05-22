package com.example.omantravelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginAcitivity extends AppCompatActivity {
    TextView txt_froget_password,txt_signup;
    Button btn_login;
    EditText edt_email,edt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_acitivity);

        txt_froget_password=findViewById(R.id.txt_forgot_password);
        txt_signup=findViewById(R.id.txt_signup);
        btn_login=findViewById(R.id.btn_login);
        edt_email=findViewById(R.id.loginEmail);
        edt_pass=findViewById(R.id.loginPassword);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(LoginAcitivity.this,HomeActivity.class));
                // String str_name=edt_email.getText().toString().trim();
                String str_email = edt_email.getText().toString().trim();
                String str_password = edt_pass.getText().toString().trim();

                if (TextUtils.isEmpty(str_email)) {
                    edt_email.setError("Empty Field..!");
                    return;
                } else if (!isValidEmailId(edt_email.getText().toString().trim())) {
                    edt_email.setError("Enter valid email address");
                    // Snackbar.make(parent_view, "Enter valid email address", Snackbar.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(str_password)) {
                    edt_pass.setError("Empty Field..!");
                    return;
                } else {
                    LoginFuntion(str_email, str_password);
                }
            }
        });

    }

    private void LoginFuntion(String str_email, String str_password) {

           if (str_email.equals("admin@gmail.com")&&str_password.equals("admin"))
           {
               startActivity(new Intent(AdminLoginAcitivity.this,AdminDashboard.class));
               Toast.makeText(this, "Login Successfully...!", Toast.LENGTH_SHORT).show();
               return;
           }else {
               Toast.makeText(this, "Wrong credentials", Toast.LENGTH_SHORT).show();
               return;
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