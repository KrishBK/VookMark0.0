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


public class Signup extends AppCompatActivity {
    EditText ed_InputName,ed_Email,ed_Phone,ed_Password,ed_rePassword;
    TextView link;
    Button btn_SignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ed_InputName=findViewById(R.id.input_name);
        ed_Email=findViewById(R.id.input_email);
        ed_Phone=findViewById(R.id.input_mobile);
        ed_Password=findViewById(R.id.input_password);
        ed_rePassword=findViewById(R.id.input_reEnterPassword);
        btn_SignUp=findViewById(R.id.btn_signup);
        link = findViewById(R.id.link_login);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                
            }
        });
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              if (!signupValidate()) {
                                                  Log.d("Signup", "onClick: fail");
                                                  onSignupFail();
                                                  return;
                                              } else {
                                                  btn_SignUp.setEnabled(false);
                                                  final ProgressDialog progressDialog = new ProgressDialog(Signup.this,R.style.Theme_AppCompat);
                                                  progressDialog.setIndeterminate(true);
                                                  progressDialog.setMessage("Creating Account...");
                                                  progressDialog.show();


                                                  new android.os.Handler().postDelayed(
                                                          new Runnable() {
                                                              public void run() {
                                                                  onSignupSuccess();
                                                                  progressDialog.dismiss();
                                                              }
                                                          }, 3000);

                                              }
                                          }
                                      }
        );
    }


    public void onSignupSuccess() {
        btn_SignUp.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    private void onSignupFail() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        btn_SignUp.setEnabled(true);
    }


    private boolean signupValidate() {
        boolean valid = true;
        Pattern pattern;
        Matcher matcher,matcher1;
        String name = ed_InputName.getText().toString();
        String email = ed_Email.getText().toString();
        String mobile = ed_Phone.getText().toString();
        String password = ed_Password.getText().toString();
        String reEnterPassword = ed_rePassword.getText().toString();

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        matcher1 = pattern.matcher(reEnterPassword);


        if (TextUtils.isEmpty(name)) {
            ed_InputName.setError("Enter your name");
            valid = false;
        } else {
            if (name.length() < 3) {
                ed_InputName.setError("At least 3 characters");
                valid = false;
            } else {
                ed_InputName.setError(null);
            }
        }


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

        if(TextUtils.isEmpty(mobile)){
            ed_Phone.setError("Enter Mobile Number");
            valid = false;
        }
        else{
            if (!mobile.matches("\\d{10}")) {
                ed_Phone.setError("Enter Valid Mobile Number");
                valid = false;
            } else {
                ed_Phone.setError(null);
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



        if (TextUtils.isEmpty(reEnterPassword)){
            ed_rePassword.setError("Re-enter password");
            valid = false;
        }
        else{
            if(!matcher1.matches()){
                ed_rePassword.setError("contains at least 1 Upper case, 1 lower case,1 number and 1 special character ");
                valid = false;
            }
            else {
                if(!(reEnterPassword.equals(password))){
                    ed_rePassword.setError("Password does not match");
                    valid = false;
                }
                else {
                    ed_rePassword.setError(null);
                }
            }
        }

        return valid;

    }
}

