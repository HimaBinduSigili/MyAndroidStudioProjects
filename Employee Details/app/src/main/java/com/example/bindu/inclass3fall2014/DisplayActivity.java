package com.example.bindu.inclass3fall2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    TextView tname;
    TextView tage;
    TextView temail;
    TextView tphone;
    TextView tdept;
    public static final int Req_code = 100;
    static String EMPLOYEE_keyy = "emp";
    static String EDIT_KEY="edit";
    Employee em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tname = (TextView) findViewById(R.id.textViewname);
        tage = (TextView) findViewById(R.id.textViewage);
        temail = (TextView) findViewById(R.id.textViewemail);
        tphone = (TextView) findViewById(R.id.textViewdepts);
        tdept = (TextView) findViewById(R.id.textViewdept);

        if (getIntent() != null && getIntent().getExtras() != null) {
            em= (Employee) getIntent().getExtras().getSerializable(MainActivity.Employee_Key);
            if (em.name == null || em.age == null || em.email == null || em.phone == null || em.name.length() == 0 || em.age.length() == 0 || em.email.length() == 0 || em.phone.length() == 0) {
                Toast.makeText(DisplayActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
            DisplayResults(em);

            findViewById(R.id.imageButtonname).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                    i.putExtra(EMPLOYEE_keyy, em);
                    i.putExtra(EDIT_KEY,"Name");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtonage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                    i.putExtra(EMPLOYEE_keyy, em);
                    i.putExtra(EDIT_KEY,"Age");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtonemail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                    i.putExtra(EMPLOYEE_keyy, em);
                    i.putExtra(EDIT_KEY,"Email");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtonphone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                    i.putExtra(EMPLOYEE_keyy, em);
                    i.putExtra(EDIT_KEY,"Phone");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtondept4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass3fall2014.intent.action.VIEW");
                    i.putExtra(EMPLOYEE_keyy, em);
                    i.putExtra(EDIT_KEY,"Dept");
                    startActivityForResult(i, Req_code);
                }
            });
        }
    }
    public  void DisplayResults(Employee em1){
        tname.setText("Name:    " + em1.name);
        tage.setText("Age:    " + em1.age);
        temail.setText("Email:    " + em1.email);
        tphone.setText("Phone:    " + em1.phone);
        tdept.setText("Department:    " + em1.department);

    }
        /*
         * Dispatch incoming result to the correct fragment.
         *
         * @param requestCode
         * @param resultCode
         * @param data
         */
        //@Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Req_code) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "No Changes are made", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_OK) {
                em=(Employee) data.getExtras().getSerializable(EMPLOYEE_keyy);
                DisplayResults(em);
            }

        }

    }

    }