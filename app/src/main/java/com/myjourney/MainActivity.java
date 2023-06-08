package com.myjourney;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myjourney.utils.viewinjection.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView
    private Button btnWrite;
    @InjectView
    private Button btnRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        
        btnWrite.setOnClickListener(this::onWriteButtonClick);
        btnRead.setOnClickListener(this::onReadButtonClick);
    }
    
    private void onWriteButtonClick(View v) {
        startActivity(new Intent(this, WriteActivity.class));
    }
    
    private void onReadButtonClick(View v) {
        startActivity(new Intent(this, ReadActivity.class));
    }
}