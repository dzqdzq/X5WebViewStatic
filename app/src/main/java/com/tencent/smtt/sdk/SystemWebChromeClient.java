package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Build.VERSION;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView.WebViewTransport;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;

class SystemWebChromeClient extends android.webkit.WebChromeClient {
   private WebView b;
   WebChromeClient a;

   SystemWebChromeClient(WebView var1, WebChromeClient var2) {
      this.b = var1;
      this.a = var2;
   }

   @TargetApi(7)
   public Bitmap getDefaultVideoPoster() {
      Bitmap var1 = this.a.getDefaultVideoPoster();

      try {
         if (var1 == null && VERSION.SDK_INT >= 24) {
            Bitmap var2 = BitmapFactory.decodeResource(this.b.getResources(), 17301540);
            return var2;
         }
      } catch (Exception var3) {
      }

      return var1;
   }

   @TargetApi(7)
   public View getVideoLoadingProgressView() {
      return this.a.getVideoLoadingProgressView();
   }

   public void getVisitedHistory(final android.webkit.ValueCallback<String[]> var1) {
      this.a.getVisitedHistory(new ValueCallback<String[]>() {
         public void a(String[] var1x) {
            var1.onReceiveValue(var1x);
         }

         // $FF: synthetic method
         public void onReceiveValue(String[] var1x) {
            this.a((String[])var1x);
         }
      });
   }

   public void onCloseWindow(android.webkit.WebView var1) {
      this.b.a(var1);
      this.a.onCloseWindow(this.b);
   }

   public boolean onConsoleMessage(ConsoleMessage var1) {
      return this.a.onConsoleMessage(new SystemWebChromeClient.a(var1));
   }

   public void onConsoleMessage(String var1, int var2, String var3) {
      this.a.onConsoleMessage(new SystemWebChromeClient.a(var1, var3, var2));
   }

   public boolean onCreateWindow(android.webkit.WebView var1, boolean var2, boolean var3, final Message var4) {
      final WebView.WebViewTransport var5 = this.b.new WebViewTransport();
      Handler var6 = var4.getTarget();
      Message var7 = Message.obtain(var6, new Runnable() {
         public void run() {
            WebView var1 = var5.getWebView();
            if (var1 != null) {
               WebViewTransport var2 = (WebViewTransport)var4.obj;
               var2.setWebView(var1.a());
            }

            var4.sendToTarget();
         }
      });
      var7.obj = var5;
      return this.a.onCreateWindow(this.b, var2, var3, var7);
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(5)
   public void onExceededDatabaseQuota(String var1, String var2, long var3, long var5, long var7, QuotaUpdater var9) {
      this.a.onExceededDatabaseQuota(var1, var2, var3, var5, var7, new SystemWebChromeClient.e(var9));
   }

   @TargetApi(5)
   public void onGeolocationPermissionsHidePrompt() {
      this.a.onGeolocationPermissionsHidePrompt();
   }

   @TargetApi(5)
   public void onGeolocationPermissionsShowPrompt(String var1, Callback var2) {
      this.a.onGeolocationPermissionsShowPrompt(var1, new SystemWebChromeClient.b(var2));
   }

   public boolean onJsAlert(android.webkit.WebView var1, String var2, String var3, JsResult var4) {
      this.b.a(var1);
      return this.a.onJsAlert(this.b, var2, var3, new SystemWebChromeClient.d(var4));
   }

   public boolean onJsBeforeUnload(android.webkit.WebView var1, String var2, String var3, JsResult var4) {
      this.b.a(var1);
      return this.a.onJsBeforeUnload(this.b, var2, var3, new SystemWebChromeClient.d(var4));
   }

   public boolean onJsConfirm(android.webkit.WebView var1, String var2, String var3, JsResult var4) {
      this.b.a(var1);
      return this.a.onJsConfirm(this.b, var2, var3, new SystemWebChromeClient.d(var4));
   }

   public boolean onJsPrompt(android.webkit.WebView var1, String var2, String var3, String var4, JsPromptResult var5) {
      this.b.a(var1);
      return this.a.onJsPrompt(this.b, var2, var3, var4, new SystemWebChromeClient.c(var5));
   }

   @TargetApi(7)
   public boolean onJsTimeout() {
      return this.a.onJsTimeout();
   }

   public void onProgressChanged(android.webkit.WebView var1, int var2) {
      this.b.a(var1);
      this.a.onProgressChanged(this.b, var2);
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(7)
   public void onReachedMaxAppCacheSize(long var1, long var3, QuotaUpdater var5) {
      this.a.onReachedMaxAppCacheSize(var1, var3, new SystemWebChromeClient.e(var5));
   }

   public void onReceivedIcon(android.webkit.WebView var1, Bitmap var2) {
      this.b.a(var1);
      this.a.onReceivedIcon(this.b, var2);
   }

   public void onReceivedTitle(android.webkit.WebView var1, String var2) {
      this.b.a(var1);
      this.a.onReceivedTitle(this.b, var2);
   }

   @TargetApi(7)
   public void onReceivedTouchIconUrl(android.webkit.WebView var1, String var2, boolean var3) {
      this.b.a(var1);
      this.a.onReceivedTouchIconUrl(this.b, var2, var3);
   }

   public void onRequestFocus(android.webkit.WebView var1) {
      this.b.a(var1);
      this.a.onRequestFocus(this.b);
   }

   public void openFileChooser(android.webkit.ValueCallback<Uri> var1) {
      this.openFileChooser(var1, (String)null, (String)null);
   }

   public void openFileChooser(android.webkit.ValueCallback<Uri> var1, String var2) {
      this.openFileChooser(var1, var2, (String)null);
   }

   public void openFileChooser(final android.webkit.ValueCallback<Uri> var1, String var2, String var3) {
      this.a.openFileChooser(new ValueCallback<Uri>() {
         public void a(Uri var1x) {
            var1.onReceiveValue(var1x);
         }

         // $FF: synthetic method
         public void onReceiveValue(Uri var1x) {
            this.a((Uri)var1x);
         }
      }, var2, var3);
   }

   public boolean onShowFileChooser(android.webkit.WebView var1, final android.webkit.ValueCallback<Uri[]> var2, final FileChooserParams var3) {
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
      this.b.a(var1);
      return this.a.onShowFileChooser(this.b, var5, var6);
   }

   public void setupAutoFill(Message var1) {
   }

   public void onPermissionRequest(final PermissionRequest var1) {
      this.a.onPermissionRequest(new com.tencent.smtt.export.external.interfaces.PermissionRequest() {
         public Uri getOrigin() {
            return VERSION.SDK_INT >= 21 ? var1.getOrigin() : null;
         }

         public String[] getResources() {
            return VERSION.SDK_INT >= 21 ? var1.getResources() : new String[0];
         }

         public void grant(String[] var1x) {
            if (VERSION.SDK_INT >= 21) {
               var1.grant(var1x);
            }

         }

         public void deny() {
            if (VERSION.SDK_INT >= 21) {
               var1.deny();
            }

         }
      });
   }

   public void onPermissionRequestCanceled(final PermissionRequest var1) {
      this.a.onPermissionRequestCanceled(new com.tencent.smtt.export.external.interfaces.PermissionRequest() {
         public Uri getOrigin() {
            return VERSION.SDK_INT >= 21 ? var1.getOrigin() : null;
         }

         public String[] getResources() {
            return VERSION.SDK_INT >= 21 ? var1.getResources() : new String[0];
         }

         public void grant(String[] var1x) {
            if (VERSION.SDK_INT >= 21) {
               var1.grant(var1x);
            }

         }

         public void deny() {
            if (VERSION.SDK_INT >= 21) {
               var1.deny();
            }

         }
      });
   }

   class b implements GeolocationPermissionsCallback {
      Callback a;

      b(Callback var2) {
         this.a = var2;
      }

      public void invoke(String var1, boolean var2, boolean var3) {
         this.a.invoke(var1, var2, var3);
      }
   }

   class e implements WebStorage.QuotaUpdater {
      QuotaUpdater a;

      e(QuotaUpdater var2) {
         this.a = var2;
      }

      public void updateQuota(long var1) {
         this.a.updateQuota(var1);
      }
   }

   private static class a implements com.tencent.smtt.export.external.interfaces.ConsoleMessage {
      private com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel a;
      private String b;
      private String c;
      private int d;

      a(ConsoleMessage var1) {
         this.a = com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel.valueOf(var1.messageLevel().name());
         this.b = var1.message();
         this.c = var1.sourceId();
         this.d = var1.lineNumber();
      }

      a(String var1, String var2, int var3) {
         this.a = com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel.LOG;
         this.b = var1;
         this.c = var2;
         this.d = var3;
      }

      public com.tencent.smtt.export.external.interfaces.ConsoleMessage.MessageLevel messageLevel() {
         return this.a;
      }

      public String message() {
         return this.b;
      }

      public String sourceId() {
         return this.c;
      }

      public int lineNumber() {
         return this.d;
      }
   }

   private class c implements com.tencent.smtt.export.external.interfaces.JsPromptResult {
      JsPromptResult a;

      c(JsPromptResult var2) {
         this.a = var2;
      }

      public void cancel() {
         this.a.cancel();
      }

      public void confirm() {
         this.a.confirm();
      }

      public void confirm(String var1) {
         this.a.confirm(var1);
      }
   }

   private class d implements com.tencent.smtt.export.external.interfaces.JsResult {
      JsResult a;

      d(JsResult var2) {
         this.a = var2;
      }

      public void cancel() {
         this.a.cancel();
      }

      public void confirm() {
         this.a.confirm();
      }
   }
}
