package com.tencent.smtt.sdk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import com.tencent.smtt.export.external.interfaces.ClientCertRequest;
import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebViewBase;
import com.tencent.smtt.export.external.interfaces.IX5WebViewClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.export.external.proxy.X5ProxyWebViewClient;
import com.tencent.smtt.utils.TbsLog;

class X5WebViewClient extends X5ProxyWebViewClient {
   private WebViewClient a;
   private WebView b;
   private static String c = null;

   public X5WebViewClient(IX5WebViewClient var1, WebView var2, WebViewClient var3) {
      super(var1);
      this.b = var2;
      this.a = var3;
      this.a.mX5Client = this;
   }

   public void doUpdateVisitedHistory(IX5WebViewBase var1, String var2, boolean var3) {
      this.b.a(var1);
      this.a.doUpdateVisitedHistory(this.b, var2, var3);
   }

   public void onFormResubmission(IX5WebViewBase var1, Message var2, Message var3) {
      this.b.a(var1);
      this.a.onFormResubmission(this.b, var2, var3);
   }

   public void onLoadResource(IX5WebViewBase var1, String var2) {
      this.b.a(var1);
      this.a.onLoadResource(this.b, var2);
   }

   public void onPageFinished(IX5WebViewBase var1, int var2, int var3, String var4) {
      if (c == null) {
         com.tencent.smtt.utils.n var5 = com.tencent.smtt.utils.n.a();
         if (var5 != null) {
            var5.a(false);
            c = Boolean.toString(false);
         }
      }

      this.b.a(var1);
      ++this.b.a;
      this.a.onPageFinished(this.b, var4);
      if ("com.qzone".equals(var1.getView().getContext().getApplicationInfo().packageName)) {
         this.b.a(var1.getView().getContext());
      }

      TbsLog.app_extra("SmttWebViewClient", var1.getView().getContext());

      try {
         super.onPageFinished(var1, var2, var3, var4);
      } catch (Exception var6) {
      }

      WebView.c();
      if (!TbsShareManager.mHasQueryed && this.b.getContext() != null && TbsShareManager.isThirdPartyApp(this.b.getContext())) {
         TbsShareManager.mHasQueryed = true;
         (new Thread(new Runnable() {
            public void run() {
               if (!TbsShareManager.forceLoadX5FromTBSDemo(X5WebViewClient.this.b.getContext()) && TbsDownloader.needDownload(X5WebViewClient.this.b.getContext(), false)) {
                  TbsDownloader.startDownload(X5WebViewClient.this.b.getContext());
               }

            }
         })).start();
      }

      if (this.b.getContext() != null && !TbsLogReport.getInstance(this.b.getContext()).getShouldUploadEventReport()) {
         TbsLogReport.getInstance(this.b.getContext()).setShouldUploadEventReport(true);
         TbsLogReport.getInstance(this.b.getContext()).dailyReport();
      }

   }

   public void onPageStarted(IX5WebViewBase var1, int var2, int var3, String var4, Bitmap var5) {
      this.b.a(var1);
      this.a.onPageStarted(this.b, var4, var5);
   }

   public void onReceivedError(IX5WebViewBase var1, WebResourceRequest var2, WebResourceError var3) {
      this.b.a(var1);
      this.a.onReceivedError(this.b, var2, var3);
   }

   public void onReceivedError(IX5WebViewBase var1, int var2, String var3, String var4) {
      if (var2 < -15) {
         if (var2 != -17) {
            return;
         }

         var2 = -1;
      }

      this.b.a(var1);
      this.a.onReceivedError(this.b, var2, var3, var4);
   }

   public void onReceivedHttpError(IX5WebViewBase var1, WebResourceRequest var2, WebResourceResponse var3) {
      this.b.a(var1);
      this.a.onReceivedHttpError(this.b, var2, var3);
   }

   public void onReceivedHttpAuthRequest(IX5WebViewBase var1, HttpAuthHandler var2, String var3, String var4) {
      this.b.a(var1);
      this.a.onReceivedHttpAuthRequest(this.b, var2, var3, var4);
   }

   public void onReceivedSslError(IX5WebViewBase var1, SslErrorHandler var2, SslError var3) {
      this.b.a(var1);
      this.a.onReceivedSslError(this.b, var2, var3);
   }

   public void onReceivedClientCertRequest(IX5WebViewBase var1, ClientCertRequest var2) {
      this.b.a(var1);
      this.a.onReceivedClientCertRequest(this.b, var2);
   }

   public void onScaleChanged(IX5WebViewBase var1, float var2, float var3) {
      this.b.a(var1);
      this.a.onScaleChanged(this.b, var2, var3);
   }

   public void onUnhandledKeyEvent(IX5WebViewBase var1, KeyEvent var2) {
      this.b.a(var1);
      this.a.onUnhandledKeyEvent(this.b, var2);
   }

   public boolean shouldOverrideKeyEvent(IX5WebViewBase var1, KeyEvent var2) {
      this.b.a(var1);
      return this.a.shouldOverrideKeyEvent(this.b, var2);
   }

   public void a(String var1) {
      Intent var2 = new Intent("android.intent.action.DIAL", Uri.parse(var1));
      var2.addFlags(268435456);

      try {
         if (this.b.getContext() != null) {
            this.b.getContext().startActivity(var2);
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public boolean shouldOverrideUrlLoading(IX5WebViewBase var1, String var2) {
      if (var2 != null && !this.b.showDebugView(var2)) {
         this.b.a(var1);
         boolean var3 = this.a.shouldOverrideUrlLoading(this.b, var2);
         if (!var3) {
            if (var2.startsWith("wtai://wp/mc;")) {
               Intent var4 = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + var2.substring("wtai://wp/mc;".length())));
               this.b.getContext().startActivity(var4);
               return true;
            }

            if (var2.startsWith("tel:")) {
               this.a(var2);
               return true;
            }
         }

         return var3;
      } else {
         return true;
      }
   }

   public void onTooManyRedirects(IX5WebViewBase var1, Message var2, Message var3) {
      this.b.a(var1);
      this.a.onTooManyRedirects(this.b, var2, var3);
   }

   public boolean shouldOverrideUrlLoading(IX5WebViewBase var1, WebResourceRequest var2) {
      String var3 = null;
      if (var2 != null && var2.getUrl() != null) {
         var3 = var2.getUrl().toString();
      }

      if (var3 != null && !this.b.showDebugView(var3)) {
         this.b.a(var1);
         boolean var4 = this.a.shouldOverrideUrlLoading(this.b, var2);
         if (!var4) {
            if (var3.startsWith("wtai://wp/mc;")) {
               Intent var5 = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + var3.substring("wtai://wp/mc;".length())));
               this.b.getContext().startActivity(var5);
               return true;
            }

            if (var3.startsWith("tel:")) {
               this.a(var3);
               return true;
            }
         }

         return var4;
      } else {
         return true;
      }
   }

   public WebResourceResponse shouldInterceptRequest(IX5WebViewBase var1, String var2) {
      this.b.a(var1);
      return this.a.shouldInterceptRequest(this.b, var2);
   }

   public WebResourceResponse shouldInterceptRequest(IX5WebViewBase var1, WebResourceRequest var2) {
      this.b.a(var1);
      return this.a.shouldInterceptRequest(this.b, var2);
   }

   public WebResourceResponse shouldInterceptRequest(IX5WebViewBase var1, WebResourceRequest var2, Bundle var3) {
      this.b.a(var1);
      return this.a.shouldInterceptRequest(this.b, var2, var3);
   }

   public void onReceivedLoginRequest(IX5WebViewBase var1, String var2, String var3, String var4) {
      this.b.a(var1);
      this.a.onReceivedLoginRequest(this.b, var2, var3, var4);
   }

   public void a(WebView var1, String var2, Bitmap var3) {
      super.onPageStarted(this.b.b(), 0, 0, var2, var3);
   }

   public void onDetectedBlankScreen(IX5WebViewBase var1, String var2, int var3) {
      this.b.a(var1);
      this.a.onDetectedBlankScreen(var2, var3);
   }

   public void onPageFinished(IX5WebViewBase var1, String var2) {
      this.onPageFinished(var1, 0, 0, var2);
   }

   public void onPageStarted(IX5WebViewBase var1, String var2, Bitmap var3) {
      this.onPageStarted(var1, 0, 0, var2, var3);
   }

   public void countPVContentCacheCallBack(String var1) {
      ++this.b.a;
   }

   public void onPageCommitVisible(IX5WebViewBase var1, String var2) {
      this.b.a(var1);
      this.a.onPageCommitVisible(this.b, var2);
   }
}
