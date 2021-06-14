package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class VideoActivity extends Activity {
   protected void onCreate(Bundle var1) {
      super.onCreate(var1);
      super.requestWindowFeature(1);
      super.getWindow().setFormat(-3);
      Intent var2 = super.getIntent();
      Bundle var3 = var2 != null ? var2.getBundleExtra("extraData") : null;
      if (var3 != null) {
         var3.putInt("callMode", 1);
         r.a(super.getApplicationContext()).a((String)null, var3, (com.tencent.tbs.video.interfaces.a)null);
      }

   }

   protected void onResume() {
      super.onResume();
      r.a(this).a(this, 2);
   }

   protected void onStop() {
      super.onStop();
      r.a(this).a(this, 1);
   }

   protected void onPause() {
      super.onPause();
      r.a(this).a(this, 3);
   }

   protected void onDestroy() {
      super.onDestroy();
      r.a(this).a(this, 4);
   }

   protected void onActivityResult(int var1, int var2, Intent var3) {
      super.onActivityResult(var1, var2, var3);
      r.a(this).a(var1, var2, var3);
   }
}
