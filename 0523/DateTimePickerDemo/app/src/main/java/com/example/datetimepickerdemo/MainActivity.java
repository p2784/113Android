package com.example.datetimepickerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
implements DatePickerDialog.OnDateSetListener
{

    private TextView txvoutput;

    private Calendar dt = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvoutput = (TextView) findViewById(R.id.txvoutput);

        Button btn = (Button) findViewById(R.id.btndate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dlg = new DatePickerDialog(MainActivity.this,MainActivity.this,
                        dt.get(Calendar.YEAR), //註解, 設定初始年
                        dt.get(Calendar.MONTH), //註解, 設定初始月
                        dt.get(Calendar.DAY_OF_MONTH));//註解, 設定初始日
                dlg.show();
            }
        });
        Button btnTime = (Button) findViewById(R.id.btnTime);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dlg = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                txvoutput.setText("時間：" + hourOfDay + "時" + minute + "分");
                            }
                        },
                        dt.get(Calendar.HOUR), //註解, 設定初始時
                        dt.get(Calendar.MINUTE), //註解, 設定初始分
                        true);//註解, 是否顯示24小時制
                dlg.show();
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txvoutput.setText("日期：" + year + "年" + (month + 1) + "月" + dayOfMonth );
    }
}