package com.github.gzuliyujiang.logger.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gzuliyujiang.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.ENABLE = BuildConfig.DEBUG;
        Logger.print("测试");
        Logger.print(savedInstanceState);
        Logger.print(new RuntimeException("测试"));
    }
}
