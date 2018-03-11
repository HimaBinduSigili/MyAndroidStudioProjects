package com.example.bindu.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
    TextView tname;
    TextView temail;
    TextView tdept;
    TextView tmood;
    public static final int Req_code = 100;
    static String Value_key = "value";
    static String STUDEN_key = "stud";
    Student st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        tname = (TextView) findViewById(R.id.textViewname);
        temail = (TextView) findViewById(R.id.textViewemail);
        tdept = (TextView) findViewById(R.id.textViewdept);
        tmood = (TextView) findViewById(R.id.textViewmood);

        if (getIntent() != null && getIntent().getExtras() != null) {
            st = (Student) getIntent().getExtras().getSerializable(MainActivity.Student_Key);
            if (st.name == null || st.email == null || st.name.length() == 0|| st.email.length() == 0 ) {
                Toast.makeText(DisplayActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
            DisplayResults(st);

            findViewById(R.id.imageButtonname).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(STUDEN_key, st);
                    i.putExtra(Value_key,"Name");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtonemail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(Value_key, "Email");
                    i.putExtra(STUDEN_key,st);
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtondept4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(STUDEN_key, st);
                    i.putExtra(Value_key,"Dept");
                    startActivityForResult(i, Req_code);
                }
            });
            findViewById(R.id.imageButtonmood).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(STUDEN_key, st);
                    i.putExtra(Value_key,"Mood");
                    startActivityForResult(i, Req_code);
                }
            });
        }
    }

public  void DisplayResults(Student st1){
    tname.setText("Name:    " + st1.name);
    temail.setText("Email:    " + st1.email);
    tdept.setText("Department:    " + st1.dept);
    tmood.setText("Mood:    "+st1.mood+"% Positive");
}
    //@Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Req_code) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "No Changes made", Toast.LENGTH_SHORT).show();

            } else if (resultCode == RESULT_OK) {
                st=(Student)data.getExtras().getSerializable(STUDEN_key);
                DisplayResults(st);
            }

        }

    }

}