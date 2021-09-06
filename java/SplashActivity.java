package com.example.window_close;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//4초 동안 지정해놓은 png파일이 켜지면서 MainActivity로 넘어간다.
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
