package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.TbsLog;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class CookieManager {
   public static enum A1 {
      a,
      b,
      c;
   }

   public static String LOGTAG = "CookieManager";
   private static CookieManager d;
   CopyOnWriteArrayList<CookieManager.b> a;
   String b;
   CookieManager.A1 c;
   private boolean e;
   private boolean f;

   private CookieManager() {
      this.c = CookieManager.A1.a;
      this.e = false;
      this.f = false;
   }

   public static CookieManager getInstance() {
      if (null == d) {
         Class var0 = CookieManager.class;
         synchronized(CookieManager.class) {
            if (null == d) {
               d = new CookieManager();
            }
         }
      }

      return d;
   }

   /** @deprecated */
   @Deprecated
   public void removeSessionCookie() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         DexLoader var2 = var1.c().b();
         var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookie", new Class[0]);
      } else {
         android.webkit.CookieManager.getInstance().removeSessionCookie();
      }

   }

   public void removeSessionCookies(ValueCallback<Boolean> var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         DexLoader var3 = var2.c().b();
         var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, var1);
      } else {
         if (VERSION.SDK_INT < 21) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "removeSessionCookies", new Class[]{android.webkit.ValueCallback.class}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void removeAllCookie() {
      if (this.a != null) {
         this.a.clear();
      }

      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().e();
      } else {
         android.webkit.CookieManager.getInstance().removeAllCookie();
      }

   }

   public void removeAllCookies(ValueCallback<Boolean> var1) {
      if (this.a != null) {
         this.a.clear();
      }

      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         DexLoader var3 = var2.c().b();
         var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, var1);
      } else {
         if (VERSION.SDK_INT < 21) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "removeAllCookies", new Class[]{android.webkit.ValueCallback.class}, var1);
      }

   }

   public void flush() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         DexLoader var2 = var1.c().b();
         var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_flush", new Class[0]);
      } else {
         if (VERSION.SDK_INT < 21) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "flush", new Class[0]);
      }

   }

   /** @deprecated */
   @Deprecated
   public void removeExpiredCookie() {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         DexLoader var2 = var1.c().b();
         var2.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeExpiredCookie", new Class[0]);
      } else {
         android.webkit.CookieManager.getInstance().removeExpiredCookie();
      }

   }

   public synchronized void setAcceptCookie(boolean var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         DexLoader var3 = var2.c().b();
         var3.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptCookie", new Class[]{Boolean.TYPE}, var1);
      } else {
         try {
            android.webkit.CookieManager.getInstance().setAcceptCookie(var1);
         } catch (Throwable var4) {
            var4.printStackTrace();
         }
      }

   }

   public synchronized void setAcceptThirdPartyCookies(WebView var1, boolean var2) {
      X5CoreEngine var3 = X5CoreEngine.a();
      if (null != var3 && var3.b()) {
         DexLoader var4 = var3.c().b();
         var4.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setAcceptThirdPartyCookies", new Class[]{Object.class, Boolean.TYPE}, var1.getView(), var2);
      } else {
         if (VERSION.SDK_INT < 21) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "setAcceptThirdPartyCookies", new Class[]{android.webkit.WebView.class, Boolean.TYPE}, var1.getView(), var2);
      }

   }

   public synchronized boolean acceptThirdPartyCookies(WebView var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         DexLoader var5 = var2.c().b();
         Object var4 = var5.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptThirdPartyCookies", new Class[]{Object.class}, var1.getView());
         return var4 != null ? (Boolean)var4 : true;
      } else if (VERSION.SDK_INT < 21) {
         return true;
      } else {
         Object var3 = com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "acceptThirdPartyCookies", new Class[]{android.webkit.WebView.class}, var1.getView());
         return var3 != null ? (Boolean)var3 : false;
      }
   }

   public synchronized void setCookie(String var1, String var2) {
      this.setCookie(var1, var2, false);
   }

   public synchronized void setCookie(String var1, String var2, boolean var3) {
      X5CoreEngine var4 = X5CoreEngine.a();
      if (null != var4 && var4.b()) {
         DexLoader var6 = var4.c().b();
         var6.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class}, var1, var2);
      } else {
         if (this.f || var3) {
            android.webkit.CookieManager.getInstance().setCookie(var1, var2);
         }

         if (!X5CoreEngine.a().d()) {
            CookieManager.b var5 = new CookieManager.b();
            var5.a = 2;
            var5.b = var1;
            var5.c = var2;
            var5.d = null;
            if (this.a == null) {
               this.a = new CopyOnWriteArrayList();
            }

            this.a.add(var5);
         }
      }

   }

   public synchronized void setCookie(String var1, String var2, ValueCallback<Boolean> var3) {
      X5CoreEngine var4 = X5CoreEngine.a();
      if (null != var4 && var4.b()) {
         DexLoader var6 = var4.c().b();
         var6.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, var1, var2, var3);
      } else {
         if (!X5CoreEngine.a().d()) {
            CookieManager.b var5 = new CookieManager.b();
            var5.a = 1;
            var5.b = var1;
            var5.c = var2;
            var5.d = var3;
            if (this.a == null) {
               this.a = new CopyOnWriteArrayList();
            }

            this.a.add(var5);
         }

         if (this.f) {
            if (VERSION.SDK_INT < 21) {
               return;
            }

            com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, var1, var2, var3);
         }
      }

   }

   public boolean hasCookies() {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().h() : android.webkit.CookieManager.getInstance().hasCookies();
   }

   public boolean acceptCookie() {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().d() : android.webkit.CookieManager.getInstance().acceptCookie();
   }

   public String getCookie(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         return var2.c().a(var1);
      } else {
         String var3 = null;

         try {
            var3 = android.webkit.CookieManager.getInstance().getCookie(var1);
         } catch (Throwable var5) {
            var5.printStackTrace();
         }

         return var3;
      }
   }

   public void setCookies(Map<String, String[]> var1) {
      boolean var2 = false;
      X5CoreEngine var3 = X5CoreEngine.a();
      if (null != var3 && var3.b()) {
         var2 = var3.c().a(var1);
      }

      if (!var2) {
         Iterator var4 = var1.keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            String[] var6 = (String[])var1.get(var5);
            int var7 = var6.length;

            for(int var8 = 0; var8 < var7; ++var8) {
               String var9 = var6[var8];
               this.setCookie(var5, var9);
            }
         }
      }

   }

   synchronized void a() {
      this.f = true;
      if (this.a != null && this.a.size() != 0) {
         X5CoreEngine var1 = X5CoreEngine.a();
         Iterator var2;
         CookieManager.b var3;
         if (null != var1 && var1.b()) {
            var2 = this.a.iterator();

            while(var2.hasNext()) {
               var3 = (CookieManager.b)var2.next();
               switch(var3.a) {
               case 1:
                  this.setCookie(var3.b, var3.c, var3.d);
                  break;
               case 2:
                  this.setCookie(var3.b, var3.c);
               }
            }
         } else {
            var2 = this.a.iterator();

            while(var2.hasNext()) {
               var3 = (CookieManager.b)var2.next();
               switch(var3.a) {
               case 1:
                  if (VERSION.SDK_INT >= 21) {
                     com.tencent.smtt.utils.i.a((Object)android.webkit.CookieManager.getInstance(), "setCookie", new Class[]{String.class, String.class, android.webkit.ValueCallback.class}, var3.b, var3.c, var3.d);
                  }
                  break;
               case 2:
                  android.webkit.CookieManager.getInstance().setCookie(var3.b, var3.c);
               }
            }
         }

         this.a.clear();
      }
   }

   public boolean setCookieCompatialbeMode(Context var1, CookieManager.A1 var2, String var3, boolean var4) {
      long var5 = System.currentTimeMillis();
      if (var1 != null && TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "cookie_switch.txt")) {
         this.c = var2;
         if (var3 != null) {
            this.b = var3;
         }

         if (this.c != CookieManager.A1.a && var4 && !X5CoreEngine.a().d()) {
            X5CoreEngine.a().a(var1);
         }

         return true;
      } else {
         return false;
      }
   }

   protected synchronized void a(Context var1, boolean var2, boolean var3) {
      if (this.c != CookieManager.A1.a && var1 != null && TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "cookie_switch.txt") && !this.e) {
         long var4 = System.currentTimeMillis();
         long var6 = 0L;
         TbsLog.i(LOGTAG, "compatiableCookieDatabaseIfNeed,isX5Inited:" + var2 + ",useX5:" + var3);
         if (!var2 && !QbSdk.getIsSysWebViewForcedByOuter() && !QbSdk.a) {
            X5CoreEngine var16 = X5CoreEngine.a();
            var16.a(var1);
         } else {
            if (QbSdk.getIsSysWebViewForcedByOuter() || QbSdk.a) {
               var3 = false;
            }

            boolean var8 = TbsExtensionFunctionManager.getInstance().canUseFunction(var1, "usex5.txt");
            TbsLog.i(LOGTAG, "usex5 : mUseX5LastProcess->" + var8 + ",useX5:" + var3);
            TbsExtensionFunctionManager.getInstance().setFunctionEnable(var1, "usex5.txt", var3);
            if (var8 != var3) {
               int var9 = 0;
               int var10 = 0;
               boolean var11 = false;
               TbsLogReport.TbsLogInfo var12 = TbsLogReport.getInstance(var1).tbsLogInfo();
               if (!TextUtils.isEmpty(this.b)) {
                  if (o.a().j(var1) > 0 && o.a().j(var1) < 36001) {
                     return;
                  }

                  if (var8) {
                     var9 = Cookies.d(var1);
                     if (var9 > 0) {
                        var10 = getROMCookieDBVersion(var1);
                        if (var10 <= 0) {
                           var11 = true;
                        }
                     }
                  } else {
                     var9 = Cookies.d(var1);
                     if (var9 > 0) {
                        String var13 = o.a().d(var1, "cookies_database_version");
                        if (!TextUtils.isEmpty(var13)) {
                           try {
                              var10 = Integer.parseInt(var13);
                           } catch (Exception var15) {
                           }
                        }
                     }
                  }

                  if (!var11 && (var9 <= 0 || var10 <= 0)) {
                     var12.setErrorCode(702);
                  } else if (var10 >= var9) {
                     var12.setErrorCode(703);
                  } else {
                     Cookies.a(var1, this.c, this.b, var11, var3);
                     var12.setErrorCode(704);
                     var6 = System.currentTimeMillis() - var4;
                  }
               } else {
                  var12.setErrorCode(701);
               }

               var12.setFailDetail("x5->sys:" + var8 + " from:" + var9 + " to:" + var10 + ",timeused:" + var6);
               TbsLogReport.getInstance(var1).eventReport(TbsLogReport.EventType.TYPE_COOKIE_DB_SWITCH, var12);
            }
         }
      }
   }

   public static int getROMCookieDBVersion(Context var0) {
      SharedPreferences var1;
      if (VERSION.SDK_INT >= 11) {
         var1 = var0.getSharedPreferences("cookiedb_info", 4);
      } else {
         var1 = var0.getSharedPreferences("cookiedb_info", 0);
      }

      return var1.getInt("db_version", -1);
   }

   public static void setROMCookieDBVersion(Context var0, int var1) {
      SharedPreferences var2 = null;
      Editor var3 = null;
      if (VERSION.SDK_INT >= 11) {
         var2 = var0.getSharedPreferences("cookiedb_info", 4);
      } else {
         var2 = var0.getSharedPreferences("cookiedb_info", 0);
      }

      var3 = var2.edit();
      var3.putInt("db_version", var1);
      var3.commit();
   }

   class b {
      int a;
      String b;
      String c;
      ValueCallback<Boolean> d;
   }


}
