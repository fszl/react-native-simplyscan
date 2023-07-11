package com.simplydeliver.simplyscan;

import android.widget.Toast;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;

import net.kuama.documentscanner.exceptions.NullCorners;
import net.kuama.documentscanner.presentation.BaseScannerActivity;

class SimplyScanView : BaseScannerActivity() {
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
      Log.d("TAG", "message")
  }

  override fun onClose() {
      finish()
  }
}