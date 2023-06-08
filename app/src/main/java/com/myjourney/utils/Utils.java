package com.myjourney.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import androidx.annotation.IdRes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

    @SuppressLint("DiscouragedApi")
    public static <T extends View> T findViewById(Activity activity, String strId) {
        @IdRes int id = activity.getResources().getIdentifier(strId, "id", activity.getPackageName());
        return activity.findViewById(id);
    }

    public static String now() {
        return new SimpleDateFormat("dd/MM/yy", Locale.getDefault())
                .format(Calendar.getInstance().getTime());
    }

    public static boolean isBlank(String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
