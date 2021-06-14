package com.tencent.smtt.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.QuotaUpdater;
import com.tencent.smtt.export.external.proxy.X5ProxyWebChromeClient;

class X5WebChromeClient extends X5ProxyWebChromeClient {
   private WebView a;
   private WebChromeClient b;

   public X5WebChromeClient(IX5WebChromeClient var1, WebView var2, WebChromeClient var3) {
      super(var1);
      this.a = var2;
      this.b = var3;
   }

   public void getVisitedHistory(android.webkit.ValueCallback<String[]> var1) {
   }

   public void onExceededDatabaseQuota(String var1, String var2, long var3, long var5, long var7, QuotaUpdater var9) {
      this.b.onExceededDatabaseQuota(var1, var2, var3, var5, var7, new X5WebChromeClient.a(var9));
   }

   public Bitmap getDefaultVideoPoster() {
      return this.b.getDefaultVideoPoster();
   }

   public void onCloseWindow(IX5WebViewBase var1) {
      this.a.a(var1);
      this.b.onCloseWindow(this.a);
   }

   public void onConsoleMessage(String var1, int var2, String var3) {
   }

   public boolean onConsoleMessage(ConsoleMessage var1) {
      return this.b.onConsoleMessage(var1);
   }

   public boolean onCreateWindow(IX5WebViewBase var1, boolean var2, boolean var3, final Message var4) {
      final WebView.WebViewTransport var5 = this.a.new WebViewTransport();
      Handler var6 = var4.getTarget();
      Message var7 = Message.obtain(var6, new Runnable() {
         public void run() {
            WebView var1 = var5.getWebView();
            if (var1 != null) {
               IX5WebViewBase.WebViewTransport var2 = (IX5WebViewBase.WebViewTransport)var4.obj;
               var2.setWebView(var1.b());
            }

            var4.sendToTarget();
         }
      });
      var7.obj = var5;
      return this.b.onCreateWindow(this.a, var2, var3, var7);
   }

   public void onGeolocationPermissionsHidePrompt() {
      this.b.onGeolocationPermissionsHidePrompt();
   }

   public void onGeolocationPermissionsShowPrompt(String var1, GeolocationPermissionsCallback var2) {
      this.b.onGeolocationPermissionsShowPrompt(var1, var2);
   }

   public void onHideCustomView() {
      this.b.onHideCustomView();
   }

   public boolean onJsAlert(IX5WebViewBase var1, String var2, String var3, JsResult var4) {
      this.a.a(var1);
      return this.b.onJsAlert(this.a, var2, var3, var4);
   }

   public boolean onJsConfirm(IX5WebViewBase var1, String var2, String var3, JsResult var4) {
      this.a.a(var1);
      return this.b.onJsConfirm(this.a, var2, var3, var4);
   }

   public boolean onJsPrompt(IX5WebViewBase var1, String var2, String var3, String var4, JsPromptResult var5) {
      this.a.a(var1);
      return this.b.onJsPrompt(this.a, var2, var3, var4, var5);
   }

   public boolean onJsBeforeUnload(IX5WebViewBase var1, String var2, String var3, JsResult var4) {
      this.a.a(var1);
      return this.b.onJsBeforeUnload(this.a, var2, var3, var4);
   }

   public boolean onJsTimeout() {
      return this.b.onJsTimeout();
   }

   public void onProgressChanged(IX5WebViewBase var1, int var2) {
      this.a.a(var1);
      this.b.onProgressChanged(this.a, var2);
   }

   public void onReachedMaxAppCacheSize(long var1, long var3, QuotaUpdater var5) {
      this.b.onReachedMaxAppCacheSize(var1, var3, new X5WebChromeClient.a(var5));
   }

   public void onReceivedIcon(IX5WebViewBase var1, Bitmap var2) {
      this.a.a(var1);
      this.b.onReceivedIcon(this.a, var2);
   }

   public void onReceivedTouchIconUrl(IX5WebViewBase var1, String var2, boolean var3) {
      this.a.a(var1);
      this.b.onReceivedTouchIconUrl(this.a, var2, var3);
   }

   public void onReceivedTitle(IX5WebViewBase var1, String var2) {
      this.a.a(var1);
      this.b.onReceivedTitle(this.a, var2);
   }

   public void onRequestFocus(IX5WebViewBase var1) {
      this.a.a(var1);
      this.b.onRequestFocus(this.a);
   }

   public void onShowCustomView(View var1, IX5WebChromeClient.CustomViewCallback var2) {
      this.b.onShowCustomView(var1, var2);
   }

   public void onShowCustomView(View var1, int var2, IX5WebChromeClient.CustomViewCallback var3) {
      this.b.onShowCustomView(var1, var2, var3);
   }

   public void openFileChooser(final android.webkit.ValueCallback<Uri[]> var1, String var2, String var3, boolean var4) {
      this.b.openFileChooser(new ValueCallback<Uri>() {
         public void a(Uri var1x) {
            var1.onReceiveValue(new Uri[]{var1x});
         }

         // $FF: synthetic method
         public void onReceiveValue(Uri var1x) {
            this.a((Uri)var1x);
         }
      }, var2, var3);
   }

   public boolean onShowFileChooser(IX5WebViewBase var1, final android.webkit.ValueCallback<Uri[]> var2, final IX5WebChromeClient.FileChooserParams var3) {
      ValueCallback var5 = new ValueCallback<Uri[]>() {
         public void a(Uri[] var1) {
            var2.onReceiveValue(var1);
         }

         // $FF: synthetic method
         public void onReceiveValue(Uri[] var1) {
            this.a((Uri[])var1);
         }
      };
      WebChromeClient.FileChooserParams var6 = new WebChromeClient.FileChooserParams() {
         public int getMode() {
            return var3.getMode();
         }

         public String[] getAcceptTypes() {
            return var3.getAcceptTypes();
         }

         public boolean isCaptureEnabled() {
            return var3.isCaptureEnabled();
         }

         public CharSequence getTitle() {
            return var3.getTitle();
         }

         public String getFilenameHint() {
            return var3.getFilenameHint();
         }

         public Intent createIntent() {
            return var3.createIntent();
         }
      };
      this.a.a(var1);
      return this.b.onShowFileChooser(this.a, var5, var6);
   }

   class a implements WebStorage.QuotaUpdater {
      QuotaUpdater a;

      a(QuotaUpdater var2) {
         this.a = var2;
      }

      public void updateQuota(long var1) {
         this.a.updateQuota(var1);
      }
   }
}
