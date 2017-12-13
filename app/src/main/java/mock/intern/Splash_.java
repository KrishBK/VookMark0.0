package mock.intern;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash_ extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


                    Intent i = new Intent(getBaseContext(), WelcomeActivity.class);
                    startActivity(i);
                    finish();

    }
}
