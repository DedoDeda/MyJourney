package com.myjourney;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myjourney.utils.Utils;
import com.myjourney.utils.viewinjection.InjectView;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class WriteActivity extends AppCompatActivity {

    private static final String TAG = WriteActivity.class.getName();

    @InjectView
    private Button btnPickDate;
    @InjectView
    private TextView txtDate;
    @InjectView
    private EditText edtWrite;
    @InjectView
    private Button btnSave;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        btnPickDate.setOnClickListener(this::onDatePick);
        txtDate.setText(Utils.now());
        btnSave.setOnClickListener(this::onSaveButtonClick);
    }

    private void onDatePick(View v) {
        datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", this::onDatePickerOkClick);
    }

    private void onDatePickerOkClick(DialogInterface dialogInterface, int whichButton) {
        txtDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%02d",
                datePickerDialog.getDatePicker().getDayOfMonth(),
                datePickerDialog.getDatePicker().getMonth(),
                datePickerDialog.getDatePicker().getYear()));
    }


    private void onSaveButtonClick(View v) {
        String userText = edtWrite.getText().toString();
        if (Utils.isBlank(userText)) {
            return;
        }
        String text = txtDate.getText().toString() + '\n' + userText + "\n\n";

        try {
            FileOutputStream fos = openFileOutput(Const.DIARY_FILENAME, Context.MODE_APPEND);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }

        edtWrite.getText().clear();
        txtDate.setText(Utils.now());
        finish();
    }
}