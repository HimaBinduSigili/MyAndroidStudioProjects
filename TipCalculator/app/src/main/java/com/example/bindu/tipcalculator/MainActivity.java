package com.example.bindu.tipcalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText edt;
TextView ti;
TextView tot;
TextView seek;
RadioGroup rg;
SeekBar sk;
int prog=25;
int tippercent=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        btn = (Button)findViewById(R.id.buttonExit);
        edt = (EditText)findViewById(R.id.editText);
        ti = (TextView)findViewById(R.id.textViewtipval);
        ti.setText("0.00");
        tot =(TextView)findViewById(R.id.textViewtotalval);
        tot.setText("0.00");
        rg =(RadioGroup)findViewById(R.id.radioGroup);
        sk=(SeekBar)findViewById(R.id.seekBar);
        seek=(TextView)findViewById(R.id.textViewseek);
        sk.setMax(50);
        sk.setProgress(prog);
        seek.setText(""+prog);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, final int i) {
                try {
                    RadioButton rb= (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    if(i==R.id.radioButton10){
                        sk.setProgress(prog);
                        seek.setText("25%");
                        float bill = (float) Double.parseDouble(edt.getText().toString());
                        tippercent=10;
                        float d = (float) (bill * 0.1);
                        double total = d + bill;
                        ti.setText(String.valueOf(d));
                        tot.setText(String.valueOf(total));

                    }else if(i==R.id.radioButton15){
                        sk.setProgress(prog);
                        seek.setText("25%");
                        tippercent=15;
                        float bill = (float) Double.parseDouble(edt.getText().toString());;
                        double d = (double) (bill * 0.15);
                        double total = d + bill;
                        ti.setText(String.valueOf(d));
                        tot.setText(String.valueOf(total));

                    }else if(i==R.id.radioButton18){
                        sk.setProgress(prog);
                        seek.setText("25%");
                        tippercent=18;
                        float bill = (float)Double.parseDouble(edt.getText().toString());
                        double d = (double) (bill * 0.18);
                        double total = d + bill;
                        ti.setText(String.valueOf(d));
                        tot.setText(String.valueOf(total));

                    }else if (i==R.id.radioButtoncustom) {
                        float bill = (float) Double.parseDouble(edt.getText().toString());
                        double d = (double) (bill * prog / 100.0);
                        double total = d + bill;
                        ti.setText(String.valueOf(d));
                        tot.setText(String.valueOf(total));
                        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int j, boolean b) {
                                try{if(rg.getCheckedRadioButtonId()==R.id.radioButtoncustom) {
                                    seek.setText("" + j+"%");
                                    tippercent=j;
                                    float bill = (float) Double.parseDouble(edt.getText().toString());
                                    double d = (double) (bill * j / 100.0);
                                    double total = d + bill;
                                    ti.setText(String.valueOf(d));
                                    tot.setText(String.valueOf(total));

                                }}
                                catch (NumberFormatException ex){
                                    ti.setText("0.00");
                                    tot.setText("0.00");
                                    edt.setError("Enter Bill Total");
                                }
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {


                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {

                            }
                        });

                        //int pro = sk.getProgress();
                    }
                }
                catch (NumberFormatException ex){
                    ti.setText("0.00");
                    tot.setText("0.00");
                    edt.setError("Enter Bill Total");

                }
            }
        });
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    float bill = (float) Double.parseDouble(edt.getText().toString());
                    double d = (double) (bill * tippercent / 100.0);
                    double total = d + bill;
                    ti.setText(String.valueOf(d));
                    tot.setText(String.valueOf(total));
                }catch (NumberFormatException e){
                        ti.setText("0.00");
                        tot.setText("0.00");
                        edt.setError("Enter Bill Total");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
