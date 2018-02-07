package com.example.bindu.todolist;

/**
 * Created by bindu on 2/1/2018.
 * homework Assignment 2
 * CreateActivity.java
 * Hima Bindu Sigili
 * Group 15
 */
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import java.util.Calendar;



public class CreateActivity extends  AppCompatActivity {
static  EditText title,date,time;
Button save;
RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        setTitle("Create Task");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        title=(EditText)findViewById(R.id.editTexttitle);
        date=(EditText)findViewById(R.id.editTextdate);
        date.setKeyListener(null);
        time=(EditText)findViewById(R.id.editTexttime);
        time.setKeyListener(null);
        rg =(RadioGroup)findViewById(R.id.radioGroupPriority);
        save = (Button)findViewById(R.id.buttonsave);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker(view);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker(view);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titl = title.getText().toString();
                String dat = date.getText().toString();
                String tim = time.getText().toString();
                RadioButton rb = (RadioButton)findViewById(rg.getCheckedRadioButtonId());
                String prior = rb.getText().toString();
                if(titl.equals("")||titl.length()==0||dat.equals("")||dat.length()==0||tim.equals("")||tim.length()==0||prior.equals("")||prior.length()==0){
                    setResult(RESULT_CANCELED);
                }else {
                    Intent i = new Intent();
                    //ls.add(new Tasks(titl,dat,tim,prior));
                    i.putExtra(MainActivity.tasks, new Tasks(titl,dat,tim,prior));

                    setResult(RESULT_OK, i);

                }
                finish();
            }
        });




    }

    public void datePicker(View view){
       DialogFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(),"Date");
    }

    public static class DatePickerFragment extends DialogFragment implements
            DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c =  Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this,year,month,day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            date.setText(i2 + "/" + (i1 + 1) + "/" + i);
        }
    }
    public void timePicker(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            if(hourOfDay>12){
                if(minute>=10)
                    time.setText(hourOfDay-12+ ":" + minute+" PM");
                else
                    time.setText(hourOfDay-12+ ":0"+ minute+" PM");
            }else if (hourOfDay==0){
                if(minute>=10)
                time.setText("12:" + minute+" AM");
                else
                    time.setText("12:0" + minute+" AM");
            }else if(hourOfDay==12){
                if(minute>=10)
                time.setText(hourOfDay+ ":" + minute+" PM");
                else
                    time.setText(hourOfDay+ ":0" + minute+" PM");
            }else{
                if(minute>=10)
                time.setText(hourOfDay+ ":" + minute+" AM");
                else
                    time.setText(hourOfDay+ ":0" + minute+" AM");

            }
            // Do something with the time chosen by the user

        }
    }
}