package com.myjourney;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myjourney.utils.viewinjection.InjectView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadActivity extends AppCompatActivity {

    private static final String TAG = ReadActivity.class.getName();

    @InjectView
    private TextView txtRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        try {
            InputStream is = this.openFileInput(Const.DIARY_FILENAME);
            if (is == null) {
                return;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append('\n').append(line);
            }
            is.close();

            txtRead.setText(builder.toString());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}