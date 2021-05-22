package com.example.omantravelguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginAcitivity extends AppCompatActivity {
    TextView txt_froget_password,txt_signup;
    Button btn_login;
    EditText edt_email,edt_pass;

    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    String[] appPermissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            //   Manifest.permission.ACCESS_BACKGROUND_LOCATION,
            Manifest.permission.INTERNET
    };
    private static final int PERMISSIONS_REQUEST_CODE = 12403;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (checkAndRequestPermissions()) {
            // All permissions are granted already. Proceed ahead
            initApp();


        }
    }

    private void LoginFuntion(String str_email, String str_password) {

        progressDialog.show();
        mAuth.signInWithEmailAndPassword(str_email, str_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user.isEmailVerified()) {
                               Appdata.UID=user.getUid();

                                //  fetchUserData(id);
                                startActivity(new Intent(LoginAcitivity.this,HomeActivity.class ));
                                finish();


                            }else{
                                progressDialog.dismiss();
                                //  progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginAcitivity.this, "Please Verified your email first", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginAcitivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //  progressBar.setVisibility(View.GONE);
                            progressDialog.dismiss();
                            //   WaitDialog.hideDialog();
                        }

                        // ...
                    }
                });

    }

    public  void  init(){
        txt_froget_password=findViewById(R.id.txt_forgot_password);
        txt_signup=findViewById(R.id.txt_signup);
        btn_login=findViewById(R.id.btn_login);
        edt_email=findViewById(R.id.loginEmail);
        edt_pass=findViewById(R.id.loginPassword);
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
    public boolean checkAndRequestPermissions()
    {
        // Check which permissions are granted
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String perm : appPermissions)
        {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED)
            {
                listPermissionsNeeded.add(perm);
            }
        }

        // Ask for non-granted permissions
        if (!listPermissionsNeeded.isEmpty())
        {
            ActivityCompat.requestPermissions(this,
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),
                    PERMISSIONS_REQUEST_CODE
            );
            return false;
        }

        // App has all permissions. Proceed ahead
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE)
        {
            HashMap<String, Integer> permissionResults = new HashMap<>();
            int deniedCount = 0;

            // Gather permission grant results
            for (int i=0; i<grantResults.length; i++)
            {
                // Add only permissions which are denied
                if (grantResults[i] == PackageManager.PERMISSION_DENIED)
                {
                    permissionResults.put(permissions[i], grantResults[i]);
                    deniedCount++;
                }
            }

            // Check if all permissions are granted
            if (deniedCount == 0)
            {
                // Proceed ahead with the app
                initApp();
            }
            // Atleast one or all permissions are denied
            else
            {
                for (Map.Entry<String, Integer> entry : permissionResults.entrySet())
                {
                    String permName = entry.getKey();
                    int permResult = entry.getValue();

                    // permission is denied (this is the first time, when "never ask again" is not checked)
                    // so ask again explaining the usage of permission
                    // shouldShowRequestPermissionRationale will return true
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permName))
                    {
                        // Show dialog of explanation
                        showDialog("", "This app needs Location and Storage permissions to work wihout any issues and problems.",
                                "Yes, Grant permissions",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        checkAndRequestPermissions();
                                    }
                                },
                                "No, Exit app", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                }, false);
                    }
                    //permission is denied (and never ask again is  checked)
                    //shouldShowRequestPermissionRationale will return false
                    else
                    {
                        // Ask user to go to settings and manually allow permissions
                        showDialog("", "You have denied some permissions to the app. Please allow all permissions at [Setting] > [Permissions] screen",
                                "Go to Settings",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        // Go to app settings
                                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                                Uri.fromParts("package", getPackageName(), null));
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                },
                                "No, Exit app", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                        finish();
                                    }
                                }, false);
                        break;
                    }
                }
            }
        }
    }
    private void initApp() {
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...!");
        init();
        txt_froget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAcitivity.this, ForgetActivity.class));
            }
        });
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginAcitivity.this, SignupActivity.class));
            }
        });
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

    public AlertDialog showDialog(String title, String msg, String positiveLabel,
                                  DialogInterface.OnClickListener positiveOnClick,
                                  String negativeLabel, DialogInterface.OnClickListener negativeOnClick,
                                  boolean isCancelAble)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setCancelable(isCancelAble);
        builder.setMessage(msg);
        builder.setPositiveButton(positiveLabel, positiveOnClick);
        builder.setNegativeButton(negativeLabel, negativeOnClick);

        AlertDialog alert = builder.create();
        alert.show();
        return alert;
    }
}