package mock.intern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mock.intern.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    static SharedPreferences prefs = null;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("firstRun", MODE_PRIVATE);

        if (prefs.getBoolean("firstRun", true)) {
            Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();

        }
        else
        {
            //do nothing
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
