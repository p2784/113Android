package com.example.radiobutton1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outpuStr = "";

                RadioButton boy = (RadioButton) findViewById(R.id.rdbBoy);
                RadioButton girl = (RadioButton) findViewById(R.id.rdbGirl);
                if(boy.isChecked())
                    outpuStr+="男生\n";
                else if (girl.isChecked())
                    outpuStr += "女生\n";

                RadioGroup type = (RadioGroup) findViewById(R.id.rgType);
//                switch (type.getCheckedRadioButtonId()){
//                    case R.id.rdbAdult:
//                        outpuStr +="全票\n" ;
//                    break;
//                    case R.id.rdbChild:
//                        outpuStr +="兒童票\n";
//                        break;
//                    case R.id.rdbStudent:
//                        outpuStr +="學生票\n";
//                        break;
//                }
                if(type.getCheckedRadioButtonId() == R.id.rdbAdult)
                    outpuStr +="全票\n" ;
                else if(type.getCheckedRadioButtonId() == R.id.rdbChild)
                    outpuStr +="兒童票\n";
                else
                    outpuStr +="學生票\n";

                    TextView output = (TextView) findViewById(R.id.lblOutput);
                output.setText(outpuStr);
            }
        });
    }
}