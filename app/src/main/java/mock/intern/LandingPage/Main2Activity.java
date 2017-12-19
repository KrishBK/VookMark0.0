package mock.intern.LandingPage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import mock.intern.R;

public class Main2Activity extends AppCompatActivity {

    private int mColumnCount = 1;
    private RecyclerView recyclerView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = this;
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new CardsAdapter(DummyContent.ITEMS));
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLayoutManager();
            }
        });
    }
    private void setLayoutManager() {
        if (mColumnCount== 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            findViewById(R.id.imageView).setBackgroundResource(R.drawable.grid);
            mColumnCount=1;
        } else {
            findViewById(R.id.imageView).setBackgroundResource(R.drawable.list);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
            mColumnCount=0;
        }
    }
}
