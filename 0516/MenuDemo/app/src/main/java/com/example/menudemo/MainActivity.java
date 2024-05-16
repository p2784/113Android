package com.example.menudemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 建立選單
        MenuInflater inflater = getMenuInflater();// 取得MenuInflater物件
        inflater.inflate(R.menu.menu_main, menu);// 載入選單資源
        return super.onCreateOptionsMenu(menu);// 回傳true表示顯示選單
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {// 選單項目被選取時呼叫
        TextView output = findViewById(R.id.lblOutput);
        int tmp;
        double result;

        EditText txtTemp = findViewById(R.id.txtTemp);
        tmp = Integer.parseInt(txtTemp.getText().toString());

        int itemId = item.getItemId();
        if (itemId == R.id.toF) {
            result = (tmp * 9.0 / 5.0) + 32.0;
            output.setText("華氏溫度: " + String.format("%.2f", result));
        } else if (itemId == R.id.toC) {
            result = (tmp - 32) * 5.0 / 9.0;
            output.setText("攝氏溫度: " + String.format("%.2f", result));
        }
        return super.onOptionsItemSelected(item);
    }
}