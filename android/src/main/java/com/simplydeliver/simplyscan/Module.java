package com.simplydeliver.simplyscan;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.content.Context;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;
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

public class Module extends ReactContextBaseJavaModule {

    private BroadcastReceiver receiver;
    private Promise scanPromise;
    Context context;
 
    String bundleid;
    String property;

    public Module(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.gears42.action.CUSTOM_PROPERTY");

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // String value = intent.getStringExtra("value");
                String key = intent.getStringExtra("key");
                String value = intent.getStringExtra("value");
                String data_type = intent.getStringExtra("data_type");
                if (scanPromise != null) {
                    scanPromise.resolve(value);
                    scanPromise = null;
                }
            }
        };
        reactContext.registerReceiver(receiver, filter);
    }

    @Override
    public String getName() {
        return "SimplyScanView";
    }

    @ReactMethod
    public void startScan(ReadableMap options, final Promise promise) {
        scanPromise = promise;

        bundleid = options.hasKey("bundleid") ? options.getString("bundleid") : "com.simplydeliver.transmissiondriver";
        property = options.hasKey("property") ? options.getString("property") : "macaddress";

        Intent lIntent = new Intent("com.gears42.action.REQUEST_CUSTOM_PROPERTIES");
        lIntent.putExtra("sender_package_name", bundleid);
        lIntent.putExtra("property_key", property);
        lIntent.setPackage("com.nix");
        context.sendBroadcast(lIntent, "gears42.permission.REQUEST_CUSTOM_PROPERTIES");
    }
}