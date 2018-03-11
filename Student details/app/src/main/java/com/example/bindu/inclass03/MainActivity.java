package com.example.bindu.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    EditText sname;
    EditText semail;
    public static String Student_Key = "stu";
    String sdept;
    SeekBar sk;
    RadioGroup rg;
    int smood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Main Activity");
        sname = (EditText) findViewById(R.id.details);
        semail = (EditText) findViewById(R.id.editTextemail);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        sk=(SeekBar)findViewById(R.id.seekBar);
        sdept="SIS";
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                smood=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonSIS){
                    sdept = "SIS";
                }else if(checkedId==R.id.radioButton2CS){
                    sdept="CS";
                }else if(checkedId==R.id.radioButton3Bio){
                    sdept="BIO";
                }else {
                    sdept="Others";
                }
            }
        });
        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String name =sname.getText().toString();
                    String email =semail.getText().toString();
                    if (sname.equals("")|| semail.equals("") || sname.length() == 0 ||semail.length() == 0 ) {
                        Toast.makeText(MainActivity.this, "Please enter all the string values", Toast.LENGTH_LONG).show();
                    } else {
                        Intent i= new Intent(MainActivity.this,DisplayActivity.class);
                        Student std = new Student(name,email,sdept,String.valueOf(smood));
                        // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
                       i.putExtra(Student_Key,std);
                        startActivity(i);
                    }
            }
        });
    }
}
