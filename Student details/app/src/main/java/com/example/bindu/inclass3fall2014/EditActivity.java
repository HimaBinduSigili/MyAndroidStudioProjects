package com.example.bindu.inclass3fall2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class EditActivity extends AppCompatActivity {
    EditText details;
    RadioGroup rg;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        details = (EditText) findViewById(R.id.details);
        tv = (TextView) findViewById(R.id.textViewNam);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        if (getIntent() != null && getIntent().getExtras() != null) {
            final Employee employee= (Employee) getIntent().getExtras().getSerializable(DisplayActivity.EMPLOYEE_keyy);
            switch (getIntent().getExtras().getString(DisplayActivity.EDIT_KEY)) {
                case "Name":
                    tv.setText("Name :");
                    details.setText(employee.name);
                    break;
                case "Age":
                    tv.setText("Age :");
                    details.setText(employee.age);
                    break;
                case "Email":
                    tv.setText("Email :");
                    details.setText(employee.email);
                    break;
                case "Phone":
                    tv.setText("Phone :");
                    details.setText(employee.phone);
                    break;
                case "Dept":
                    tv.setVisibility(View.INVISIBLE);
                    details.setVisibility(View.INVISIBLE);
                    findViewById(R.id.textViewdepts).setVisibility(View.VISIBLE);
                    rg.setVisibility(View.VISIBLE);
                    if(employee.department.equals("CS"))
                        rg.check(R.id.radioButton2CS);
                    else if(employee.department.equals("Health Informatics"))
                        rg.check(R.id.radioButton3Health);
                    else if(employee.department.equals("SIS"))
                        rg.check(R.id.radioButtonSIS);
                    break;
                default:
                    Toast.makeText(this, "In Default", Toast.LENGTH_SHORT).show();
            }
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioButtonSIS) {
                        employee.department = "SIS";
                    } else if (checkedId == R.id.radioButton2CS) {
                        employee.department = "CS";
                    } else {
                        employee.department = "Health Informatics";
                    }
                }
            });

            findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = details.getText().toString();
                    if ((text.equals("") || text.length() == 0 )&& employee.department.equals("")) {
                        setResult(RESULT_CANCELED);

                    } else {
                        String t=tv.getText().toString();
                        switch (t){
                            case "Name :":
                                employee.name=text;
                                break;
                            case "Age :":
                                employee.age=text;
                                break;
                            case "Phone :":
                                employee.phone=text;
                                break;
                            case "Email :":
                                employee.email=text;
                                break;
                        }
                        Intent i = new Intent();
                        i.putExtra(DisplayActivity.EMPLOYEE_keyy, employee);
                        setResult(RESULT_OK, i);
                    }
                    finish();
                }
            });
        }
    }
}
