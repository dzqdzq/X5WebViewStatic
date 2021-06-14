package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.net.http.SslCertificate;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnLongClickListener;
import android.webkit.WebView.FindListener;
import android.widget.FrameLayout;

import com.tencent.smtt.export.external.extension.interfaces.IX5WebChromeClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebSettingsExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewClientExtension;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.export.external.extension.proxy.X5ProxyWebViewClientExtension;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.sdk.stat.MttLoader;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;
import org.json.JSONObject;

public class WebView extends FrameLayout implements OnLongClickListener {
   private final String b;
   public static final String SCHEME_TEL = "tel:";
   public static final String SCHEME_MAILTO = "mailto:";
   public static final String SCHEME_GEO = "geo:0,0?q=";
   private static final Lock c = new ReentrantLock();
   private static OutputStream d = null;
   public static final int GETPVERROR = -1;
   private boolean e;
   private IX5WebViewBase f;
   private WebView.a g;
   private WebSettings h;
   private Context i;
   private static Context j = null;
   volatile int a;
   private volatile boolean k;
   public WebViewCallbackClient mWebViewCallbackClient;
   public static boolean mWebViewCreated = false;
   private static com.tencent.smtt.utils.n l = null;
   private static Method m = null;
   private WebViewClient n;
   private WebChromeClient o;
   private static String p = null;
   private final int q;
   private final int r;
   private final int s;
   public static boolean mSysWebviewCreated = false;
   private final String t;
   private final String u;
   private static Paint v = null;
   private static boolean w = true;
   public static int NIGHT_MODE_ALPHA = 153;
   public static final int NORMAL_MODE_ALPHA = 255;
   public static final int NIGHT_MODE_COLOR = -16777216;
   private Object x;
   private OnLongClickListener y;

   public WebView(Context var1, boolean var2) {
      super(var1);
      this.b = "WebView";
      this.e = false;
      this.h = null;
      this.i = null;
      this.a = 0;
      this.k = false;
      this.n = null;
      this.o = null;
      this.q = 1;
      this.r = 2;
      this.s = 3;
      this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
      this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
      this.x = null;
      this.y = null;
   }

   public WebView(Context var1) {
      this(var1, (AttributeSet)null);
   }

   public WebView(Context var1, AttributeSet var2) {
      this(var1, var2, 0);
   }

   public WebView(Context var1, AttributeSet var2, int var3) {
      this(var1, var2, var3, false);
   }

   /** @deprecated */
   @Deprecated
   public WebView(Context var1, AttributeSet var2, int var3, boolean var4) {
      this(var1, var2, var3, (Map)null, var4);
   }

   @TargetApi(11)
   public WebView(Context var1, AttributeSet var2, int var3, Map<String, Object> var4, boolean var5) {
      super(var1, var2, var3);
      this.b = "WebView";
      this.e = false;
      this.h = null;
      this.i = null;
      this.a = 0;
      this.k = false;
      this.n = null;
      this.o = null;
      this.q = 1;
      this.r = 2;
      this.s = 3;
      this.t = "javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));";
      this.u = "javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);";
      this.x = null;
      this.y = null;
      mWebViewCreated = true;
      if (QbSdk.getIsSysWebViewForcedByOuter() && TbsShareManager.isThirdPartyApp(var1)) {
         this.i = var1;
         this.f = null;
         this.e = false;
         QbSdk.a(var1, "failed to createTBSWebview!");
         this.g = new WebView.a(var1, var2);
         CookieManager.getInstance().a(var1, true, false);
         CookieSyncManager.createInstance(this.i).startSync();

         try {
            Class var13 = Class.forName("android.webkit.WebViewWorker");
            Method var7 = var13.getDeclaredMethod("getHandler");
            var7.setAccessible(true);
            Handler var8 = (Handler)var7.invoke((Object)null);
            Thread var9 = var8.getLooper().getThread();
            var9.setUncaughtExceptionHandler(new UncaughtSqliteExceptionHandler());
            mSysWebviewCreated = true;
         } catch (Exception var10) {
         }

         CookieManager.getInstance().a();
         this.g.setFocusableInTouchMode(true);
         this.addView(this.g, new LayoutParams(-1, -1));
         TbsLog.i("WebView", "SystemWebView Created Success! #3");
         TbsLog.e("WebView", "sys WebView: IsSysWebViewForcedByOuter = true", true);
         TbsCoreLoadStat.getInstance().a(var1, 402, new Throwable());
      } else {
         if (TbsShareManager.isThirdPartyApp(var1)) {
            TbsLog.setWriteLogJIT(true);
         } else {
            TbsLog.setWriteLogJIT(false);
         }

         TbsLog.initIfNeed(var1);
         if (var1 == null) {
            throw new IllegalArgumentException("Invalid context argument");
         } else {
            if (l == null) {
               l = com.tencent.smtt.utils.n.a(var1);
            }

            if (l.a) {
               TbsLog.e("WebView", "sys WebView: debug.conf force syswebview", true);
               QbSdk.a(var1, "debug.conf force syswebview!");
            }

            this.c(var1);
            this.i = var1;
            if (var1 != null) {
               j = var1.getApplicationContext();
            }

            if (this.e && !QbSdk.a) {
               this.f = X5CoreEngine.a().a(true).a(var1);
               if (this.f == null || this.f.getView() == null) {
                  TbsLog.e("WebView", "sys WebView: failed to createTBSWebview", true);
                  this.f = null;
                  this.e = false;
                  QbSdk.a(var1, "failed to createTBSWebview!");
                  this.c(var1);
                  if (TbsShareManager.isThirdPartyApp(this.i)) {
                     this.g = new WebView.a(var1, var2);
                  } else {
                     this.g = new WebView.a(var1);
                  }

                  TbsLog.i("WebView", "SystemWebView Created Success! #1");
                  CookieManager.getInstance().a(var1, true, false);
                  CookieManager.getInstance().a();
                  this.g.setFocusableInTouchMode(true);
                  this.addView(this.g, new LayoutParams(-1, -1));

                  try {
                     if (VERSION.SDK_INT >= 11) {
                        this.removeJavascriptInterface("searchBoxJavaBridge_");
                        this.removeJavascriptInterface("accessibility");
                        this.removeJavascriptInterface("accessibilityTraversal");
                     }
                  } catch (Throwable var11) {
                     var11.printStackTrace();
                  }

                  TbsLog.writeLogToDisk();
                  com.tencent.smtt.sdk.o.a(var1);
                  return;
               }

               TbsLog.i("WebView", "X5 WebView Created Success!!");
               this.f.getView().setFocusableInTouchMode(true);
               this.a(var2);
               this.addView(this.f.getView(), new LayoutParams(-1, -1));
               this.f.setDownloadListener(new QQBrowserDownloadListener(this, (DownloadListener)null, this.e));
               this.f.getX5WebViewExtension().setWebViewClientExtension(new X5ProxyWebViewClientExtension(X5CoreEngine.a().a(true).k()) {
                  public void invalidate() {
                  }

                  public void onScrollChanged(int var1, int var2, int var3, int var4) {
                     super.onScrollChanged(var1, var2, var3, var4);
                     WebView.this.onScrollChanged(var3, var4, var1, var2);
                  }
               });
            } else {
               this.f = null;
               if (TbsShareManager.isThirdPartyApp(this.i)) {
                  this.g = new WebView.a(var1, var2);
               } else {
                  this.g = new WebView.a(var1);
               }

               TbsLog.i("WebView", "SystemWebView Created Success! #2");
               CookieManager.getInstance().a(var1, true, false);
               CookieManager.getInstance().a();
               this.g.setFocusableInTouchMode(true);
               this.addView(this.g, new LayoutParams(-1, -1));
               this.setDownloadListener((DownloadListener)null);
               TbsLog.writeLogToDisk();
               com.tencent.smtt.sdk.o.a(var1);
            }

            try {
               if (VERSION.SDK_INT >= 11) {
                  this.removeJavascriptInterface("searchBoxJavaBridge_");
                  this.removeJavascriptInterface("accessibility");
                  this.removeJavascriptInterface("accessibilityTraversal");
               }
            } catch (Throwable var12) {
               var12.printStackTrace();
            }

            if (("com.tencent.mobileqq".equals(this.i.getApplicationInfo().packageName) || "com.tencent.mm".equals(this.i.getApplicationInfo().packageName)) && SDKEngine.a(true).h() && VERSION.SDK_INT >= 11) {
               this.setLayerType(1, (Paint)null);
            }

            if (this.f != null) {
               TbsLog.writeLogToDisk();
               if (!TbsShareManager.isThirdPartyApp(var1)) {
                  int var6 = TbsDownloadConfig.getInstance(var1).mPreferences.getInt("tbs_decouplecoreversion", 0);
                  if (var6 > 0 && var6 != com.tencent.smtt.sdk.o.a().i(var1) && var6 == com.tencent.smtt.sdk.o.a().j(var1)) {
                     com.tencent.smtt.sdk.o.a().o(var1);
                  } else {
                     TbsLog.i("WebView", "webview construction #1 deCoupleCoreVersion is " + var6 + " getTbsCoreShareDecoupleCoreVersion is " + com.tencent.smtt.sdk.o.a().i(var1) + " getTbsCoreInstalledVerInNolock is " + com.tencent.smtt.sdk.o.a().j(var1));
                  }
               }
            }

            QbSdk.continueLoadSo(var1);
         }
      }
   }

   public Object createPrintDocumentAdapter(String var1) {
      if (this.e) {
         try {
            return this.f.createPrintDocumentAdapter(var1);
         } catch (Throwable var3) {
            var3.printStackTrace();
            return null;
         }
      } else if (VERSION.SDK_INT < 21) {
         return null;
      } else {
         Object var2 = com.tencent.smtt.utils.i.a((Object)this.g, "createPrintDocumentAdapter", new Class[]{String.class}, var1);
         return var2;
      }
   }

   public int computeHorizontalScrollOffset() {
      try {
         Method var1;
         Object var2;
         if (this.e) {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.f.getView(), "computeHorizontalScrollOffset");
            var1.setAccessible(true);
            var2 = var1.invoke(this.f.getView());
            return (Integer)var2;
         } else {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeHorizontalScrollOffset");
            var1.setAccessible(true);
            var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public int computeVerticalScrollOffset() {
      try {
         Method var1;
         Object var2;
         if (this.e) {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.f.getView(), "computeVerticalScrollOffset");
            var1.setAccessible(true);
            var2 = var1.invoke(this.f.getView());
            return (Integer)var2;
         } else {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeVerticalScrollOffset");
            var1.setAccessible(true);
            var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public int computeVerticalScrollExtent() {
      try {
         Method var1;
         Object var2;
         if (this.e) {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.f.getView(), "computeVerticalScrollExtent");
            var1.setAccessible(true);
            var2 = var1.invoke(this.f.getView());
            return (Integer)var2;
         } else {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeVerticalScrollExtent");
            var1.setAccessible(true);
            var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public int computeHorizontalScrollRange() {
      try {
         if (this.e) {
            Object var4 = com.tencent.smtt.utils.i.a((Object)this.f.getView(), "computeHorizontalScrollRange", new Class[0]);
            return (Integer)var4;
         } else {
            Method var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeHorizontalScrollRange");
            var1.setAccessible(true);
            Object var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public int computeHorizontalScrollExtent() {
      try {
         Method var1;
         Object var2;
         if (this.e) {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.f.getView(), "computeHorizontalScrollExtent");
            var1.setAccessible(true);
            var2 = var1.invoke(this.f.getView());
            return (Integer)var2;
         } else {
            var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeHorizontalScrollExtent");
            var1.setAccessible(true);
            var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   public int computeVerticalScrollRange() {
      try {
         if (this.e) {
            Object var4 = com.tencent.smtt.utils.i.a((Object)this.f.getView(), "computeVerticalScrollRange", new Class[0]);
            return (Integer)var4;
         } else {
            Method var1 = (Method) com.tencent.smtt.utils.i.a(this.g, "computeVerticalScrollRange");
            var1.setAccessible(true);
            Object var2 = var1.invoke(this.g);
            return (Integer)var2;
         }
      } catch (Exception var3) {
         var3.printStackTrace();
         return -1;
      }
   }

   private boolean b(Context var1) {
      try {
         String var2 = var1.getPackageName();
         if (var2.indexOf("com.tencent.mobileqq") >= 0) {
            return true;
         }
      } catch (Throwable var3) {
         var3.printStackTrace();
      }

      return false;
   }

   @TargetApi(11)
   protected void onSizeChanged(int var1, int var2, int var3, int var4) {
      super.onSizeChanged(var1, var2, var3, var4);
      if (VERSION.SDK_INT >= 21 && this.b(this.i) && this.isHardwareAccelerated() && var1 > 0 && var2 > 0 && this.getLayerType() != 2) {
      }

   }

   private void c(Context var1) {
      if (QbSdk.i && TbsShareManager.isThirdPartyApp(var1)) {
         TbsExtensionFunctionManager.getInstance().initTbsBuglyIfNeed(var1);
      }

      X5CoreEngine var2 = X5CoreEngine.a();
      var2.a(var1);
      this.e = var2.b();
   }

   public void setScrollBarStyle(int var1) {
      if (this.e) {
         this.f.getView().setScrollBarStyle(var1);
      } else {
         this.g.setScrollBarStyle(var1);
      }

   }

   public void setHorizontalScrollbarOverlay(boolean var1) {
      if (!this.e) {
         this.g.setHorizontalScrollbarOverlay(var1);
      } else {
         this.f.setHorizontalScrollbarOverlay(var1);
      }

   }

   public void setVerticalScrollbarOverlay(boolean var1) {
      if (!this.e) {
         this.g.setVerticalScrollbarOverlay(var1);
      } else {
         this.f.setVerticalScrollbarOverlay(var1);
      }

   }

   public boolean overlayHorizontalScrollbar() {
      return !this.e ? this.g.overlayHorizontalScrollbar() : this.f.overlayHorizontalScrollbar();
   }

   public boolean overlayVerticalScrollbar() {
      return this.e ? this.f.overlayVerticalScrollbar() : this.g.overlayVerticalScrollbar();
   }

   public boolean requestChildRectangleOnScreen(View var1, Rect var2, boolean var3) {
      if (this.e) {
         View var4 = this.f.getView();
         return var4 instanceof ViewGroup ? ((ViewGroup)var4).requestChildRectangleOnScreen(var1 == this ? var4 : var1, var2, var3) : false;
      } else {
         return this.g.requestChildRectangleOnScreen((View)(var1 == this ? this.g : var1), var2, var3);
      }
   }

   public int getWebScrollX() {
      return this.e ? this.f.getView().getScrollX() : this.g.getScrollX();
   }

   public int getWebScrollY() {
      return this.e ? this.f.getView().getScrollY() : this.g.getScrollY();
   }

   public int getVisibleTitleHeight() {
      if (!this.e) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "getVisibleTitleHeight");
         return var1 == null ? 0 : (Integer)var1;
      } else {
         return this.f.getVisibleTitleHeight();
      }
   }

   public SslCertificate getCertificate() {
      return !this.e ? this.g.getCertificate() : this.f.getCertificate();
   }

   /** @deprecated */
   @Deprecated
   public void setCertificate(SslCertificate var1) {
      if (!this.e) {
         this.g.setCertificate(var1);
      } else {
         this.f.setCertificate(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void savePassword(String var1, String var2, String var3) {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "savePassword", new Class[]{String.class, String.class, String.class}, var1, var2, var3);
      } else {
         this.f.savePassword(var1, var2, var3);
      }

   }

   public void setHttpAuthUsernamePassword(String var1, String var2, String var3, String var4) {
      if (!this.e) {
         this.g.setHttpAuthUsernamePassword(var1, var2, var3, var4);
      } else {
         this.f.setHttpAuthUsernamePassword(var1, var2, var3, var4);
      }

   }

   public String[] getHttpAuthUsernamePassword(String var1, String var2) {
      return !this.e ? this.g.getHttpAuthUsernamePassword(var1, var2) : this.f.getHttpAuthUsernamePassword(var1, var2);
   }

   public void destroy() {
      Class var1 = WebView.class;
      synchronized(WebView.class) {
         if (!this.k && this.a != 0) {
            this.h();
         }
      }

      if (!this.e) {
         this.g.destroy();

         try {
            var1 = Class.forName("android.webkit.BrowserFrame");
            Field var2 = var1.getDeclaredField("sConfigCallback");
            var2.setAccessible(true);
            ComponentCallbacks var3 = (ComponentCallbacks)var2.get((Object)null);
            if (null != var3) {
               var2.set((Object)null, (Object)null);
               Class var4 = Class.forName("android.view.ViewRoot");
               Field var5 = var4.getDeclaredField("sConfigCallbacks");
               var5.setAccessible(true);
               Object var6 = var5.get((Object)null);
               if (null != var6) {
                  List var7 = (List)var6;
                  synchronized(var7) {
                     var7.remove(var3);
                  }
               }
            }
         } catch (Exception var11) {
            var1 = null;
         }
      } else {
         this.f.destroy();
      }

   }

   private long g() {
      long var1 = 0L;
      synchronized(QbSdk.h) {
         if (QbSdk.e) {
            QbSdk.g += System.currentTimeMillis() - QbSdk.f;
            TbsLog.d("sdkreport", "pv report, WebView.getWifiConnectedTime QbSdk.sWifiConnectedTime=" + QbSdk.g);
         }

         var1 = QbSdk.g / 1000L;
         QbSdk.g = 0L;
         QbSdk.f = System.currentTimeMillis();
         return var1;
      }
   }

   /** @deprecated */
   @Deprecated
   public static void enablePlatformNotifications() {
      if (!X5CoreEngine.a().b()) {
         com.tencent.smtt.utils.i.a("android.webkit.WebView", "enablePlatformNotifications");
      }

   }

   /** @deprecated */
   @Deprecated
   public static void disablePlatformNotifications() {
      if (!X5CoreEngine.a().b()) {
         com.tencent.smtt.utils.i.a("android.webkit.WebView", "disablePlatformNotifications");
      }

   }

   public void setNetworkAvailable(boolean var1) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 3) {
            this.g.setNetworkAvailable(var1);
         }
      } else {
         this.f.setNetworkAvailable(var1);
      }

   }

   public WebBackForwardList saveState(Bundle var1) {
      return !this.e ? WebBackForwardList.a(this.g.saveState(var1)) : WebBackForwardList.a(this.f.saveState(var1));
   }

   /** @deprecated */
   @Deprecated
   public boolean savePicture(Bundle var1, File var2) {
      if (!this.e) {
         Object var3 = com.tencent.smtt.utils.i.a((Object)this.g, "savePicture", new Class[]{Bundle.class, File.class}, var1, var2);
         return var3 == null ? false : (Boolean)var3;
      } else {
         return this.f.savePicture(var1, var2);
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean restorePicture(Bundle var1, File var2) {
      if (!this.e) {
         Object var3 = com.tencent.smtt.utils.i.a((Object)this.g, "restorePicture", new Class[]{Bundle.class, File.class}, var1, var2);
         return var3 == null ? false : (Boolean)var3;
      } else {
         return this.f.restorePicture(var1, var2);
      }
   }

   public WebBackForwardList restoreState(Bundle var1) {
      return !this.e ? WebBackForwardList.a(this.g.restoreState(var1)) : WebBackForwardList.a(this.f.restoreState(var1));
   }

   public JSONObject reportInitPerformance(long var1, int var3, long var4, long var6) {
      JSONObject var8 = new JSONObject();

      try {
         var8.put("IS_X5", this.e);
      } catch (JSONException var10) {
         var10.printStackTrace();
      }

      return var8;
   }

   @TargetApi(8)
   public void loadUrl(String var1, Map<String, String> var2) {
      if (this.e) {
      }

      if (var1 != null && !this.showDebugView(var1)) {
         if (!this.e) {
            if (VERSION.SDK_INT >= 8) {
               this.g.loadUrl(var1, var2);
            }
         } else {
            this.f.loadUrl(var1, var2);
         }

      }
   }

   public void loadUrl(String var1) {
      if (this.e) {
      }

      if (var1 != null && !this.showDebugView(var1)) {
         if (!this.e) {
            this.g.loadUrl(var1);
         } else {
            this.f.loadUrl(var1);
         }

      }
   }

   @SuppressLint({"NewApi"})
   public boolean showDebugView(String var1) {
      var1 = var1.toLowerCase();
      if (var1.startsWith("https://debugtbs.qq.com")) {
         this.getView().setVisibility(4);
         com.tencent.smtt.utils.d var3 = com.tencent.smtt.utils.d.a(this.i);
         var3.a(var1, this, this.i, com.tencent.smtt.sdk.n.a().getLooper());
         return true;
      } else if (var1.startsWith("https://debugx5.qq.com")) {
         if (!this.e) {
            StringBuilder var2 = new StringBuilder();
            var2.append("<!DOCTYPE html><html><body>");
            var2.append("<head>");
            var2.append("<title>无法打开debugx5</title>");
            var2.append("<meta name=\"viewport\" content=\"width=device-width, user-scalable=no\" />");
            var2.append("</head>");
            var2.append("<br/><br /><h2>debugx5页面仅在使用了X5内核时有效，由于当前没有使用X5内核，无法打开debugx5！</h2><br />");
            var2.append("尝试<a href=\"https://debugtbs.qq.com?10000\">进入DebugTbs安装或打开X5内核</a>");
            var2.append("</body></html>");
            this.loadDataWithBaseURL((String)null, var2.toString(), "text/html", "utf-8", (String)null);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @TargetApi(5)
   public void postUrl(String var1, byte[] var2) {
      if (!this.e) {
         this.g.postUrl(var1, var2);
      } else {
         this.f.postUrl(var1, var2);
      }

   }

   public void loadData(String var1, String var2, String var3) {
      if (!this.e) {
         this.g.loadData(var1, var2, var3);
      } else {
         this.f.loadData(var1, var2, var3);
      }

   }

   public void loadDataWithBaseURL(String var1, String var2, String var3, String var4, String var5) {
      if (!this.e) {
         this.g.loadDataWithBaseURL(var1, var2, var3, var4, var5);
      } else {
         this.f.loadDataWithBaseURL(var1, var2, var3, var4, var5);
      }

   }

   @TargetApi(11)
   public void saveWebArchive(String var1) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.i.a((Object)this.g, "saveWebArchive", new Class[]{String.class}, var1);
         }
      } else {
         this.f.saveWebArchive(var1);
      }

   }

   @TargetApi(11)
   public void saveWebArchive(String var1, boolean var2, ValueCallback<String> var3) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.i.a((Object)this.g, "saveWebArchive", new Class[]{String.class, Boolean.TYPE, android.webkit.ValueCallback.class}, var1, var2, var3);
         }
      } else {
         this.f.saveWebArchive(var1, var2, var3);
      }

   }

   public void stopLoading() {
      if (!this.e) {
         this.g.stopLoading();
      } else {
         this.f.stopLoading();
      }

   }

   public static void setWebContentsDebuggingEnabled(boolean var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var1.c().a(var0);
      } else if (VERSION.SDK_INT >= 19) {
         try {
            Class var2 = Class.forName("android.webkit.WebView");
            Class[] var3 = new Class[]{Boolean.TYPE};
            m = var2.getDeclaredMethod("setWebContentsDebuggingEnabled", var3);
            if (m != null) {
               m.setAccessible(true);
               m.invoke((Object)null, var0);
            }
         } catch (Exception var4) {
            TbsLog.e("QbSdk", "Exception:" + var4.getStackTrace());
            var4.printStackTrace();
         }
      }

   }

   public void reload() {
      if (!this.e) {
         this.g.reload();
      } else {
         this.f.reload();
      }

   }

   public boolean canGoBack() {
      return !this.e ? this.g.canGoBack() : this.f.canGoBack();
   }

   public void goBack() {
      if (!this.e) {
         this.g.goBack();
      } else {
         this.f.goBack();
      }

   }

   public boolean canGoForward() {
      return !this.e ? this.g.canGoForward() : this.f.canGoForward();
   }

   public void goForward() {
      if (!this.e) {
         this.g.goForward();
      } else {
         this.f.goForward();
      }

   }

   public boolean canGoBackOrForward(int var1) {
      return !this.e ? this.g.canGoBackOrForward(var1) : this.f.canGoBackOrForward(var1);
   }

   public void goBackOrForward(int var1) {
      if (!this.e) {
         this.g.goBackOrForward(var1);
      } else {
         this.f.goBackOrForward(var1);
      }

   }

   public boolean pageUp(boolean var1) {
      return !this.e ? this.g.pageUp(var1) : this.f.pageUp(var1, -1);
   }

   public boolean pageDown(boolean var1) {
      return !this.e ? this.g.pageDown(var1) : this.f.pageDown(var1, -1);
   }

   /** @deprecated */
   @Deprecated
   public void clearView() {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "clearView");
      } else {
         this.f.clearView();
      }

   }

   /** @deprecated */
   @Deprecated
   public Picture capturePicture() {
      if (!this.e) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "capturePicture");
         return var1 == null ? null : (Picture)var1;
      } else {
         return this.f.capturePicture();
      }
   }

   /** @deprecated */
   @Deprecated
   public float getScale() {
      if (!this.e) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "getScale");
         return var1 == null ? 0.0F : (Float)var1;
      } else {
         return this.f.getScale();
      }
   }

   public void setInitialScale(int var1) {
      if (!this.e) {
         this.g.setInitialScale(var1);
      } else {
         this.f.setInitialScale(var1);
      }

   }

   public void invokeZoomPicker() {
      if (!this.e) {
         this.g.invokeZoomPicker();
      } else {
         this.f.invokeZoomPicker();
      }

   }

   public WebView.HitTestResult getHitTestResult() {
      return !this.e ? new WebView.HitTestResult(this.g.getHitTestResult()) : new WebView.HitTestResult(this.f.getHitTestResult());
   }

   public IX5WebViewBase.HitTestResult getX5HitTestResult() {
      return !this.e ? null : this.f.getHitTestResult();
   }

   public void requestFocusNodeHref(Message var1) {
      if (!this.e) {
         this.g.requestFocusNodeHref(var1);
      } else {
         this.f.requestFocusNodeHref(var1);
      }

   }

   public void requestImageRef(Message var1) {
      if (!this.e) {
         this.g.requestImageRef(var1);
      } else {
         this.f.requestImageRef(var1);
      }

   }

   public String getUrl() {
      return !this.e ? this.g.getUrl() : this.f.getUrl();
   }

   @TargetApi(3)
   public String getOriginalUrl() {
      return !this.e ? this.g.getOriginalUrl() : this.f.getOriginalUrl();
   }

   public String getTitle() {
      return !this.e ? this.g.getTitle() : this.f.getTitle();
   }

   public Bitmap getFavicon() {
      return !this.e ? this.g.getFavicon() : this.f.getFavicon();
   }

   public static PackageInfo getCurrentWebViewPackage() {
      if (!X5CoreEngine.a().b()) {
         if (VERSION.SDK_INT < 26) {
            return null;
         } else {
            try {
               Object var0 = com.tencent.smtt.utils.i.a("android.webkit.WebView", "getCurrentWebViewPackage");
               return (PackageInfo)var0;
            } catch (Exception var1) {
               var1.printStackTrace();
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public void setRendererPriorityPolicy(int var1, boolean var2) {
      try {
         if (!this.e) {
            if (VERSION.SDK_INT >= 26) {
               com.tencent.smtt.utils.i.a((Object)this.g, "setRendererPriorityPolicy", new Class[]{Integer.TYPE, Boolean.TYPE}, var1, var2);
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public int getRendererRequestedPriority() {
      try {
         if (!this.e) {
            if (VERSION.SDK_INT < 26) {
               return 0;
            } else {
               Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "getRendererRequestedPriority");
               return var1 == null ? 0 : (Integer)var1;
            }
         } else {
            return 0;
         }
      } catch (Exception var2) {
         var2.printStackTrace();
         return 0;
      }
   }

   public boolean getRendererPriorityWaivedWhenNotVisible() {
      try {
         if (!this.e) {
            if (VERSION.SDK_INT < 26) {
               return false;
            } else {
               Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "getRendererPriorityWaivedWhenNotVisible");
               return var1 == null ? false : (Boolean)var1;
            }
         } else {
            return false;
         }
      } catch (Exception var2) {
         var2.printStackTrace();
         return false;
      }
   }

   public WebChromeClient getWebChromeClient() {
      return this.o;
   }

   public WebViewClient getWebViewClient() {
      return this.n;
   }

   public int getProgress() {
      return !this.e ? this.g.getProgress() : this.f.getProgress();
   }

   public int getContentHeight() {
      return !this.e ? this.g.getContentHeight() : this.f.getContentHeight();
   }

   public int getContentWidth() {
      if (!this.e) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "getContentWidth");
         return var1 == null ? 0 : (Integer)var1;
      } else {
         return this.f.getContentWidth();
      }
   }

   public void pauseTimers() {
      if (!this.e) {
         this.g.pauseTimers();
      } else {
         this.f.pauseTimers();
      }

   }

   public void resumeTimers() {
      if (!this.e) {
         this.g.resumeTimers();
      } else {
         this.f.resumeTimers();
      }

   }

   public void onPause() {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "onPause");
      } else {
         this.f.onPause();
      }

   }

   public void onResume() {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "onResume");
      } else {
         this.f.onResume();
      }

   }

   /** @deprecated */
   @Deprecated
   public void freeMemory() {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "freeMemory");
      } else {
         this.f.freeMemory();
      }

   }

   public void clearCache(boolean var1) {
      if (!this.e) {
         this.g.clearCache(var1);
      } else {
         this.f.clearCache(var1);
      }

   }

   public void clearFormData() {
      if (!this.e) {
         this.g.clearFormData();
      } else {
         this.f.clearFormData();
      }

   }

   public void clearHistory() {
      if (!this.e) {
         this.g.clearHistory();
      } else {
         this.f.clearHistory();
      }

   }

   public void clearSslPreferences() {
      if (!this.e) {
         this.g.clearSslPreferences();
      } else {
         this.f.clearSslPreferences();
      }

   }

   public WebBackForwardList copyBackForwardList() {
      return this.e ? WebBackForwardList.a(this.f.copyBackForwardList()) : WebBackForwardList.a(this.g.copyBackForwardList());
   }

   @TargetApi(16)
   public void setFindListener(final IX5WebViewBase.FindListener var1) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 16) {
            this.g.setFindListener(new FindListener() {
               public void onFindResultReceived(int var1x, int var2, boolean var3) {
                  var1.onFindResultReceived(var1x, var2, var3);
               }
            });
         }
      } else {
         this.f.setFindListener(var1);
      }

   }

   @TargetApi(3)
   public void findNext(boolean var1) {
      if (!this.e) {
         this.g.findNext(var1);
      } else {
         this.f.findNext(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public int findAll(String var1) {
      if (!this.e) {
         Object var2 = com.tencent.smtt.utils.i.a((Object)this.g, "findAll", new Class[]{String.class}, var1);
         return var2 == null ? 0 : (Integer)var2;
      } else {
         return this.f.findAll(var1);
      }
   }

   /** @deprecated */
   @Deprecated
   public static String findAddress(String var0) {
      return !X5CoreEngine.a().b() ? android.webkit.WebView.findAddress(var0) : null;
   }

   @TargetApi(16)
   public void findAllAsync(String var1) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 16) {
            com.tencent.smtt.utils.i.a((Object)this.g, "findAllAsync", new Class[]{String.class}, var1);
         }
      } else {
         this.f.findAllAsync(var1);
      }

   }

   public boolean showFindDialog(String var1, boolean var2) {
      return false;
   }

   @TargetApi(3)
   public void clearMatches() {
      if (!this.e) {
         this.g.clearMatches();
      } else {
         this.f.clearMatches();
      }

   }

   public void documentHasImages(Message var1) {
      if (!this.e) {
         this.g.documentHasImages(var1);
      } else {
         this.f.documentHasImages(var1);
      }

   }

   public void setWebViewClient(WebViewClient var1) {
      if (this.e) {
         this.f.setWebViewClient(var1 == null ? null : new X5WebViewClient(X5CoreEngine.a().a(true).j(), this, var1));
      } else {
         this.g.setWebViewClient(var1 == null ? null : new SystemWebViewClient(this, var1));
      }

      this.n = var1;
   }

   public void setWebViewCallbackClient(WebViewCallbackClient var1) {
      this.mWebViewCallbackClient = var1;
      if (this.e && this.getX5WebViewExtension() != null) {
         Bundle var2 = new Bundle();
         var2.putBoolean("flag", true);
         this.getX5WebViewExtension().invokeMiscMethod("setWebViewCallbackClientFlag", var2);
      }

   }

   public void customDiskCachePathEnabled(boolean var1, String var2) {
      if (this.e && this.getX5WebViewExtension() != null) {
         Bundle var3 = new Bundle();
         var3.putBoolean("enabled", var1);
         var3.putString("path", var2);
         this.getX5WebViewExtension().invokeMiscMethod("customDiskCachePathEnabled", var3);
      }

   }

   public void setDownloadListener(final DownloadListener var1) {
      if (!this.e) {
         this.g.setDownloadListener(new android.webkit.DownloadListener() {
            public void onDownloadStart(String var1x, String var2, String var3, String var4, long var5) {
               if (var1 == null) {
                  ApplicationInfo var7 = WebView.this.i == null ? null : WebView.this.i.getApplicationInfo();
                  if (var7 == null || !"com.tencent.mm".equals(var7.packageName)) {
                     MttLoader.loadUrl(WebView.this.i, var1x, (HashMap)null, (WebView)null);
                  }
               } else {
                  var1.onDownloadStart(var1x, var2, var3, var4, var5);
               }

            }
         });
      } else {
         this.f.setDownloadListener(new QQBrowserDownloadListener(this, var1, this.e));
      }

   }

   public void setWebChromeClient(WebChromeClient var1) {
      if (this.e) {
         this.f.setWebChromeClient(var1 == null ? null : new X5WebChromeClient(X5CoreEngine.a().a(true).i(), this, var1));
      } else {
         this.g.setWebChromeClient(var1 == null ? null : new SystemWebChromeClient(this, var1));
      }

      this.o = var1;
   }

   /** @deprecated */
   @Deprecated
   public void setPictureListener(final WebView.PictureListener var1) {
      if (!this.e) {
         if (var1 == null) {
            this.g.setPictureListener((android.webkit.WebView.PictureListener)null);
         } else {
            this.g.setPictureListener(new android.webkit.WebView.PictureListener() {
               public void onNewPicture(android.webkit.WebView var1x, Picture var2) {
                  WebView.this.a(var1x);
                  var1.onNewPicture(WebView.this, var2);
               }
            });
         }
      } else if (var1 == null) {
         this.f.setPictureListener((IX5WebViewBase.PictureListener)null);
      } else {
         this.f.setPictureListener(new IX5WebViewBase.PictureListener() {
            public void onNewPictureIfHaveContent(IX5WebViewBase var1x, Picture var2) {
            }

            public void onNewPicture(IX5WebViewBase var1x, Picture var2, boolean var3) {
               WebView.this.a(var1x);
               var1.onNewPicture(WebView.this, var2);
            }
         });
      }

   }

   public void addJavascriptInterface(Object var1, String var2) {
      if (!this.e) {
         this.g.addJavascriptInterface(var1, var2);
      } else {
         this.f.addJavascriptInterface(var1, var2);
      }

   }

   @TargetApi(11)
   public void removeJavascriptInterface(String var1) {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.i.a((Object)this.g, "removeJavascriptInterface", new Class[]{String.class}, var1);
         }
      } else {
         this.f.removeJavascriptInterface(var1);
      }

   }

   public WebSettings getSettings() {
      if (this.h != null) {
         return this.h;
      } else {
         return this.e ? (this.h = new WebSettings(this.f.getSettings())) : (this.h = new WebSettings(this.g.getSettings()));
      }
   }

   /** @deprecated */
   @Deprecated
   public static synchronized Object getPluginList() {
      return !X5CoreEngine.a().b() ? com.tencent.smtt.utils.i.a("android.webkit.WebView", "getPluginList") : null;
   }

   /** @deprecated */
   @Deprecated
   public void refreshPlugins(boolean var1) {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "refreshPlugins", new Class[]{Boolean.TYPE}, var1);
      } else {
         this.f.refreshPlugins(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setMapTrackballToArrowKeys(boolean var1) {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "setMapTrackballToArrowKeys", new Class[]{Boolean.TYPE}, var1);
      } else {
         this.f.setMapTrackballToArrowKeys(var1);
      }

   }

   public void flingScroll(int var1, int var2) {
      if (!this.e) {
         this.g.flingScroll(var1, var2);
      } else {
         this.f.flingScroll(var1, var2);
      }

   }

   /** @deprecated */
   @Deprecated
   public View getZoomControls() {
      return !this.e ? (View)com.tencent.smtt.utils.i.a((Object)this.g, "getZoomControls") : this.f.getZoomControls();
   }

   /** @deprecated */
   @Deprecated
   public boolean canZoomIn() {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "canZoomIn");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return this.f.canZoomIn();
      }
   }

   public boolean isPrivateBrowsingEnabled() {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "isPrivateBrowsingEnabled");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return this.f.isPrivateBrowsingEnable();
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean canZoomOut() {
      if (!this.e) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.g, "canZoomOut");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return this.f.canZoomOut();
      }
   }

   public boolean zoomIn() {
      return !this.e ? this.g.zoomIn() : this.f.zoomIn();
   }

   public boolean zoomOut() {
      return !this.e ? this.g.zoomOut() : this.f.zoomOut();
   }

   public void dumpViewHierarchyWithProperties(BufferedWriter var1, int var2) {
      if (!this.e) {
         com.tencent.smtt.utils.i.a((Object)this.g, "dumpViewHierarchyWithProperties", new Class[]{BufferedWriter.class, Integer.TYPE}, var1, var2);
      } else {
         this.f.dumpViewHierarchyWithProperties(var1, var2);
      }

   }

   public View findHierarchyView(String var1, int var2) {
      return !this.e ? (View)com.tencent.smtt.utils.i.a((Object)this.g, "findHierarchyView", new Class[]{String.class, Integer.TYPE}, var1, var2) : this.f.findHierarchyView(var1, var2);
   }

   public void computeScroll() {
      if (!this.e) {
         this.g.computeScroll();
      } else {
         this.f.computeScroll();
      }

   }

   public void setBackgroundColor(int var1) {
      if (!this.e) {
         this.g.setBackgroundColor(var1);
      } else {
         this.f.setBackgroundColor(var1);
      }

      super.setBackgroundColor(var1);
   }

   public View getView() {
      return (View)(!this.e ? this.g : this.f.getView());
   }

   protected void onDetachedFromWindow() {
      super.onDetachedFromWindow();
      if (!this.k && this.a != 0) {
         this.h();
      }

   }

   protected void onVisibilityChanged(View var1, int var2) {
      if (this.i == null) {
         super.onVisibilityChanged(var1, var2);
      } else {
         if (p == null) {
            ApplicationInfo var3 = this.i.getApplicationInfo();
            p = var3.packageName;
         }

         if (p != null && (p.equals("com.tencent.mm") || p.equals("com.tencent.mobileqq"))) {
            super.onVisibilityChanged(var1, var2);
         } else {
            if (var2 != 0 && !this.k && this.a != 0) {
               this.h();
            }

            super.onVisibilityChanged(var1, var2);
         }
      }
   }

   private void h() {
      (new Thread(new Runnable() {
         public void run() {
            if (!WebView.this.k && WebView.this.a != 0) {
               Class var1 = WebView.class;
               synchronized(WebView.class) {
                  if (WebView.this.k || WebView.this.a == 0) {
                     return;
                  }

                  WebView.this.k = true;
                  String var2 = "";
                  String var3 = "";
                  String var4 = "";
                  if (WebView.this.e) {
                     Bundle var5 = WebView.this.f.getX5WebViewExtension().getSdkQBStatisticsInfo();
                     if (var5 != null) {
                        var2 = var5.getString("guid");
                        var3 = var5.getString("qua2");
                        var4 = var5.getString("lc");
                     }
                  }

                  if ("com.qzone".equals(WebView.this.i.getApplicationInfo().packageName)) {
                     int var10 = WebView.this.e(WebView.this.i);
                     WebView.this.a = var10 == -1 ? WebView.this.a : var10;
                     WebView.this.f(WebView.this.i);
                  }

                  boolean var11 = false;

                  try {
                     var11 = WebView.this.f.getX5WebViewExtension().isX5CoreSandboxMode();
                  } catch (Throwable var8) {
                     TbsLog.w("onVisibilityChanged", "exception: " + var8);
                  }

                  com.tencent.smtt.sdk.stat.b.a(WebView.this.i, var2, var3, var4, WebView.this.a, WebView.this.e, WebView.this.g(), var11);
                  WebView.this.a = 0;
                  WebView.this.k = false;
               }
            }

         }
      })).start();
   }

   public IX5WebViewExtension getX5WebViewExtension() {
      return !this.e ? null : this.f.getX5WebViewExtension();
   }

   public IX5WebSettingsExtension getSettingsExtension() {
      return !this.e ? null : this.f.getX5WebViewExtension().getSettingsExtension();
   }

   public void setWebViewClientExtension(IX5WebViewClientExtension var1) {
      if (this.e) {
         this.f.getX5WebViewExtension().setWebViewClientExtension(var1);
      }
   }

   public void setWebChromeClientExtension(IX5WebChromeClientExtension var1) {
      if (this.e) {
         this.f.getX5WebViewExtension().setWebChromeClientExtension(var1);
      }
   }

   public IX5WebChromeClientExtension getWebChromeClientExtension() {
      return !this.e ? null : this.f.getX5WebViewExtension().getWebChromeClientExtension();
   }

   public IX5WebViewClientExtension getWebViewClientExtension() {
      return !this.e ? null : this.f.getX5WebViewExtension().getWebViewClientExtension();
   }

   public void evaluateJavascript(String var1, ValueCallback<String> var2) {
      if (this.e) {
         try {
            View var3 = this.f.getView();
            Method var4 = com.tencent.smtt.utils.i.a(var3, "evaluateJavascript", String.class, android.webkit.ValueCallback.class);
            var4.setAccessible(true);
            var4.invoke(this.f.getView(), var1, var2);
         } catch (Exception var7) {
            var7.printStackTrace();
            this.loadUrl(var1);
         }
      } else if (VERSION.SDK_INT >= 19) {
         try {
            Class var8 = Class.forName("android.webkit.WebView");
            Class[] var9 = new Class[]{String.class, android.webkit.ValueCallback.class};
            Method var5 = var8.getDeclaredMethod("evaluateJavascript", var9);
            var5.setAccessible(true);
            var5.invoke(this.g, var1, var2);
         } catch (Exception var6) {
            var6.printStackTrace();
         }
      }

   }

   public static int getTbsCoreVersion(Context var0) {
      return QbSdk.getTbsVersion(var0);
   }

   public static int getTbsSDKVersion(Context var0) {
      return 44051;
   }

   public boolean setVideoFullScreen(Context var1, boolean var2) {
      String var3 = var1.getApplicationInfo().processName;
      if (var3.contains("com.tencent.android.qqdownloader") && this.f != null) {
         Bundle var4 = new Bundle();
         if (var2) {
            var4.putInt("DefaultVideoScreen", 2);
         } else {
            var4.putInt("DefaultVideoScreen", 1);
         }

         this.f.getX5WebViewExtension().invokeMiscMethod("setVideoParams", var4);
         return true;
      } else {
         return false;
      }
   }

   void a(android.webkit.WebView var1) {
      if (!this.e) {
      }

   }

   android.webkit.WebView a() {
      return !this.e ? this.g : null;
   }

   void a(IX5WebViewBase var1) {
      this.f = var1;
   }

   IX5WebViewBase b() {
      return this.f;
   }

   public void setOnTouchListener(OnTouchListener var1) {
      this.getView().setOnTouchListener(var1);
   }

   private void a(AttributeSet var1) {
      try {
         if (var1 != null) {
            int var2 = var1.getAttributeCount();

            for(int var3 = 0; var3 < var2; ++var3) {
               if (var1.getAttributeName(var3).equalsIgnoreCase("scrollbars")) {
                  int[] var4 = this.getResources().getIntArray(16842974);
                  int var5 = var1.getAttributeIntValue(var3, -1);
                  if (var5 == var4[1]) {
                     this.f.getView().setVerticalScrollBarEnabled(false);
                     this.f.getView().setHorizontalScrollBarEnabled(false);
                  } else if (var5 == var4[2]) {
                     this.f.getView().setVerticalScrollBarEnabled(false);
                  } else if (var5 == var4[3]) {
                     this.f.getView().setHorizontalScrollBarEnabled(false);
                  }
               }
            }
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

   }

   private Context d(Context var1) {
      return VERSION.SDK_INT >= 21 && VERSION.SDK_INT <= 22 ? var1.createConfigurationContext(new Configuration()) : var1;
   }

   public void switchNightMode(boolean var1) {
      if (var1 != w) {
         w = var1;
         if (w) {
            TbsLog.e("QB_SDK", "deleteNightMode");
            this.loadUrl("javascript:document.getElementsByTagName('HEAD').item(0).removeChild(document.getElementById('QQBrowserSDKNightMode'));");
         } else {
            TbsLog.e("QB_SDK", "nightMode");
            this.loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
         }

      }
   }

   public void switchToNightMode() {
      TbsLog.e("QB_SDK", "switchToNightMode 01");
      if (!w) {
         TbsLog.e("QB_SDK", "switchToNightMode");
         this.loadUrl("javascript:var style = document.createElement('style');style.type='text/css';style.id='QQBrowserSDKNightMode';style.innerHTML='html,body{background:none !important;background-color: #1d1e2a !important;}html *{background-color: #1d1e2a !important; color:#888888 !important;border-color:#3e4f61 !important;text-shadow:none !important;box-shadow:none !important;}a,a *{border-color:#4c5b99 !important; color:#2d69b3 !important;text-decoration:none !important;}a:visited,a:visited *{color:#a600a6 !important;}a:active,a:active *{color:#5588AA !important;}input,select,textarea,option,button{background-image:none !important;color:#AAAAAA !important;border-color:#4c5b99 !important;}form,div,button,span{background-color:#1d1e2a !important; border-color:#4c5b99 !important;}img{opacity:0.5}';document.getElementsByTagName('HEAD').item(0).appendChild(style);");
      }

   }

   public static synchronized void setSysDayOrNight(boolean var0) {
      if (var0 != w) {
         w = var0;
         if (v == null) {
            v = new Paint();
            v.setColor(-16777216);
         }

         if (!var0) {
            if (v.getAlpha() != NIGHT_MODE_ALPHA) {
               v.setAlpha(NIGHT_MODE_ALPHA);
            }
         } else if (v.getAlpha() != 255) {
            v.setAlpha(255);
         }

      }
   }

   public void setDayOrNight(boolean var1) {
      try {
         if (this.e) {
            this.getSettingsExtension().setDayOrNight(var1);
         }

         setSysDayOrNight(var1);
         this.getView().postInvalidate();
      } catch (Throwable var3) {
         var3.printStackTrace();
      }

   }

   public void setARModeEnable(boolean var1) {
      try {
         if (this.e) {
            this.getSettingsExtension().setARModeEnable(var1);
         }
      } catch (Throwable var3) {
         var3.printStackTrace();
      }

   }

   public boolean isDayMode() {
      return w;
   }

   public int getSysNightModeAlpha() {
      return NIGHT_MODE_ALPHA;
   }

   public void setSysNightModeAlpha(int var1) {
      NIGHT_MODE_ALPHA = var1;
   }

   public boolean onLongClick(View var1) {
      if (this.y != null) {
         return !this.y.onLongClick(var1) ? this.a(var1) : true;
      } else {
         return this.a(var1);
      }
   }

   public void setOnLongClickListener(OnLongClickListener var1) {
      if (!this.e) {
         this.g.setOnLongClickListener(var1);
      } else {
         View var2 = this.f.getView();

         try {
            if (this.x == null) {
               Method var3 = (Method) com.tencent.smtt.utils.i.a(var2, "getListenerInfo");
               var3.setAccessible(true);
               Object var4 = var3.invoke(var2, (Object[])null);
               Field var5 = var4.getClass().getDeclaredField("mOnLongClickListener");
               var5.setAccessible(true);
               this.x = var5.get(var4);
            }
         } catch (Throwable var6) {
            return;
         }

         this.y = var1;
         this.getView().setOnLongClickListener(this);
      }

   }

   private int e(Context var1) {
      FileOutputStream var2 = FileUtil.b(var1, true, "tbslock.txt");
      FileLock var3 = null;
      if (var2 != null) {
         var3 = FileUtil.a(var1, var2);
         if (var3 == null) {
            return -1;
         } else {
            boolean var4 = c.tryLock();
            if (var4) {
               FileInputStream var5 = null;

               try {
                  File var6 = QbSdk.getTbsFolderDir(var1);
                  File var25 = new File(var6 + File.separator + "core_private", "pv.db");
                  if (var25 != null && var25.exists()) {
                     Properties var26 = new Properties();
                     var5 = new FileInputStream(var25);
                     var26.load(var5);
                     var5.close();
                     String var9 = var26.getProperty("PV");
                     if (var9 == null) {
                        byte var27 = -1;
                        return var27;
                     } else {
                        int var10 = Integer.parseInt(var9);
                        int var11 = var10;
                        return var11;
                     }
                  } else {
                     byte var8 = -1;
                     return var8;
                  }
               } catch (Exception var23) {
                  TbsLog.e("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + var23.toString());
                  byte var7 = -1;
                  return var7;
               } finally {
                  if (var5 != null) {
                     try {
                        var5.close();
                     } catch (IOException var22) {
                        TbsLog.e("getTbsCorePV", "TbsInstaller--getTbsCorePV IOException=" + var22.toString());
                     }
                  }

                  c.unlock();
                  FileUtil.freeFileLock(var3, var2);
               }
            } else {
               FileUtil.freeFileLock(var3, var2);
               return -1;
            }
         }
      } else {
         return -1;
      }
   }

   void a(Context var1) {
      int var3 = this.e(var1);
      String var2;
      if (var3 != -1) {
         ++var3;
         var2 = "PV=" + String.valueOf(var3);
      } else {
         var2 = "PV=1";
      }

      File var4 = QbSdk.getTbsFolderDir(var1);
      File var5 = new File(var4 + File.separator + "core_private", "pv.db");
      if (var5 != null) {
         try {
            try {
               var5.getParentFile().mkdirs();
               if (!var5.isFile() || !var5.exists()) {
                  var5.createNewFile();
               }

               d = new FileOutputStream(var5, false);
               d.write(var2.getBytes());
            } finally {
               if (d != null) {
                  d.flush();
               }

            }
         } catch (Throwable var10) {
         }

      }
   }

   private void f(Context var1) {
      try {
         File var2 = QbSdk.getTbsFolderDir(var1);
         File var3 = new File(var2 + File.separator + "core_private", "pv.db");
         if (var3 == null || !var3.exists()) {
            return;
         }

         var3.delete();
      } catch (Exception var4) {
         TbsLog.i("getTbsCorePV", "TbsInstaller--getTbsCorePV Exception=" + var4.toString());
      }

   }

   private boolean a(View var1) {
      if (this.i != null && getTbsCoreVersion(this.i) > 36200) {
         return false;
      } else {
         Object var2 = com.tencent.smtt.utils.i.a(this.x, "onLongClick", new Class[]{View.class}, var1);
         return var2 != null ? (Boolean)var2 : false;
      }
   }

   public void addView(View var1) {
      if (!this.e) {
         this.g.addView(var1);
      } else {
         View var2 = this.f.getView();

         try {
            Method var3 = com.tencent.smtt.utils.i.a(var2, "addView", View.class);
            var3.setAccessible(true);
            var3.invoke(var2, var1);
         } catch (Throwable var4) {
            return;
         }
      }

   }

   public void removeView(View var1) {
      if (!this.e) {
         this.g.removeView(var1);
      } else {
         View var2 = this.f.getView();

         try {
            Method var3 = com.tencent.smtt.utils.i.a(var2, "removeView", View.class);
            var3.setAccessible(true);
            var3.invoke(var2, var1);
         } catch (Throwable var4) {
            return;
         }
      }

   }

   public static String getCrashExtraMessage(Context var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1 = "tbs_core_version:" + QbSdk.getTbsVersionForCrash(var0) + ";" + "tbs_sdk_version:" + '갓' + ";";
         boolean var2 = false;
         if ("com.tencent.mm".equals(var0.getApplicationInfo().packageName)) {
            var2 = true;

            try {
               Class var3 = Class.forName("de.robv.android.xposed.XposedBridge");
            } catch (ClassNotFoundException var5) {
               var2 = false;
            } catch (Throwable var6) {
               var6.printStackTrace();
               var2 = false;
            }
         }

         if (var2) {
            return var1 + "isXposed=true;";
         } else {
            StringBuilder var7 = new StringBuilder();
            var7.append(SDKEngine.a(true).e());
            var7.append("\n");
            var7.append(var1);
            if (!TbsShareManager.isThirdPartyApp(var0) && QbSdk.n != null && QbSdk.n.containsKey("weapp_id") && QbSdk.n.containsKey("weapp_name")) {
               String var4 = "weapp_id:" + QbSdk.n.get("weapp_id") + ";" + "weapp_name" + ":" + QbSdk.n.get("weapp_name") + ";";
               var7.append("\n");
               var7.append(var4);
            }

            return var7.length() > 8192 ? var7.substring(var7.length() - 8192) : var7.toString();
         }
      }
   }

   public static boolean getTbsNeedReboot() {
      c();
      boolean var0 = SDKEngine.a(true).f();
      return var0;
   }

   static void c() {
      Runnable var0 = new Runnable() {
         public void run() {
            if (WebView.j == null) {
               TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--mAppContext == null");
            } else {
               SDKEngine var1 = SDKEngine.a(true);
               if (SDKEngine.b) {
                  TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--needReboot = true");
               } else {
                  m var2 = com.tencent.smtt.sdk.m.a(WebView.j);
                  int var3 = var2.c();
                  TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--installStatus = " + var3);
                  int var4;
                  if (var3 == 2) {
                     TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--install setTbsNeedReboot true");
                     var4 = var2.b();
                     var1.a(String.valueOf(var4));
                     var1.b(true);
                  } else {
                     var4 = var2.b("copy_status");
                     TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copyStatus = " + var4);
                     if (var4 == 1) {
                        TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--copy setTbsNeedReboot true");
                        int var5 = var2.c("copy_core_ver");
                        var1.a(String.valueOf(var5));
                        var1.b(true);
                     } else {
                        if (!X5CoreEngine.a().b() && (var3 == 3 || var4 == 3)) {
                           TbsLog.d("TbsNeedReboot", "WebView.updateNeeeRebootStatus--setTbsNeedReboot true");
                           var1.a(String.valueOf(SDKEngine.d()));
                           var1.b(true);
                        }

                     }
                  }
               }
            }
         }
      };

      try {
         (new Thread(var0)).start();
      } catch (Throwable var2) {
         TbsLog.e("webview", "updateRebootStatus excpetion: " + var2);
      }

   }

   public void super_onScrollChanged(int var1, int var2, int var3, int var4) {
      if (!this.e) {
         this.g.a(var1, var2, var3, var4);
      } else {
         View var5 = this.f.getView();

         try {
            com.tencent.smtt.utils.i.a((Object)var5, "super_onScrollChanged", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE}, var1, var2, var3, var4);
         } catch (Throwable var7) {
            var7.printStackTrace();
         }
      }

   }

   public boolean super_overScrollBy(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      if (!this.e) {
         return this.g.a(var1, var2, var3, var4, var5, var6, var7, var8, var9);
      } else {
         View var10 = this.f.getView();

         try {
            Object var11 = com.tencent.smtt.utils.i.a((Object)var10, "super_overScrollBy", new Class[]{Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Boolean.TYPE}, var1, var2, var3, var4, var5, var6, var7, var8, var9);
            return var11 == null ? false : (Boolean)var11;
         } catch (Throwable var12) {
            return false;
         }
      }
   }

   public void super_onOverScrolled(int var1, int var2, boolean var3, boolean var4) {
      if (!this.e) {
         this.g.a(var1, var2, var3, var4);
      } else {
         View var5 = this.f.getView();

         try {
            com.tencent.smtt.utils.i.a((Object)var5, "super_onOverScrolled", new Class[]{Integer.TYPE, Integer.TYPE, Boolean.TYPE, Boolean.TYPE}, var1, var2, var3, var4);
         } catch (Throwable var7) {
            var7.printStackTrace();
         }
      }

   }

   public boolean super_dispatchTouchEvent(MotionEvent var1) {
      if (!this.e) {
         return this.g.b(var1);
      } else {
         View var2 = this.f.getView();

         try {
            Object var3 = com.tencent.smtt.utils.i.a((Object)var2, "super_dispatchTouchEvent", new Class[]{MotionEvent.class}, var1);
            return var3 == null ? false : (Boolean)var3;
         } catch (Throwable var4) {
            return false;
         }
      }
   }

   public boolean super_onInterceptTouchEvent(MotionEvent var1) {
      if (!this.e) {
         return this.g.c(var1);
      } else {
         View var2 = this.f.getView();

         try {
            Object var3 = com.tencent.smtt.utils.i.a((Object)var2, "super_onInterceptTouchEvent", new Class[]{MotionEvent.class}, var1);
            return var3 == null ? false : (Boolean)var3;
         } catch (Throwable var4) {
            return false;
         }
      }
   }

   public boolean super_onTouchEvent(MotionEvent var1) {
      if (!this.e) {
         return this.g.a(var1);
      } else {
         View var2 = this.f.getView();

         try {
            Object var3 = com.tencent.smtt.utils.i.a((Object)var2, "super_onTouchEvent", new Class[]{MotionEvent.class}, var1);
            return var3 == null ? false : (Boolean)var3;
         } catch (Throwable var4) {
            return false;
         }
      }
   }

   public void super_computeScroll() {
      if (!this.e) {
         this.g.a();
      } else {
         View var1 = this.f.getView();

         try {
            com.tencent.smtt.utils.i.a((Object)var1, "super_computeScroll");
         } catch (Throwable var3) {
            var3.printStackTrace();
         }
      }

   }

   public int getScrollBarDefaultDelayBeforeFade() {
      return this.getView() == null ? 0 : this.getView().getScrollBarDefaultDelayBeforeFade();
   }

   public int getScrollBarFadeDuration() {
      return this.getView() == null ? 0 : this.getView().getScrollBarFadeDuration();
   }

   public int getScrollBarSize() {
      return this.getView() == null ? 0 : this.getView().getScrollBarSize();
   }

   public int getScrollBarStyle() {
      return this.getView() == null ? 0 : this.getView().getScrollBarStyle();
   }

   public void setVisibility(int var1) {
      super.setVisibility(var1);
      if (this.getView() != null) {
         this.getView().setVisibility(var1);
      }
   }

   public static void setDataDirectorySuffix(String var0) {
      if (VERSION.SDK_INT >= 28) {
         try {
            Class var1 = Class.forName("android.webkit.WebView");
            com.tencent.smtt.utils.i.a(var1, "setDataDirectorySuffix", new Class[]{String.class}, var0);
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }

      HashMap var3 = new HashMap();
      var3.put("data_directory_suffix", var0);
      QbSdk.initTbsSettings(var3);
   }

   private class a extends android.webkit.WebView {
      public a(Context var2) {
         this(var2, (AttributeSet)null);
      }

      public a(Context var2, AttributeSet var3) {
         super(WebView.this.d(var2), var3);
         if (!QbSdk.getIsSysWebViewForcedByOuter() || !TbsShareManager.isThirdPartyApp(var2)) {
            CookieSyncManager.createInstance(WebView.this.i).startSync();

            try {
               Class var4 = Class.forName("android.webkit.WebViewWorker");
               Method var5 = var4.getDeclaredMethod("getHandler");
               var5.setAccessible(true);
               Handler var6 = (Handler)var5.invoke((Object)null);
               Thread var7 = var6.getLooper().getThread();
               var7.setUncaughtExceptionHandler(new UncaughtSqliteExceptionHandler());
               WebView.mSysWebviewCreated = true;
            } catch (Exception var8) {
            }
         }

      }

      public void invalidate() {
         super.invalidate();
         if (WebView.this.mWebViewCallbackClient != null) {
            WebView.this.mWebViewCallbackClient.invalidate();
         }

      }

      public android.webkit.WebSettings getSettings() {
         try {
            return super.getSettings();
         } catch (Exception var2) {
            var2.printStackTrace();
            return null;
         }
      }

      protected void onScrollChanged(int var1, int var2, int var3, int var4) {
         if (WebView.this.mWebViewCallbackClient != null) {
            WebView.this.mWebViewCallbackClient.onScrollChanged(var1, var2, var3, var4, this);
         } else {
            super.onScrollChanged(var1, var2, var3, var4);
            WebView.this.onScrollChanged(var1, var2, var3, var4);
         }

      }

      public void a(int var1, int var2, int var3, int var4) {
         super.onScrollChanged(var1, var2, var3, var4);
      }

      public void computeScroll() {
         if (WebView.this.mWebViewCallbackClient != null) {
            WebView.this.mWebViewCallbackClient.computeScroll(this);
         } else {
            super.computeScroll();
         }

      }

      public void a() {
         super.computeScroll();
      }

      @SuppressLint({"ClickableViewAccessibility"})
      public boolean onTouchEvent(MotionEvent var1) {
         if (!this.hasFocus()) {
            this.requestFocus();
         }

         if (WebView.this.mWebViewCallbackClient != null) {
            return WebView.this.mWebViewCallbackClient.onTouchEvent(var1, this);
         } else {
            try {
               return super.onTouchEvent(var1);
            } catch (Exception var3) {
               var3.printStackTrace();
               return false;
            }
         }
      }

      public boolean a(MotionEvent var1) {
         return super.onTouchEvent(var1);
      }

      protected void dispatchDraw(Canvas var1) {
         try {
            super.dispatchDraw(var1);
            if (!WebView.w && WebView.v != null) {
               var1.save();
               var1.drawPaint(WebView.v);
               var1.restore();
            }
         } catch (Throwable var3) {
            var3.printStackTrace();
         }

      }

      @TargetApi(9)
      public boolean overScrollBy(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
         if (WebView.this.mWebViewCallbackClient != null) {
            return WebView.this.mWebViewCallbackClient.overScrollBy(var1, var2, var3, var4, var5, var6, var7, var8, var9, this);
         } else {
            return VERSION.SDK_INT >= 9 ? super.overScrollBy(var1, var2, var3, var4, var5, var6, var7, var8, var9) : false;
         }
      }

      @TargetApi(9)
      public boolean a(int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
         return VERSION.SDK_INT >= 9 ? super.overScrollBy(var1, var2, var3, var4, var5, var6, var7, var8, var9) : false;
      }

      @TargetApi(9)
      public void onOverScrolled(int var1, int var2, boolean var3, boolean var4) {
         if (WebView.this.mWebViewCallbackClient != null) {
            WebView.this.mWebViewCallbackClient.onOverScrolled(var1, var2, var3, var4, this);
         } else if (VERSION.SDK_INT >= 9) {
            super.onOverScrolled(var1, var2, var3, var4);
         }

      }

      @TargetApi(9)
      public void a(int var1, int var2, boolean var3, boolean var4) {
         if (VERSION.SDK_INT >= 9) {
            super.onOverScrolled(var1, var2, var3, var4);
         }

      }

      public boolean dispatchTouchEvent(MotionEvent var1) {
         return WebView.this.mWebViewCallbackClient != null ? WebView.this.mWebViewCallbackClient.dispatchTouchEvent(var1, this) : super.dispatchTouchEvent(var1);
      }

      public boolean b(MotionEvent var1) {
         return super.dispatchTouchEvent(var1);
      }

      public boolean onInterceptTouchEvent(MotionEvent var1) {
         return WebView.this.mWebViewCallbackClient != null ? WebView.this.mWebViewCallbackClient.onInterceptTouchEvent(var1, this) : super.onInterceptTouchEvent(var1);
      }

      public boolean c(MotionEvent var1) {
         return super.onInterceptTouchEvent(var1);
      }

      public void setOverScrollMode(int var1) {
         try {
            super.setOverScrollMode(var1);
         } catch (Exception var3) {
         }
      }
   }

   /** @deprecated */
   @Deprecated
   public interface PictureListener {
      /** @deprecated */
      @Deprecated
      void onNewPicture(WebView var1, Picture var2);
   }

   public static class HitTestResult {
      public static final int UNKNOWN_TYPE = 0;
      /** @deprecated */
      @Deprecated
      public static final int ANCHOR_TYPE = 1;
      public static final int PHONE_TYPE = 2;
      public static final int GEO_TYPE = 3;
      public static final int EMAIL_TYPE = 4;
      public static final int IMAGE_TYPE = 5;
      /** @deprecated */
      @Deprecated
      public static final int IMAGE_ANCHOR_TYPE = 6;
      public static final int SRC_ANCHOR_TYPE = 7;
      public static final int SRC_IMAGE_ANCHOR_TYPE = 8;
      public static final int EDIT_TEXT_TYPE = 9;
      private IX5WebViewBase.HitTestResult a;
      private android.webkit.WebView.HitTestResult b = null;

      public HitTestResult() {
         this.a = null;
         this.b = null;
      }

      public HitTestResult(IX5WebViewBase.HitTestResult var1) {
         this.a = var1;
         this.b = null;
      }

      public HitTestResult(android.webkit.WebView.HitTestResult var1) {
         this.a = null;
         this.b = var1;
      }

      public int getType() {
         int var1 = 0;
         if (this.a != null) {
            var1 = this.a.getType();
         } else if (this.b != null) {
            var1 = this.b.getType();
         }

         return var1;
      }

      public String getExtra() {
         String var1 = "";
         if (this.a != null) {
            var1 = this.a.getExtra();
         } else if (this.b != null) {
            var1 = this.b.getExtra();
         }

         return var1;
      }
   }

   public class WebViewTransport {
      private WebView b;

      public synchronized void setWebView(WebView var1) {
         this.b = var1;
      }

      public synchronized WebView getWebView() {
         return this.b;
      }
   }
}
