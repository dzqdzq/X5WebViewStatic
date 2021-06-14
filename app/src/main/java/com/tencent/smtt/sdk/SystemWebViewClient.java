package com.tencent.smtt.sdk;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslCertificate;
import android.net.http.SslError;
import android.os.Message;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.tencent.smtt.utils.TbsLog;
import java.io.InputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Map;

@SuppressLint({"NewApi", "Override"})
class SystemWebViewClient extends android.webkit.WebViewClient {
   private WebViewClient a;
   private WebView b;
   private static String c = null;

   SystemWebViewClient(WebView var1, WebViewClient var2) {
      this.b = var1;
      this.a = var2;
   }

   public void onLoadResource(android.webkit.WebView var1, String var2) {
      this.b.a(var1);
      this.a.onLoadResource(this.b, var2);
   }

   public boolean shouldOverrideUrlLoading(android.webkit.WebView var1, String var2) {
      if (var2 != null && !this.b.showDebugView(var2)) {
         this.b.a(var1);
         return this.a.shouldOverrideUrlLoading(this.b, var2);
      } else {
         return true;
      }
   }

   public boolean shouldOverrideUrlLoading(android.webkit.WebView var1, WebResourceRequest var2) {
      String var3 = null;
      if (var2 != null && var2.getUrl() != null) {
         var3 = var2.getUrl().toString();
      }

      if (var3 != null && !this.b.showDebugView(var3)) {
         this.b.a(var1);
         boolean var4 = false;
         if (VERSION.SDK_INT >= 24) {
            Object var5 = com.tencent.smtt.utils.i.a((Object)var2, "isRedirect");
            if (var5 instanceof Boolean) {
               var4 = (Boolean)var5;
            }
         }

         SystemWebViewClient.e var6 = new SystemWebViewClient.e(var2.getUrl().toString(), var2.isForMainFrame(), var4, var2.hasGesture(), var2.getMethod(), var2.getRequestHeaders());
         return this.a.shouldOverrideUrlLoading(this.b, (com.tencent.smtt.export.external.interfaces.WebResourceRequest)var6);
      } else {
         return true;
      }
   }

   @TargetApi(11)
   public WebResourceResponse shouldInterceptRequest(android.webkit.WebView var1, String var2) {
      if (VERSION.SDK_INT < 11) {
         return null;
      } else {
         com.tencent.smtt.export.external.interfaces.WebResourceResponse var3 = this.a.shouldInterceptRequest(this.b, var2);
         return var3 == null ? null : new WebResourceResponse(var3.getMimeType(), var3.getEncoding(), var3.getData());
      }
   }

   public WebResourceResponse shouldInterceptRequest(android.webkit.WebView var1, WebResourceRequest var2) {
      if (VERSION.SDK_INT < 21) {
         return null;
      } else if (var2 == null) {
         return null;
      } else {
         boolean var3 = false;
         if (VERSION.SDK_INT >= 24) {
            Object var4 = com.tencent.smtt.utils.i.a((Object)var2, "isRedirect");
            if (var4 instanceof Boolean) {
               var3 = (Boolean)var4;
            }
         }

         SystemWebViewClient.e var9 = new SystemWebViewClient.e(var2.getUrl().toString(), var2.isForMainFrame(), var3, var2.hasGesture(), var2.getMethod(), var2.getRequestHeaders());
         com.tencent.smtt.export.external.interfaces.WebResourceResponse var5 = this.a.shouldInterceptRequest(this.b, (com.tencent.smtt.export.external.interfaces.WebResourceRequest)var9);
         if (var5 == null) {
            return null;
         } else {
            WebResourceResponse var6 = new WebResourceResponse(var5.getMimeType(), var5.getEncoding(), var5.getData());
            var6.setResponseHeaders(var5.getResponseHeaders());
            int var7 = var5.getStatusCode();
            String var8 = var5.getReasonPhrase();
            if (var7 != var6.getStatusCode() || var8 != null && !var8.equals(var6.getReasonPhrase())) {
               var6.setStatusCodeAndReasonPhrase(var7, var8);
            }

            return var6;
         }
      }
   }

   public boolean shouldOverrideKeyEvent(android.webkit.WebView var1, KeyEvent var2) {
      this.b.a(var1);
      return this.a.shouldOverrideKeyEvent(this.b, var2);
   }

   public void onFormResubmission(android.webkit.WebView var1, Message var2, Message var3) {
      this.b.a(var1);
      this.a.onFormResubmission(this.b, var2, var3);
   }

   public void onPageFinished(android.webkit.WebView var1, String var2) {
      if (c == null) {
         com.tencent.smtt.utils.n var3 = com.tencent.smtt.utils.n.a();
         if (var3 != null) {
            var3.a(true);
            c = Boolean.toString(true);
         }
      }

      this.b.a(var1);
      ++this.b.a;
      this.a.onPageFinished(this.b, var2);
      if ("com.qzone".equals(var1.getContext().getApplicationInfo().packageName)) {
         this.b.a(var1.getContext());
      }

      TbsLog.app_extra("SystemWebViewClient", var1.getContext());
      WebView.c();
      if (!TbsShareManager.mHasQueryed && this.b.getContext() != null && TbsShareManager.isThirdPartyApp(this.b.getContext())) {
         TbsShareManager.mHasQueryed = true;
         (new Thread(new Runnable() {
            public void run() {
               if (!TbsShareManager.forceLoadX5FromTBSDemo(SystemWebViewClient.this.b.getContext()) && TbsDownloader.needDownload(SystemWebViewClient.this.b.getContext(), false)) {
                  TbsDownloader.startDownload(SystemWebViewClient.this.b.getContext());
               }

            }
         })).start();
      }

      if (this.b.getContext() != null && !TbsLogReport.getInstance(this.b.getContext()).getShouldUploadEventReport()) {
         TbsLogReport.getInstance(this.b.getContext()).setShouldUploadEventReport(true);
         TbsLogReport.getInstance(this.b.getContext()).dailyReport();
      }

   }

   public void onPageStarted(android.webkit.WebView var1, String var2, Bitmap var3) {
      this.b.a(var1);
      this.a.onPageStarted(this.b, var2, var3);
   }

   public void onReceivedError(android.webkit.WebView var1, int var2, String var3, String var4) {
      this.b.a(var1);
      this.a.onReceivedError(this.b, var2, var3, var4);
   }

   public void onReceivedError(android.webkit.WebView var1, WebResourceRequest var2, final WebResourceError var3) {
      this.b.a(var1);
      SystemWebViewClient.f var4 = null;
      if (var2 != null) {
         var4 = new SystemWebViewClient.f(var2);
      }

      com.tencent.smtt.export.external.interfaces.WebResourceError var5 = null;
      if (var3 != null) {
         var5 = new com.tencent.smtt.export.external.interfaces.WebResourceError() {
            public CharSequence getDescription() {
               return var3.getDescription();
            }

            public int getErrorCode() {
               return var3.getErrorCode();
            }
         };
      }

      this.a.onReceivedError(this.b, var4, var5);
   }

   public void onReceivedHttpError(android.webkit.WebView var1, WebResourceRequest var2, WebResourceResponse var3) {
      this.b.a(var1);
      SystemWebViewClient.f var4 = new SystemWebViewClient.f(var2);
      SystemWebViewClient.g var5 = new SystemWebViewClient.g(var3);
      this.a.onReceivedHttpError(this.b, var4, var5);
   }

   public void onReceivedHttpAuthRequest(android.webkit.WebView var1, HttpAuthHandler var2, String var3, String var4) {
      this.b.a(var1);
      this.a.onReceivedHttpAuthRequest(this.b, new SystemWebViewClient.b(var2), var3, var4);
   }

   public void doUpdateVisitedHistory(android.webkit.WebView var1, String var2, boolean var3) {
      this.b.a(var1);
      this.a.doUpdateVisitedHistory(this.b, var2, var3);
   }

   @TargetApi(12)
   public void onReceivedLoginRequest(android.webkit.WebView var1, String var2, String var3, String var4) {
      if (VERSION.SDK_INT >= 12) {
         this.b.a(var1);
         this.a.onReceivedLoginRequest(this.b, var2, var3, var4);
      }

   }

   @TargetApi(8)
   public void onReceivedSslError(android.webkit.WebView var1, SslErrorHandler var2, SslError var3) {
      if (VERSION.SDK_INT >= 8) {
         this.b.a(var1);
         this.a.onReceivedSslError(this.b, new SystemWebViewClient.c(var2), new SystemWebViewClient.d(var3));
      }

   }

   public void onReceivedClientCertRequest(android.webkit.WebView var1, ClientCertRequest var2) {
      if (VERSION.SDK_INT >= 21) {
         this.b.a(var1);
         SystemWebViewClient.a var3 = new SystemWebViewClient.a(var2);
         this.a.onReceivedClientCertRequest(this.b, var3);
      }

   }

   public void onScaleChanged(android.webkit.WebView var1, float var2, float var3) {
      this.b.a(var1);
      this.a.onScaleChanged(this.b, var2, var3);
   }

   public void onTooManyRedirects(android.webkit.WebView var1, Message var2, Message var3) {
      this.b.a(var1);
      this.a.onTooManyRedirects(this.b, var2, var3);
   }

   public void onUnhandledKeyEvent(android.webkit.WebView var1, KeyEvent var2) {
      this.b.a(var1);
      this.a.onUnhandledKeyEvent(this.b, var2);
   }

   public void onPageCommitVisible(android.webkit.WebView var1, String var2) {
      this.b.a(var1);
      this.a.onPageCommitVisible(this.b, var2);
   }

   public boolean onRenderProcessGone(android.webkit.WebView var1, final RenderProcessGoneDetail var2) {
      this.b.a(var1);
      return this.a.onRenderProcessGone(this.b, new WebViewClient.RenderProcessGoneDetail() {
         public boolean didCrash() {
            return var2.didCrash();
         }
      });
   }

   private static class g extends com.tencent.smtt.export.external.interfaces.WebResourceResponse {
      WebResourceResponse a;

      public g(WebResourceResponse var1) {
         this.a = var1;
      }

      public InputStream getData() {
         return this.a.getData();
      }

      public String getEncoding() {
         return this.a.getEncoding();
      }

      public String getMimeType() {
         return this.a.getMimeType();
      }

      public String getReasonPhrase() {
         return this.a.getReasonPhrase();
      }

      public Map<String, String> getResponseHeaders() {
         return this.a.getResponseHeaders();
      }

      public int getStatusCode() {
         return this.a.getStatusCode();
      }

      public void setData(InputStream var1) {
         this.a.setData(var1);
      }

      public void setEncoding(String var1) {
         this.a.setEncoding(var1);
      }

      public void setMimeType(String var1) {
         this.a.setMimeType(var1);
      }

      public void setResponseHeaders(Map<String, String> var1) {
         this.a.setResponseHeaders(var1);
      }

      public void setStatusCodeAndReasonPhrase(int var1, String var2) {
         this.a.setStatusCodeAndReasonPhrase(var1, var2);
      }
   }

   private static class f implements com.tencent.smtt.export.external.interfaces.WebResourceRequest {
      WebResourceRequest a;

      f(WebResourceRequest var1) {
         this.a = var1;
      }

      public String getMethod() {
         return this.a.getMethod();
      }

      public Map<String, String> getRequestHeaders() {
         return this.a.getRequestHeaders();
      }

      public Uri getUrl() {
         return this.a.getUrl();
      }

      public boolean hasGesture() {
         return this.a.hasGesture();
      }

      public boolean isForMainFrame() {
         return this.a.isForMainFrame();
      }

      public boolean isRedirect() {
         boolean var1 = false;
         if (VERSION.SDK_INT >= 24) {
            Object var2 = com.tencent.smtt.utils.i.a((Object)this.a, "isRedirect");
            if (var2 instanceof Boolean) {
               var1 = (Boolean)var2;
            }
         }

         return var1;
      }
   }

   private static class d implements com.tencent.smtt.export.external.interfaces.SslError {
      SslError a;

      d(SslError var1) {
         this.a = var1;
      }

      public SslCertificate getCertificate() {
         return this.a.getCertificate();
      }

      public boolean addError(int var1) {
         return this.a.addError(var1);
      }

      public boolean hasError(int var1) {
         return this.a.hasError(var1);
      }

      public int getPrimaryError() {
         return this.a.getPrimaryError();
      }

      public String getUrl() {
         return this.a.getUrl();
      }
   }

   private static class c implements com.tencent.smtt.export.external.interfaces.SslErrorHandler {
      SslErrorHandler a;

      c(SslErrorHandler var1) {
         this.a = var1;
      }

      public void proceed() {
         this.a.proceed();
      }

      public void cancel() {
         this.a.cancel();
      }
   }

   private static class b implements com.tencent.smtt.export.external.interfaces.HttpAuthHandler {
      private HttpAuthHandler a;

      b(HttpAuthHandler var1) {
         this.a = var1;
      }

      public void proceed(String var1, String var2) {
         this.a.proceed(var1, var2);
      }

      public void cancel() {
         this.a.cancel();
      }

      public boolean useHttpAuthUsernamePassword() {
         return this.a.useHttpAuthUsernamePassword();
      }
   }

   private static class a extends com.tencent.smtt.export.external.interfaces.ClientCertRequest {
      private ClientCertRequest a;

      public a(ClientCertRequest var1) {
         this.a = var1;
      }

      public void cancel() {
         this.a.cancel();
      }

      public String getHost() {
         return this.a.getHost();
      }

      public String[] getKeyTypes() {
         return this.a.getKeyTypes();
      }

      public int getPort() {
         return this.a.getPort();
      }

      public Principal[] getPrincipals() {
         return this.a.getPrincipals();
      }

      public void ignore() {
         this.a.ignore();
      }

      public void proceed(PrivateKey var1, X509Certificate[] var2) {
         this.a.proceed(var1, var2);
      }
   }

   static class e implements com.tencent.smtt.export.external.interfaces.WebResourceRequest {
      private String a;
      private boolean b;
      private boolean c;
      private boolean d;
      private String e;
      private Map<String, String> f;

      public e(String var1, boolean var2, boolean var3, boolean var4, String var5, Map<String, String> var6) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.e = var5;
         this.f = var6;
      }

      public Uri getUrl() {
         return Uri.parse(this.a);
      }

      public boolean isForMainFrame() {
         return this.b;
      }

      public boolean isRedirect() {
         return this.c;
      }

      public boolean hasGesture() {
         return this.d;
      }

      public String getMethod() {
         return this.e;
      }

      public Map<String, String> getRequestHeaders() {
         return this.f;
      }
   }
}
