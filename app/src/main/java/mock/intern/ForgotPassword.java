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


public class ForgotPassword extends AppCompatActivity {
    EditText ed_Email,ed_User;
    TextView link,f_p;
    Button btn_Signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ed_Email=findViewById(R.id.forgotPassword_email);
        ed_User=findViewById(R.id.forgotPassword_username);
        btn_Signin = findViewById(R.id.btn_reset);
        f_p=findViewById(R.id.forgotPassword);

        link = findViewById(R.id.link_login);

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(ForgotPassword.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        btn_Signin.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              if (!Validate()) {
                                                  Log.d("Signup", "onClick: fail");
                                                  Fail();
                                                  return;
                                              } else {
                                                  btn_Signin.setEnabled(false);
                                                  final ProgressDialog progressDialog = new ProgressDialog(ForgotPassword.this,R.style.Theme_AppCompat);
                                                  progressDialog.setIndeterminate(true);
                                                  progressDialog.setMessage("Processing...");
                                                  progressDialog.show();


                                                  new android.os.Handler().postDelayed(
                                                          new Runnable() {
                                                              public void run() {
                                                                  Success();
                                                                  progressDialog.dismiss();
                                                              }
                                                          }, 3000);

                                              }
                                          }
                                      }
        );
    }


    public void Success() {
        btn_Signin.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    private void Fail() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        btn_Signin.setEnabled(true);
    }


    private boolean Validate() {
        boolean valid = true;

        String email = ed_Email.getText().toString();
        String name = ed_User.getText().toString();

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

        if (TextUtils.isEmpty(name)) {
            ed_User.setError("Enter your name");
            valid = false;
        } else {
            if (name.length() < 3) {
                ed_User.setError("At least 3 characters");
                valid = false;
            } else {
                ed_User.setError(null);
            }
        }
        return valid;

    }
}

