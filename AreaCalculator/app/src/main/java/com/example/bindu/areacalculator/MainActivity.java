package com.example.bindu.areacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView L1,L2,shape,res;
EditText e1,e2;
Button cal,clr;
ImageView Tri,Sqr,Cir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        L1=(TextView)findViewById(R.id.textViewL1);
        L2=(TextView)findViewById(R.id.textViewL2);
        shape=(TextView)findViewById(R.id.textViewShape);
        res=(TextView)findViewById(R.id.textView4);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        cal=(Button)findViewById(R.id.buttonCal);
        clr=(Button)findViewById(R.id.buttonClear);
        Tri=(ImageView)findViewById(R.id.imageViewT);
        Sqr=(ImageView)findViewById(R.id.imageViewS);
        Cir=(ImageView)findViewById(R.id.imageViewC);
        Tri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                shape.setText("Triangle");
                L1.setText("Base :");
                L2.setText("Height :");
            }
        });
        Sqr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape.setText("Square");
                L2.setVisibility(View.INVISIBLE);
                L1.setText("Side :");
                e2.setVisibility(View.INVISIBLE);
            }
        });
        Cir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shape.setText("Circle");
                L2.setVisibility(View.INVISIBLE);
                L1.setText("Radius:");
                e2.setVisibility(View.INVISIBLE);
            }
        });
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String shp= shape.getText().toString();
                Double area;
                try{
                    switch (shp){
                        case "Triangle":
                            Double base= Double.parseDouble(e1.getText().toString());
                            Double height = Double.parseDouble(e2.getText().toString());
                             area= 0.5*base*height;
                            res.setText("Area of Traingle is "+area);
                            break;
                        case "Square":
                            Double side= Double.parseDouble(e1.getText().toString());
                            area = side*side;
                            res.setText("Area of Square is "+area);
                            break;
                        case "Circle":
                            Double radius= Double.parseDouble(e1.getText().toString());
                            area = 3.14*radius*radius;
                            res.setText("Area of Circle is "+area);
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "Select a Shape", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this, "Enter the lengths", Toast.LENGTH_SHORT).show();
                }
            }
        });
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L2.setVisibility(View.VISIBLE);
                e2.setVisibility(View.VISIBLE);
                L1.setText("Length 1:");
                L2.setText("Length 2:");
                e1.setText("");
                e2.setText("");
                shape.setText("Select a shape");
                res.setText("");
            }
        });
    }
}
