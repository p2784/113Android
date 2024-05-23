package com.example.explicitintentdemo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factivity);

        convertTempture();

        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            finish();
        });
    }

    private void convertTempture() {
        int c ;
        double f = 0.0;
        String place = "";
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            c = bundle.getInt("TEMPC", 0);
            f = c * 9.0 / 5.0 + 32.0;
            place = bundle.getString("PLACE","");
            TextView output = findViewById(R.id.lblOutput);
            output.setText(place + "\n華氏溫度 " + Double.toString(f));
        }
    }
}