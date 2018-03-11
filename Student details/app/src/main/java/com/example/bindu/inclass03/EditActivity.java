package com.example.bindu.inclass03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {
    EditText details;
    RadioGroup rg;
    TextView tv, md;
    SeekBar sk;
    Student st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        details = (EditText) findViewById(R.id.details);
        tv = (TextView) findViewById(R.id.textViewNam);
        md = (TextView) findViewById(R.id.textViewmood);
        sk = (SeekBar) findViewById(R.id.seekBar);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        if (getIntent() != null && getIntent().getExtras() != null) {
            st=(Student)getIntent().getExtras().getSerializable(DisplayActivity.STUDEN_key);
            switch (getIntent().getExtras().getString(DisplayActivity.Value_key)){
                case "Name":
                    tv.setText("Name :");
                    details.setText(st.name);
                    break;
                case "Email":
                    tv.setText("Email :");
                    details.setText(st.email);
                    break;
                case "Dept":
                    findViewById(R.id.textViewdepts).setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    if(st.dept.equals("SIS"))
                        rg.check(R.id.radioButtonSIS);
                    else if(st.dept.equals("CS"))
                        rg.check(R.id.radioButton2CS);
                    else if(st.dept.equals("BIO"))
                        rg.check(R.id.radioButton3Bio);
                    else if(st.dept.equals("Others"))
                        rg.check(R.id.radiobuttonOthers);
                    break;
                case "Mood":
                    md.setVisibility(View.VISIBLE);
                    sk.setVisibility(View.VISIBLE);
                    sk.setProgress(Integer.parseInt(st.mood));
                    details.setVisibility(View.INVISIBLE);
                    break;
                default:
                    Toast.makeText(this, "In Default", Toast.LENGTH_SHORT).show();
                    break;
            }
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    st.dept=((RadioButton)findViewById(checkedId)).getText().toString();
                }
            });
            sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    st.mood=String.valueOf(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tv.getText().toString().equals("Name :"))
                    st.name=details.getText().toString();
                    else if(tv.getText().toString().equals("Email :"))
                    st.email=details.getText().toString();
                    if (st.equals(getIntent().getExtras().getSerializable(DisplayActivity.STUDEN_key))) {
                       setResult(RESULT_CANCELED);
                    } else {
                        Intent i = new Intent();
                        i.putExtra(DisplayActivity.STUDEN_key, st);
                        setResult(RESULT_OK, i);
                    }
                    finish();
                }
            });
        }
    }
}
