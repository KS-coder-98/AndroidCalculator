package pl.krzysiek.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get title from activity and hide them
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        final Button btnSimple = findViewById(R.id.buttonSimple);
        final Button btnAdvanced = findViewById(R.id.buttonAdvanced);
        final Button btnAbout = findViewById(R.id.buttonAbout);
        final Button btnExit = findViewById(R.id.buttonExit);

        btnSimple.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SimpleCalculatorActivity.class);
            startActivity(intent);
        });

        btnAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        btnAdvanced.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AdvancedCalculatorActivity.class);
            startActivity(intent);
        });

        btnExit.setOnClickListener(v -> {
            this.finishAffinity();
        });
    }
}