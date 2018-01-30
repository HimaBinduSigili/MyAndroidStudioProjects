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

public class EditActivity extends AppCompatActivity {
    EditText details;
    RadioGroup rg;
    TextView tv,md;
    SeekBar sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        details= (EditText)findViewById(R.id.details);
        tv = (TextView)findViewById(R.id.textViewNam);
        tv.setText(getIntent().getExtras().getString(DisplayActivity.text_key));
        md=(TextView)findViewById(R.id.textViewmood);
        sk = (SeekBar)findViewById(R.id.seekBar);
        details.setText(getIntent().getExtras().getString(DisplayActivity.name_key));
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        RadioButton rb =(RadioButton)findViewById(rg.getCheckedRadioButtonId());
        if(getIntent().getExtras().getBoolean(DisplayActivity.bool_key)){
            findViewById(R.id.textViewdepts).setVisibility(View.VISIBLE);
            rg.setVisibility(View.VISIBLE);
            details.setVisibility(View.INVISIBLE);
        }
        if(getIntent().getExtras().getBoolean(DisplayActivity.bool_key1)){
            md.setVisibility(View.VISIBLE);
            sk.setVisibility(View.VISIBLE);
            details.setVisibility(View.INVISIBLE);
        }

        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getIntent().getExtras().getBoolean(DisplayActivity.bool_key1)){
                    Intent j = new Intent();
                    j.putExtra(DisplayActivity.Value_key,""+sk.getProgress());
                    setResult(RESULT_OK, j);
                    finish();
                }
                else if(!getIntent().getExtras().getBoolean(DisplayActivity.bool_key)) {
                    String text = details.getText().toString();
                    if (text==null || text.length() == 0) {
                        setResult(RESULT_CANCELED);

                    } else {
                        Intent i = new Intent();
                        i.putExtra(DisplayActivity.Value_key, text);
                        setResult(RESULT_OK, i);
                    }
                    finish();
                }
                else
                {
                    RadioButton r = ((RadioButton) findViewById(rg.getCheckedRadioButtonId()));
                    String text = r.getText().toString();
                    Intent i = new Intent();
                    i.putExtra(DisplayActivity.Value_key, text);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}
