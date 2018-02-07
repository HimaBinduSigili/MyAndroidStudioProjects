package com.example.bindu.inclass4;
/**Assignment 4
 * File name: InClass4
 * Group15 members: Hima Bindu Sigili
 *                  Bryson Shannon
 * Created by bindu on 2/5/2018.
 */

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecondActivity extends AppCompatActivity {
ExecutorService threadpool;
ProgressDialog progressDialog1,progressDialog2;
Handler handler;
SeekBar co,le;
TextView c,l ,pw;
int count = 0,length = 0;
Button thrd, Async;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        threadpool = Executors.newFixedThreadPool(2);
        co=(SeekBar)findViewById(R.id.seekBarcount);
        le =(SeekBar)findViewById(R.id.seekBarLength);
        c = (TextView)findViewById(R.id.textViewcountvalue);
        l =(TextView)findViewById(R.id.textViewlenvalue);
        thrd =(Button) findViewById(R.id.buttonThread);
        Async =(Button)findViewById(R.id.buttonAsync);
        pw =(TextView) findViewById(R.id.textViewpassword);
        final int minval =1, minval1 =8;
        co.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(progress<minval){
                        co.setProgress(minval);
                        count = minval;
                        c.setText(""+minval);
                    }else {
                        count = progress;
                        c.setText("" + progress);
                    }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        le.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(progress<minval1){
                        le.setProgress(minval1);
                        length = minval1;
                        l.setText(""+minval1);
                    }else {
                        length = progress;
                        l.setText("" + progress);
                    }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //progressDialog.incrementProgressBy(1);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case DoWorkThread.STATUS_Start:
                        progressDialog1 = new ProgressDialog(SecondActivity.this);
                        progressDialog1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog1.setMessage("Generating Passwords ...");
                        progressDialog1.setCancelable(false);
                        progressDialog1.setMax(count);
                        progressDialog1.show();
                        break;
                    case DoWorkThread.STATUS_Progress:
                        progressDialog1.setProgress((Integer) msg.obj);
                        break;
                    case DoWorkThread.STATUS_Stop:
                        final String[] password = msg.getData().getStringArray(DoWorkThread.RESULT);
                        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                        builder.setTitle("Passwords");
                        builder.setItems(password, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               pw.setText("Password:  "+password[which]);
                            }
                        });
                        progressDialog1.dismiss();
                        builder.create().show();
                        break;
                }
                return false;
            }
        });
        thrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threadpool.execute(new DoWorkThread());
            }
        });
        Async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DoWorkAsync().execute();
            }
        });


    }
   class DoWorkAsync extends AsyncTask<Void, Integer, String[]>{

        @Override
        protected String[] doInBackground(Void... voids) {
            String []passwords = new String[count];
            for (int i = 0; i <count ; i++) {
                passwords[i] = Util.getPassword(length);
                publishProgress(i+1);
            }
            return passwords;
        }


        @Override
        protected void onPreExecute() {
            progressDialog2 = new ProgressDialog(SecondActivity.this);
            progressDialog2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog2.setMessage("Generating Passwords ...");
            progressDialog2.setCancelable(false);
            progressDialog2.setMax(count);
            progressDialog2.show();
        }

        @Override
        protected void onPostExecute(final String... aVoid) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setTitle("Passwords");
            builder.setItems(aVoid, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pw.setText("Password:  "+aVoid[which]);
                }
            });
            progressDialog2.dismiss();
            builder.create().show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog2.setProgress(values[0]);

        }
    }

    class DoWorkThread implements Runnable{
        public static final int STATUS_Start = 0x00;
        public static final int STATUS_Stop = 0x01;
        public static final int STATUS_Progress = 0x02;
        public static final String RESULT ="RESULT";
        String []passwords = new String[count];
        @Override
        public void run() {
            Message msg = new Message();
            msg.what = STATUS_Start;
            handler.sendMessage(msg);
            for (int i = 0; i <count ; i++) {
                Message prg = new Message();
                prg.what = STATUS_Progress;
                prg.obj = (Integer)(i+1);
               passwords[i] = Util.getPassword(length);
               handler.sendMessage(prg);
            }
            Message stp = new Message();
            stp.what = STATUS_Stop;
            Bundle data = new Bundle();
            data.putStringArray(RESULT,passwords);
            stp.setData(data);
            handler.sendMessage(stp);
        }
    }
}
