package com.example.bindu.inclass02fall2015v2;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView pm,result,prev;
EditText hrs,mins;
Switch ampm;
Button EST,CST,MST,PST,Clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pm=(TextView)findViewById(R.id.textViewAMPM);
        result=(TextView)findViewById(R.id.textViewResult);
        hrs=(EditText)findViewById(R.id.editTexthrs);
        mins=(EditText)findViewById(R.id.editTextmnts);
        ampm=(Switch)findViewById(R.id.switch1);
        EST=(Button)findViewById(R.id.buttonEST);
        CST=(Button)findViewById(R.id.buttonCST);
        MST=(Button)findViewById(R.id.buttonMST);
        PST=(Button)findViewById(R.id.buttonPST);
        Clear=(Button)findViewById(R.id.buttonClear);
        prev=(TextView)findViewById(R.id.textViewPrev);
        ampm.setChecked(true);
        pm.setText("PM");
        ampm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ampm.isChecked())
                    pm.setText("PM");
                else
                    pm.setText("AM");
            }
        });
        EST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hours= hrs.getText().toString();
                String mints= mins.getText().toString();
                int h=Integer.parseInt(hours);
                int m=Integer.parseInt(mints);
                if((h>=0 && h<=12)&& (m>=0 && m <=60)){
                    if(h>=5){
                        int est1= h-5;
                        if(h-5==0)
                            est1=12;
                        prev.setText("");
                        String s= est1+" : "+mints+" "+pm.getText().toString();
                        result.setText("EST:    "+s);
                    }else {
                        int est2= 12+h-5;
                        String s;
                        if(pm.getText().toString().equals("PM")){
                            prev.setText("");
                            s= est2+" : "+mints+" "+"AM";}
                        else{
                            prev.setText("Previous Day");
                            s= est2+" : "+mints+" "+"PM";
                        }

                        result.setText("EST:    "+s);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Please enter correct time",Toast.LENGTH_LONG).show();
                }
            }
        });
        CST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hours= hrs.getText().toString();
                String mints= mins.getText().toString();
                int h=Integer.parseInt(hours);
                int m=Integer.parseInt(mints);
                if((h>=0 && h<=12)&& (m>=0 && m <=60)){
                    if(h>=6 ){
                        int cst1= h-6;
                        if(h-6==0)
                            cst1=12;
                        prev.setText("");
                        String s= cst1+" : "+mints+" "+pm.getText().toString();
                        result.setText("EST:    "+s);
                    }else {
                        int cst2= 12+h-6;
                        String s;
                        if(pm.getText().toString().equals("PM")){
                            prev.setText("");
                            s= cst2+" : "+mints+" "+"AM";}
                        else{
                            prev.setText("Previous Day");
                            s= cst2+" : "+mints+" "+"PM";
                        }

                        result.setText("CST:    "+s);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Please enter correct time",Toast.LENGTH_LONG).show();
                }
            }
        });
        MST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hours= hrs.getText().toString();
                String mints= mins.getText().toString();
                int h=Integer.parseInt(hours);
                int m=Integer.parseInt(mints);
                if((h>=0 && h<=12)&& (m>=0 && m <=60)){
                    if(h>=7 ){
                        int mst1= h-7;
                        if(h-7==0)
                            mst1=12;
                        prev.setText("");
                        String s= mst1+" : "+mints+" "+pm.getText().toString();
                        result.setText("MST:    "+s);
                    }else {
                        int mst2= 12+h-7;
                        String s;
                        if(pm.getText().toString().equals("PM")){
                            prev.setText("");
                            s= mst2+" : "+mints+" "+"AM";}
                        else{
                            prev.setText("Previous Day");
                            s= mst2+" : "+mints+" "+"PM";
                        }

                        result.setText("MST:    "+s);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Please enter correct time",Toast.LENGTH_LONG).show();
                }
            }
        });
        PST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hours= hrs.getText().toString();
                String mints= mins.getText().toString();
                int h=Integer.parseInt(hours);
                int m=Integer.parseInt(mints);
                if((h>=0 && h<=12)&& (m>=0 && m <=60)){
                    if(h>=8 ){
                        int pst1= h-8;
                        if(h-8==0)
                            pst1=12;
                        prev.setText("");
                        String s= pst1+" : "+mints+" "+pm.getText().toString();
                        result.setText("PST:    "+s);
                    }else {
                        int pst2= 12+h-8;
                        String s;
                        if(pm.getText().toString().equals("PM")){
                            prev.setText("");
                            s= pst2+" : "+mints+" "+"AM";}
                        else{
                            prev.setText("Previous Day");
                            s= pst2+" : "+mints+" "+"PM";
                        }

                        result.setText("PST:    "+s);
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Please enter correct time",Toast.LENGTH_LONG).show();
                }
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hrs.setText("");
                mins.setText("");
                result.setText("Result:");
                prev.setText("");
            }
        });

    }
}
