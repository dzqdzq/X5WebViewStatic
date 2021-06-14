package com.tencent.smtt.sdk;

import java.util.Map;

public class WebStorage {
   private static WebStorage a;

   public void getOrigins(ValueCallback<Map> var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         var2.c().a((android.webkit.ValueCallback)var1);
      } else {
         android.webkit.WebStorage.getInstance().getOrigins(var1);
      }

   }

   public void getUsageForOrigin(String var1, ValueCallback<Long> var2) {
      X5CoreEngine var3 = X5CoreEngine.a();
      if (null != var3 && var3.b()) {
         var3.c().a((String)var1, (android.webkit.ValueCallback)var2);
      } else {
         android.webkit.WebStorage.getInstance().getUsageForOrigin(var1, var2);
      }

   }

   public void getQuotaForOrigin(String var1, ValueCallback<Long> var2) {
      X5CoreEngine var3 = X5CoreEngine.a();
      if (null != var3 && var3.b()) {
         var3.c().b(var1, var2);
      } else {
         android.webkit.WebStorage.getInstance().getQuotaForOrigin(var1, var2);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setQuotaForOrigin(String var1, long var2) {
      X5CoreEngine var4 = X5CoreEngine.a();
      if (null != var4 && var4.b()) {
         var4.c().a(var1, var2);
      } else {
         android.webkit.WebStorage.getInstance().setQuotaForOrigin(var1, var2);
      }

   }

   public void deleteOrigin(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         var2.c().e(var1);
      } else {
         android.webkit.WebStorage.getInstance().deleteOrigin(var1);
      }

   }

   public void deleteAllData() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().n();
      } else {
         android.webkit.WebStorage.getInstance().deleteAllData();
      }

   }

   public static WebStorage getInstance() {
      return a();
   }

   private static synchronized WebStorage a() {
      if (a == null) {
         a = new WebStorage();
      }

      return a;
   }

   /** @deprecated */
   @Deprecated
   public interface QuotaUpdater {
      void updateQuota(long var1);
   }
}
