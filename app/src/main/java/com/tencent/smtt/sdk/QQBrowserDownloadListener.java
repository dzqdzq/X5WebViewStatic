package com.tencent.smtt.sdk;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import com.tencent.smtt.sdk.stat.MttLoader;
import java.util.HashMap;

class QQBrowserDownloadListener implements com.tencent.smtt.export.external.interfaces.DownloadListener {
   private DownloadListener a;
   private WebView b;

   public QQBrowserDownloadListener(WebView var1, DownloadListener var2, boolean var3) {
      this.a = var2;
      this.b = var1;
   }

   public void onDownloadStart(String var1, String var2, byte[] var3, String var4, String var5, String var6, long var7, String var9, String var10) {
      if (this.a == null) {
         if (QbSdk.canOpenMimeFileType(this.b.getContext(), var6)) {
            Intent var11 = new Intent("com.tencent.QQBrowser.action.sdk.document");
            var11.setFlags(268435456);
            var11.putExtra("key_reader_sdk_url", var1);
            var11.putExtra("key_reader_sdk_type", 1);
            var11.setData(Uri.parse(var1));
            this.b.getContext().startActivity(var11);
         } else {
            ApplicationInfo var12 = this.b.getContext().getApplicationInfo();
            if (var12 == null || !"com.tencent.mm".equals(var12.packageName)) {
               MttLoader.loadUrl(this.b.getContext(), var1, (HashMap)null, (WebView)null);
            }
         }
      } else {
         this.a.onDownloadStart(var1, var4, var5, var6, var7);
      }

   }

   public void onDownloadVideo(String var1, long var2, int var4) {
   }

   public void onDownloadStart(String var1, String var2, String var3, String var4, long var5) {
      this.onDownloadStart(var1, (String)null, (byte[])null, var2, var3, var4, var5, (String)null, (String)null);
   }
}
