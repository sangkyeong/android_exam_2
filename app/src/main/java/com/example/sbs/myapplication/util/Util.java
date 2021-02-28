package com.example.sbs.myapplication.util;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    private static Application application;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;

    public static void init(Application application) {
        Util.application = application;

        sp = PreferenceManager.getDefaultSharedPreferences(application);
        spEditor = sp.edit();
    }

    public static void toast(String msg) {
        Toast.makeText(application, msg, Toast.LENGTH_SHORT).show();
    }

    public static void spPut(String key, int value) {
        spEditor.putInt(key, value);
    }

    public static void spPut(String key, boolean value) {
        spEditor.putBoolean(key, value);
    }

    public static void spPut(String key, String value) {
        spEditor.putString(key, value);
    }

    public static void spPut(String key, Object obj) {
        spPut(key, objToJsonString(obj));
    }

    public static void spCommit() {
        spEditor.commit();
    }

    public static int spGetInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public static boolean spGetBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public static String spGetString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public static void log(String msg) {
        Log.d("AA1", msg);
    }

    public static String objToJsonString(Object obj) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    public static <T> T spGetObj(String key, Class<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T spGetObj(String key, TypeReference<T> cls) {
        String jsonString = spGetString(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T) om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static float dipToPixels(float borderRadius) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderRadius, metrics);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView) {
        loadImageOn(imgUrl, imageView, 0);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView, int borderRadius) {
        if (borderRadius > 0) {
            Glide.with(application)
                    .load(imgUrl)
                    .transform(new CenterCrop(), new RoundedCorners((int) dipToPixels(borderRadius)))
                    .into(imageView);
        } else {
            Glide.with(application)
                    .load(imgUrl)
                    .into(imageView);
        }

    }
}
