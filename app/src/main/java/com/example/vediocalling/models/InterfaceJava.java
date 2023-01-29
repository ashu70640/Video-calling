package com.example.vediocalling.models;

import android.webkit.JavascriptInterface;

import com.example.vediocalling.activities.callActivity;

public class InterfaceJava {
     callActivity callActivity;
     public InterfaceJava(callActivity callActivity){
         this.callActivity=callActivity;
     }
     @JavascriptInterface
     public void onPeerConnected(){
          callActivity.onPeerConnected();
     }
}
