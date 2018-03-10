package com.example.bindu.bmicalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText years;
EditText Pounds;
EditText feet;
EditText inches;
TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btn = (Button) findViewById(R.id.buttonCalculate);
        years = (EditText) findViewById(R.id.editTextyear);
        Pounds = (EditText) findViewById(R.id.editTextpounds);
        feet = (EditText) findViewById(R.id.editTextfeet);
        inches = (EditText) findViewById(R.id.editTextinches);
        res=(TextView)findViewById(R.id.textViewResult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try{
                    int age = Integer.parseInt(years.getText().toString());
                    Double weight = Double.parseDouble(Pounds.getText().toString());
                    int feeti = Integer.parseInt(feet.getText().toString());
                    int inch = Integer.parseInt(inches.getText().toString());

                    if (age < 18){
                        Toast.makeText(MainActivity.this,"Age should be 18 and above",Toast.LENGTH_LONG).show();
                    }
                    else {
                        int height = 12*feeti+inch;
                        float bmi = (float) ((703.0 *weight)/(height*height));
                        float loseweight = (float) (((bmi-25)*(height*height))/703.0);
                        float gainweight = (float) (((18.5-bmi)*(height*height))/703);
                        String bmi1=String.format("%.2f",bmi);
                        String normal = "Result \n BMI = "+bmi1+"<font color=#a6d854>(Normal)</font> \n Normal BMI range: 18.5-25\n Keep up the good work !!";
                        String underweight = "Result BMI = "+bmi1+"<font color=#ff4040>(Underweight)</font> Normal BMI range: 18.5-25 You will need to gain "+gainweight+" lbs to reach a BMI of 18.5";
                        String overweight = "Result BMI = "+bmi1+"<font color=#FF8800>(Overweight)</font> Normal BMI range: 18.5-25 You will need to lose "+loseweight+" lbs to reach a BMI of 25";
                        if (bmi<18.5){
                            res.setText(Html.fromHtml(underweight));
                        }
                        else if(bmi<25){
                            res.setText(Html.fromHtml(normal));
                        }
                        else {
                            res.setText(Html.fromHtml(overweight));
                        }
                    }

                } catch (NumberFormatException ex){
                    Toast.makeText(MainActivity.this,"One of the fields is missing Age or Weight or Height",Toast.LENGTH_LONG).show();
                }



            }
        });



    }
}

