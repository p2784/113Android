package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvShow = (TextView) findViewById(R.id.txvShow);
        txvShow.setTextSize(36);
        Button btnCalc = (Button) findViewById(R.id.btnCalc);
        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText edtHeight = (EditText) findViewById(R.id.edtHeight);
        EditText edtWeight = (EditText) findViewById(R.id.edtWeight);

        if (v.getId() == R.id.btnCalc) {
            String heightStr = edtHeight.getText().toString();
            String weightStr = edtWeight.getText().toString();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                txvShow.setText("請於身高、體重填入數字，勿留白");
                return;
            }

            double height, weight;


            height = Double.parseDouble(heightStr);
            weight = Double.parseDouble(weightStr);


            if (height <= 0 || weight <= 0) {
                txvShow.setText("身高體重必須大於0");
                return;
            }

            double bmi = weight / Math.pow(height / 100.0, 2);

            if (bmi >= 24)
                txvShow.setTextColor(Color.RED);
            else if (bmi < 18.5)
                txvShow.setTextColor(Color.BLUE);
            else
                txvShow.setTextColor(Color.GREEN);

            txvShow.setText(String.format("%.2f", bmi));
        } else {
            edtHeight.setText("0");
            edtWeight.setText("0");
            txvShow.setText("");
        }
    }
}