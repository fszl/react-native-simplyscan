package com.simplydeliver.simplyscan;

import android.widget.Toast;
import android.graphics.Bitmap

import net.kuama.documentscanner.exceptions.NullCorners;
import net.kuama.documentscanner.presentation.BaseScannerActivity;

import com.facebook.react.bridge.Promise;

var currentPromise: Promise? = null

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