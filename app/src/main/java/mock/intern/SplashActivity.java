package mock.intern;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mock.intern.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences prefs = null;
    @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = getSharedPreferences("firstRun", MODE_PRIVATE);

        if (prefs.getBoolean("firstRun", true)) {
            Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
            startActivity(intent);
            prefs.edit().putBoolean("firstRun", false).commit();
        }
        else
        {
            //do nothing
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }


    }
}
