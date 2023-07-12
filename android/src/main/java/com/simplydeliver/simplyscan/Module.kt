package com.simplydeliver.simplyscan;

import android.content.Intent
import android.widget.Toast;
import android.graphics.Bitmap
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import net.kuama.documentscanner.exceptions.NullCorners;
import net.kuama.documentscanner.presentation.BaseScannerActivity;



class SimplyScanView(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "SimplyScanView"
    }

    @ReactMethod
    fun startScan() {
        val intent = Intent(reactApplicationContext, ScanActivity::class.java)
        reactApplicationContext.startActivity(intent)
    }

}

class ScanActivity : BaseScannerActivity() {
  override fun onError(throwable: Throwable) {
      when (throwable) {
          is NullCorners -> Toast.makeText(
              this,
              net.kuama.documentscanner.R.string.null_corners, Toast.LENGTH_LONG
          )
              .show()
          else -> Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
      }
  }

  override fun onDocumentAccepted(bitmap: Bitmap) {
  }

  override fun onClose() {
      finish()
  }
}