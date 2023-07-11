package com.simplydeliver.simplyscan;

import android.view.View;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ViewManager;

public class Package : ReactPackage {

    override fun createViewManagers(reactContext: ReactApplicationContext): 
      MutableList<ViewManager<out View, out ReactShadowNode<*>>> {
        return mutableListOf(
          SimplyScanView()
        )
    }

    override fun createNativeModules(reactContext: ReactApplicationContext): 
      MutableList<NativeModule> {
        return mutableListOf()
    }
}
