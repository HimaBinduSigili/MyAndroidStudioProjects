package com.example.bindu.inclass4;
/**Assignment 4
 * File name: InClass4
 * Group15 members: Hima Bindu Sigili
 *                  Bryson Shannon
 * Created by bindu on 2/5/2018.
 */

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       findViewById(R.id.textViewwelcome).postDelayed(new Runnable() {
           @Override
           public void run() {
               Intent intent = new Intent(MainActivity.this
               , SecondActivity.class);
               startActivity(intent);
           }
       },3000);
    }
}
