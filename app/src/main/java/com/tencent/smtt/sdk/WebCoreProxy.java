package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5CoreServiceWorkerController;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.IconListener;
import com.tencent.smtt.utils.TbsLog;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

class WebCoreProxy {
   private DexLoader a;

   public WebCoreProxy(DexLoader var1) {
      this.a = var1;
   }

   public boolean a() throws Throwable {
      Object var1 = null;

      try {
         Method var2 = this.a.getClassLoader().loadClass("com.tencent.tbs.tbsshell.WebCoreProxy").getMethod("canUseX5");
         var2.setAccessible(true);
         var1 = var2.invoke((Object)null);
         if (var1 instanceof Boolean) {
            return (Boolean)var1;
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return (Boolean)var1;
   }

   public DexLoader b() {
      return this.a;
   }

   public Object c() {
      return this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cacheDisabled", new Class[0]);
   }

   public boolean d() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_acceptCookie", new Class[0]);
      return null == var1 ? false : (Boolean)var1;
   }

   public void e() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_removeAllCookie", new Class[0]);
   }

   public boolean a(Map<String, String[]> var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_setCookies", new Class[]{Map.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public void a(boolean var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webview_setWebContentsDebuggingEnabled", new Class[]{Boolean.TYPE}, var1);
   }

   public IX5WebViewBase a(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createSDKWebview", new Class[]{Context.class}, var1);
      IX5WebViewBase var3 = null;

      try {
         if (null == var2) {
            Object var4 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0]);
            if (var4 != null && var4 instanceof Throwable) {
               TbsCoreLoadStat.getInstance().a(var1, 325, (Throwable)var4);
            }

            if (var4 != null && var4 instanceof String) {
               TbsCoreLoadStat.getInstance().a(var1, 325, new Throwable((String)var4));
            }

            var2 = null;
         } else {
            var3 = (IX5WebViewBase)var2;
            if (var3 != null && var3.getView() == null) {
               TbsCoreLoadStat.getInstance().a(var1, 325, new Throwable("x5webview.getView is null!"));
               var2 = null;
            }
         }
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return null == var2 ? null : var3;
   }

   public String a(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCookie", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public String f() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getMiniQBVersion", new Class[0]);
      return null == var1 ? null : (String)var1;
   }

   public InputStream a(String var1, boolean var2) {
      Object var3 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCacheFile", new Class[]{String.class, Boolean.TYPE}, var1, var2);
      return null == var3 ? null : (InputStream)var3;
   }

   public Object g() {
      return this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getCachFileBaseDir", new Class[0]);
   }

   public boolean h() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "cookieManager_hasCookies", new Class[0]);
      return null == var1 ? false : (Boolean)var1;
   }

   public IX5WebChromeClient i() {
      if (this.a == null) {
         return null;
      } else {
         Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClient", new Class[0]);
         return null == var1 ? null : (IX5WebChromeClient)var1;
      }
   }

   public IX5WebViewClient j() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebViewClient", new Class[0]);
      return null == var1 ? null : (IX5WebViewClient)var1;
   }

   public IX5WebViewClientExtension k() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDefaultX5WebChromeClientExtension", new Class[0]);
      return null == var1 ? null : (IX5WebViewClientExtension)var1;
   }

   public void b(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "openIconDB", new Class[]{String.class}, var1);
   }

   public Uri[] a(int var1, Intent var2) {
      Object var3 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "parseFileChooserResult", new Class[]{Integer.TYPE, Intent.class}, var1, var2);
      return null == var3 ? null : (Uri[])((Uri[])var3);
   }

   public void l() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", (Class[])null);
   }

   public void a(String var1, IconListener var2) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "requestIconForPageUrl", new Class[]{String.class, IconListener.class}, var1, var2);
   }

   public void c(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "retainIconForPageUrl", new Class[]{String.class}, var1);
   }

   public void d(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "releaseIconForPageUrl", new Class[]{String.class}, var1);
   }

   public void m() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeIconDB", (Class[])null);
   }

   public boolean b(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasUsernamePassword", new Class[]{Context.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public void c(Context var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, var1);
   }

   public boolean d(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasHttpAuthUsernamePassword", new Class[]{Context.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public void e(Context var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, var1);
   }

   public boolean f(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseHasFormData", new Class[]{Context.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public void g(Context var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, var1);
   }

   public void a(android.webkit.ValueCallback<Map> var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetOrigins", new Class[]{android.webkit.ValueCallback.class}, var1);
   }

   public void a(String var1, android.webkit.ValueCallback<Long> var2) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetUsageForOrigin", new Class[]{String.class, android.webkit.ValueCallback.class}, var1, var2);
   }

   public void b(String var1, android.webkit.ValueCallback<Long> var2) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageGetQuotaForOrigin", new Class[]{String.class, android.webkit.ValueCallback.class}, var1, var2);
   }

   public void a(String var1, long var2) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageSetQuotaForOrigin", new Class[]{String.class, Long.TYPE}, var1, var2);
   }

   public void e(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteOrigin", new Class[]{String.class}, var1);
   }

   public void n() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webStorageDeleteAllData", (Class[])null);
   }

   public IX5DateSorter h(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "createDateSorter", new Class[]{Context.class}, var1);
      return null == var2 ? null : (IX5DateSorter)var2;
   }

   public void b(android.webkit.ValueCallback<Set<String>> var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsGetOrigins", new Class[]{android.webkit.ValueCallback.class}, var1);
   }

   public void c(String var1, android.webkit.ValueCallback<Boolean> var2) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsGetAllowed", new Class[]{String.class, android.webkit.ValueCallback.class}, var1, var2);
   }

   public void f(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsClear", new Class[]{String.class}, var1);
   }

   public void g(String var1) {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsAllow", new Class[]{String.class}, var1);
   }

   public void o() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "geolocationPermissionsClearAll", (Class[])null);
   }

   public String h(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetFileExtensionFromUrl", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public boolean i(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapHasMimeType", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public String j(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetMimeTypeFromExtension", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public boolean k(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapHasExtension", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public String l(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "mimeTypeMapGetMimeTypeFromExtension", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public String m(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilGuessUrl", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public String a(String var1, String var2, String var3) {
      Object var4 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilComposeSearchUrl", new Class[]{String.class, String.class, String.class}, var1, var2, var3);
      return null == var4 ? null : (String)var4;
   }

   public byte[] a(byte[] var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilDecode", new Class[]{String.class}, var1);
      return null == var2 ? null : (byte[])((byte[])var2);
   }

   public boolean n(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsAssetUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean o(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsCookielessProxyUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean p(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsFileUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean q(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsAboutUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean r(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsDataUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean s(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsJavaScriptUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean t(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsHttpUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean u(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsHttpsUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean v(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsNetworkUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean w(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsContentUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public boolean x(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilIsValidUrl", new Class[]{String.class}, var1);
      return null == var2 ? false : (Boolean)var2;
   }

   public String y(String var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilStripAnchor", new Class[]{String.class}, var1);
      return null == var2 ? null : (String)var2;
   }

   public String b(String var1, String var2, String var3) {
      Object var4 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "urlUtilGuessFileName", new Class[]{String.class, String.class, String.class}, var1, var2, var3);
      return null == var4 ? null : (String)var4;
   }

   public void a(Context var1, boolean var2) {
      TbsLog.w("desktop", " tbsWizard clearAllX5Cache");
      if (var2) {
         this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class}, var1);
      } else {
         try {
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "clearAllCache", new Class[]{Context.class, Boolean.TYPE}, var1, var2);
         } catch (Exception var6) {
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearUsernamePassword", new Class[]{Context.class}, var1);
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearHttpAuthUsernamePassword", new Class[]{Context.class}, var1);
            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "webViewDatabaseClearFormData", new Class[]{Context.class}, var1);
            this.a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "removeAllCacheFiles", (Class[])null);
            this.a.invokeStaticMethod("com.tencent.smtt.webkit.CacheManager", "clearLocalStorage", (Class[])null);
            Object var4 = this.a.invokeStaticMethod("com.tencent.smtt.net.http.DnsManager", "getInstance", (Class[])null);
            if (var4 != null) {
               this.a.invokeMethod(var4, "com.tencent.smtt.net.http.DnsManager", "removeAllDns", (Class[])null);
            }

            Object var5 = this.a.invokeStaticMethod("com.tencent.smtt.webkit.SmttPermanentPermissions", "getInstance", (Class[])null);
            if (var5 != null) {
               this.a.invokeMethod(var5, "com.tencent.smtt.webkit.SmttPermanentPermissions", "clearAllPermanentPermission", (Class[])null);
            }

            this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "removeAllIcons", (Class[])null);
         }
      }

   }

   public int a(Context var1, String var2, Map<String, String> var3, String var4, android.webkit.ValueCallback<String> var5) {
      if (TbsDownloader.getOverSea(var1)) {
         return -103;
      } else {
         Object var6;
         if (var4 == null) {
            var6 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class, android.webkit.ValueCallback.class}, var1, var2, var3, var5);
            if (var6 == null) {
               var6 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, Map.class}, var1, var2, var3);
            }

            if (var6 == null) {
               var6 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class}, var1, var2);
            }

            return null == var6 ? -104 : (Integer)var6;
         } else {
            var6 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "startMiniQB", new Class[]{Context.class, String.class, String.class}, var1, var2, var4);
            return null == var6 ? -104 : (Integer)var6;
         }
      }
   }

   public boolean a(Context var1, String var2) {
      Object var3 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "canOpenFile", new Class[]{Context.class, String.class}, var1, var2);
      return var3 instanceof Boolean ? (Boolean)var3 : false;
   }

   public void p() {
      this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "closeFileReader", new Class[0]);
   }

   public String i(Context var1) {
      Object var2 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getDefaultUserAgent", new Class[]{Context.class}, var1);
      return var2 instanceof String ? (String)var2 : null;
   }

   public IX5CoreServiceWorkerController q() {
      Object var1 = this.a.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", "getServiceWorkerController", new Class[0]);
      return var1 instanceof IX5CoreServiceWorkerController ? (IX5CoreServiceWorkerController)var1 : null;
   }
}
