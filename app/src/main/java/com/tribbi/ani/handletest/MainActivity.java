package com.tribbi.ani.handletest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private  final String TAG =  "Tribbi";
    private  Handler handler;
    private  Button bt1,bt2,bt3;
    private  ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCom();
    }

    private void initCom(){

        handler =  new Handler();

        bt1 = (Button)findViewById(R.id.bt1);
        bt2 = (Button)findViewById(R.id.bt2);
        bt3 = (Button)findViewById(R.id.bt3);
        bar = (ProgressBar) findViewById(R.id.bar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Mesg1"+bt1);
                handler.post(r);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Mesg2"+bt2);
                handler.removeCallbacks(r);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d(TAG,"Button3");
                mhandler.post(runnable);

            }
        });
    }


    Handler mhandler =  new Handler(){
        @Override
        public void handleMessage(android.os.Message msg){
            bar.setProgress(msg.arg1);
            mhandler.removeCallbacks(runnable);
            Log.d("TAG","--mesg"+msg.arg1);

        };
    };

    //这个叫匿名内部类
    Runnable runnable = new Runnable() {
        int i;
        @Override
        public void run() {
            i = i+10;
            Message msg = mhandler.obtainMessage();
            msg.arg1 = i;
            Log.d("TAG","--mesg1111");
            mhandler.sendMessage(msg);
            Log.d("TAG","--mesg2222");
            try{

                Thread.sleep(1000);
            }catch (InterruptedException e){

                e.printStackTrace();
            }

            if(i >= 100){

                mhandler.removeCallbacks(runnable);
            }
        }
    };

    Runnable r = new Runnable() {
        @Override
        public void run() {

            handler.postDelayed(r,1000);
        }
    };

}
