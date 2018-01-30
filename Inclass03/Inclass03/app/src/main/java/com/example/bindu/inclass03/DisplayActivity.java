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
    ImageButton iname;
    ImageButton iemail;
    ImageButton idept;
    ImageButton imood;
    public static final int Req_codename = 100;
    public static final int Req_codeemail = 200;
    public static final int Req_codedept = 300;
    public static final int Req_codemood=400;
    static String Value_key = "value";
    static String name_key = "value";
    static String text_key = "text";
    static String bool_key = "bool";
    static String bool_key1 = "bool1";
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
            tname.setText("Name:    " + st.name);
            temail.setText("Email:    " + st.email);
            tdept.setText("Department:    " + st.dept);
            tmood.setText("Mood:    "+st.mood+"% Positive");

            findViewById(R.id.imageButtonname).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(name_key, st.name);
                    i.putExtra(text_key,"Name: ");
                    i.putExtra(bool_key,false);

                    startActivityForResult(i, Req_codename);
                }
            });
            findViewById(R.id.imageButtonemail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(name_key, st.email);
                    i.putExtra(bool_key,false);
                    i.putExtra(text_key,"Email: ");
                    startActivityForResult(i, Req_codeemail);
                }
            });
            findViewById(R.id.imageButtondept4).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(name_key, st.dept);
                    i.putExtra(bool_key,true);
                    startActivityForResult(i, Req_codedept);
                }
            });
            findViewById(R.id.imageButtonmood).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent("com.example.bindu.inclass03.intent.action.VIEW");
                    i.putExtra(name_key, st.mood);
                    i.putExtra(bool_key1,true);
                    i.putExtra(text_key,"");
                    startActivityForResult(i, Req_codemood);
                }
            });
        }
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
        if (requestCode == Req_codename) {
            if (resultCode == RESULT_CANCELED) {
                tname.append("");

            } else if (resultCode == RESULT_OK) {
                st.name=data.getExtras().getString(Value_key);
                tname.setText("Name:    " + st.name);
            }

        }

        if (requestCode == Req_codeemail) {
            if (resultCode == RESULT_CANCELED) {
                temail.append("");

            } else if (resultCode == RESULT_OK) {
                st.email=data.getExtras().getString(Value_key);
                temail.setText("Email:    " + st.email);
            }

        }

        if (requestCode == Req_codedept) {
            if (resultCode == RESULT_CANCELED) {
                tdept.append("");

            } else if (resultCode == RESULT_OK) {
                st.dept=data.getExtras().getString(Value_key);
                tdept.setText("Department:    " + st.dept);
            }

        }

        if (requestCode == Req_codemood) {
            if (resultCode == RESULT_CANCELED) {
                tmood.append("");

            } else if (resultCode == RESULT_OK) {
                st.mood=data.getExtras().getString(Value_key);
                tmood.setText("Mood:    " +st.mood +"% Positive");
            }

        }
    }

}