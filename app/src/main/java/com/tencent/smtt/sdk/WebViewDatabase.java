package com.tencent.smtt.sdk;

import android.content.Context;

public class WebViewDatabase {
   private static WebViewDatabase a;
   private Context b;

   protected WebViewDatabase(Context var1) {
      this.b = var1;
   }

   public static WebViewDatabase getInstance(Context var0) {
      return a(var0);
   }

   private static synchronized WebViewDatabase a(Context var0) {
      if (a == null) {
         a = new WebViewDatabase(var0);
      }

      return a;
   }

   /** @deprecated */
   @Deprecated
   public boolean hasUsernamePassword() {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().b(this.b) : android.webkit.WebViewDatabase.getInstance(this.b).hasUsernamePassword();
   }

   /** @deprecated */
   @Deprecated
   public void clearUsernamePassword() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().c(this.b);
      } else {
         android.webkit.WebViewDatabase.getInstance(this.b).clearUsernamePassword();
      }

   }

   public boolean hasHttpAuthUsernamePassword() {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().d(this.b) : android.webkit.WebViewDatabase.getInstance(this.b).hasHttpAuthUsernamePassword();
   }

   public void clearHttpAuthUsernamePassword() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().e(this.b);
      } else {
         android.webkit.WebViewDatabase.getInstance(this.b).clearHttpAuthUsernamePassword();
      }

   }

   public boolean hasFormData() {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().f(this.b) : android.webkit.WebViewDatabase.getInstance(this.b).hasFormData();
   }

   public void clearFormData() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().g(this.b);
      } else {
         android.webkit.WebViewDatabase.getInstance(this.b).clearFormData();
      }

   }
}
