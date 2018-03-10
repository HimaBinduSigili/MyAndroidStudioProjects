package com.example.bindu.inclass3fall2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText ename;
EditText eage;
EditText eemail;
EditText ephone;
public static String Employee_Key = "emp";
String sdept;
RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ename = (EditText) findViewById(R.id.details);
        eage = (EditText) findViewById(R.id.editTextage);
        eemail = (EditText) findViewById(R.id.editTextemail);
        ephone = (EditText) findViewById(R.id.editTextphone);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        sdept="SIS";
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
               if(checkedId==R.id.radioButtonSIS){
                   sdept = "SIS";
               }else if(checkedId==R.id.radioButton2CS){
                   sdept="CS";
               }else{
                   sdept="Health Informatics";
               }
            }
        });
        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        String sname =ename.getText().toString();
                        String semail =eemail.getText().toString();
                        int sage=Integer.parseInt(eage.getText().toString());
                        int sphone=Integer.parseInt(ephone.getText().toString());

                        if (sname.equals("")|| semail.equals("") || sname.length() == 0 ||semail.length() == 0 ) {
                           Toast.makeText(MainActivity.this, "Please enter all the string values", Toast.LENGTH_LONG).show();
                        } else {
                          /* Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                           i.addCategory(Intent.CATEGORY_DEFAULT);*/
                          Intent i= new Intent(MainActivity.this,DisplayActivity.class);
                           Employee empl = new Employee(sname,String.valueOf(sage),semail,String.valueOf(sphone), sdept);

                       // Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.uncc.edu"));
                           i.putExtra(Employee_Key,empl);
                        startActivity(i);
                       }
                    }
                    catch (NumberFormatException ex){
                        Toast.makeText(MainActivity.this, "Please enter all the num values", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
}
