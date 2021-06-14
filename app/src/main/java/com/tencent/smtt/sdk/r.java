package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tbs.video.interfaces.IUserStateChangedListener;

class r {
   private static r e = null;
   t a = null;
   Context b;
   com.tencent.tbs.video.interfaces.a c;
   IUserStateChangedListener d;

   public static synchronized r a(Context var0) {
      if (e == null) {
         e = new r(var0);
      }

      return e;
   }

   private r(Context var1) {
      this.b = var1.getApplicationContext();
      this.a = new t(this.b);
   }

   public boolean a(String var1, Bundle var2, com.tencent.tbs.video.interfaces.a var3) {
      if (var2 == null) {
         var2 = new Bundle();
      }

      if (!TextUtils.isEmpty(var1)) {
         var2.putString("videoUrl", var1);
      }

      if (var3 != null) {
         this.a.a();
         if (!this.a.b()) {
            return false;
         }

         this.c = var3;
         this.d = new IUserStateChangedListener() {
            public void onUserStateChanged() {
               r.this.a.c();
            }
         };
         this.c.a(this.d);
         var2.putInt("callMode", 3);
      } else {
         var2.putInt("callMode", 1);
      }

      this.a.a(var2, var3 == null ? null : this);
      return true;
   }

   void a(Activity var1, int var2) {
      this.a.a(var1, var2);
   }

   public boolean a() {
      this.a.a();
      return this.a.b();
   }

   public void a(int var1, int var2, Intent var3) {
      if (this.c != null) {
         this.c.a(var1, var2, var3);
      }

   }
}
