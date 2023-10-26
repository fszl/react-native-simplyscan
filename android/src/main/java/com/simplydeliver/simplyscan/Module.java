package com.simplydeliver.simplyscan;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.IntentFilter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.graphics.Bitmap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Promise;

import android.util.Log;

import java.io.File;
import java.lang.System;
import java.util.Date;
import java.util.Random;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;   
import java.util.Calendar;  
import java.io.ByteArrayOutputStream;
import android.util.Base64;
import java.lang.StringBuilder;

public class Module extends ReactContextBaseJavaModule implements ActivityEventListener {

    private BroadcastReceiver receiver;
    private Promise scanPromise;
 
    String bundleid;

    @Override
    public void onNewIntent(Intent intent) {
    }

    public Module(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        reactContext.addActivityEventListener(mActivityEventListener);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gears42.action.CUSTOM_PROPERTY");

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String key = intent.getString("key");
                String value = intent.getString("value");
                String data_type = intent.getString("data_type");
                if (scanPromise != null) {
                    scanPromise.resolve(value);
                    scanPromise = null;
                }
            }
        };
        registerReceiver(receiver, filter);
    }

    @Override
    public String getName() {
        return "SimplyScanView";
    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
        unregisterReceiver(receiver);
            receiver = null;
        }
        super.onDestroy();
    }

    @ReactMethod
    public void startScan(ReadableMap options, final Promise promise) {
        scanPromise = promise;

        bundleid = options.hasKey("bundleid") ? options.getInt("bundleid") : "com.simplydeliver.transmissiondriver";

        Intent lIntent = new Intent("com.gears42.action.REQUEST_CUSTOM_PROPERTIES");
        lIntent.putExtra("sender_package_name", bundleid);
        lIntent.putExtra("property_key", "macaddress");
        lIntent.setPackage("com.nix");
        sendBroadcast(lIntent, "gears42.permission.REQUEST_CUSTOM_PROPERTIES");
    }
}