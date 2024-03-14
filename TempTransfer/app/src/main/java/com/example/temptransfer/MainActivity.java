package com.example.temptransfer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_Click(View view){
        EditText edtTemp = (EditText) findViewById(R.id.edtTemp);
        TextView  txvShow = (TextView) findViewById((R.id.txvShow));

        double degreeC = Double.parseDouble(edtTemp.getText().toString());
        double degreeF = (9.0* degreeC) / 5.0 + 32.0;

        txvShow.setText("華氏溫度: "+ String.format("%.2f", degreeF));
    }
}