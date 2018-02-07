package com.example.bindu.todolist;
/**
 * Created by bindu on 2/1/2018.
 * homework Assignment 2
 * MainActivity.java
 * Hima Bindu Sigili
 * Group 15
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    TextView title, date, time, prior, display;
    ImageButton add, edit, del;
    ImageView nex, prev, firs, las;
    Tasks ta = new Tasks();
    int index=0;
    LinkedList<Tasks> tas = new LinkedList<>();;
    public static final int Req_Code = 100;
    public static final int Req_Code1 = 101;
    public static final String tasks = "tasks";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        setTitle("View Tasks");
        title = (TextView) findViewById(R.id.textViewtitle);
        date = (TextView) findViewById(R.id.textViewdate);
        time = (TextView) findViewById(R.id.textViewtime);
        prior = (TextView) findViewById(R.id.textViewpriority);
        display = (TextView) findViewById(R.id.textViewtasks);
        add = (ImageButton) findViewById(R.id.imageButtonadd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateActivity.class);
               // i.putParcelableArrayListExtra("GET_LIST",tas);
                startActivityForResult(i, Req_Code);
            }
        });
        edit =(ImageButton) findViewById(R.id.imageButtonedit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                i.putExtra(MainActivity.tasks, tas.get(index));
                startActivityForResult(i, Req_Code1);
            }
        });
        del =(ImageButton)findViewById(R.id.imageButtondel);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   try{
                       if(index>=0 && tas.size()>1) {
                           tas.remove(index);
                           Log.d("demo", "" + index);
                           index =0;
                           setTextsOftask(tas.get(index), index);
                       }else if(index == 0 && tas.size()==1){
                           tas.remove(index);
                          title.setText("Task Title");
                          date.setText("Task Date");
                          time.setText("Task Time");
                          prior.setText("Task Priority");
                          display.setText("Task 0 Of 0");
                       }
                   }catch (Exception e){
                       Log.d("demo", ""+e);
                   }


            }
        });
        nex =(ImageView)findViewById(R.id.imageViewnext);
        nex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index<tas.size()-1) {
                    index++;
                    setTextsOftask(tas.get(index),index);

                }else if(index<=tas.size()-1){
                    Toast.makeText(MainActivity.this,"Current task is the Last task",Toast.LENGTH_SHORT).show();
                }
            }
        });
        prev =(ImageView)findViewById(R.id.imageViewprev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index>0 ) {
                    index--;
                    setTextsOftask(tas.get(index),index);

                }else if(index==0){
                    Toast.makeText(MainActivity.this, "Curernt task is the First task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        firs =(ImageView)findViewById(R.id.imageViewfirst);
        firs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = 0;
                setTextsOftask(tas.get(0),0);
            }
        });
        las =(ImageView)findViewById(R.id.imageViewlast);
        las.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = tas.size()-1;
                setTextsOftask(tas.get(index),index);
            }
        });
    }


    public  void setTextsOftask(Tasks ta,int index) {
        title.setText(ta.getTitle());
        date.setText(ta.getDate());
        time.setText(ta.getTime());
        prior.setText(ta.getPriority()+" Priority");
        display.setText("Task "+(index+1)+" Of "+tas.size());
    }
/* */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Req_Code){
            if(resultCode==RESULT_OK){
                Tasks t = (Tasks) data.getExtras().get(MainActivity.tasks);
                tas.add(t);
                if(tas.size()>1)
                    Collections.sort(tas);
               index= tas.indexOf(t);

                setTextsOftask(tas.get(index),index);

            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(MainActivity.this,"Please enter all details",Toast.LENGTH_LONG).show();
            }

        }
        if(requestCode==Req_Code1){
            if(resultCode==RESULT_OK){
                Tasks t = (Tasks) data.getExtras().getParcelable(MainActivity.tasks);
                tas.set(index,t);
                if(tas.size()>1)
                    Collections.sort(tas);
                index = tas.indexOf(t);
                setTextsOftask(tas.get(index),index);
            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(MainActivity.this,"Please enter all details",Toast.LENGTH_LONG).show();
            }

        }
    }


}
