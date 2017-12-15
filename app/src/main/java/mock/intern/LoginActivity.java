package mock.intern;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by BalaKrishnan on 14-12-2017.
 */


public class LoginActivity extends AppCompatActivity {
    EditText ed_Email,ed_Password;
    TextView link,f_p;
    Button btn_Signin;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_Email=findViewById(R.id.login_email);
        ed_Password=findViewById(R.id.login_password);
        btn_Signin = findViewById(R.id.btn_login);
        f_p=findViewById(R.id.forgotPassword);

        link = findViewById(R.id.link_Logon);
       f_p.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i= new Intent(LoginActivity.this,ForgotPassword.class);
               startActivity(i);
               finish();
           }
       });
       link.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i= new Intent(LoginActivity.this,Signup.class);
               startActivity(i);
               finish();
           }
       });
        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!signinValidate()) {
                    Log.d("Signup", "onClick: fail");
                    onSigninFail();
                    return;
                } else {
                    btn_Signin.setEnabled(false);
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.Theme_AppCompat);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Signing In...");
                    progressDialog.show();


                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    onSigninSuccess();
                                    progressDialog.dismiss();
                                }
                            }, 3000);

                }
            }
        }
        );
   }


    public void onSigninSuccess() {
        btn_Signin.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    private void onSigninFail() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btn_Signin.setEnabled(true);
    }


    private boolean signinValidate() {
       boolean valid = true;
        Pattern pattern;
        Matcher matcher;

       String email = ed_Email.getText().toString();
       String password = ed_Password.getText().toString();

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        if(TextUtils.isEmpty(email)){
            ed_Email.setError("Enter a email address");
            valid = false;
        }
        else {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                ed_Email.setError("Enter a valid email address");
                valid = false;
            } else {
                ed_Email.setError(null);
            }
        }



        if (TextUtils.isEmpty(password)){
            ed_Password.setError("Enter password");
            valid = false;
        }
        else{

            if (!matcher.matches()){
                ed_Password.setError("contains at least 1 Upper case, 1 lower case,1 number and 1 special character ");
                valid = false;
            }
         else {
            ed_Password.setError(null);
            }
        }

       return valid;

    }
}

