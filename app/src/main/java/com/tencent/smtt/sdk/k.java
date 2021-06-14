package com.tencent.smtt.sdk;

import android.os.Build.VERSION;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerClient;
import com.tencent.smtt.export.external.interfaces.ServiceWorkerWebSettings;

public class k extends ServiceWorkerController {
   public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
      if (VERSION.SDK_INT < 24) {
         return null;
      } else {
         final android.webkit.ServiceWorkerWebSettings var1 = android.webkit.ServiceWorkerController.getInstance().getServiceWorkerWebSettings();
         return new ServiceWorkerWebSettings() {
            public void setCacheMode(int var1x) {
               if (VERSION.SDK_INT >= 24) {
                  var1.setCacheMode(var1x);
               }

            }

            public int getCacheMode() {
               return VERSION.SDK_INT >= 24 ? var1.getCacheMode() : -1;
            }

            public void setAllowContentAccess(boolean var1x) {
               if (VERSION.SDK_INT >= 24) {
                  var1.setAllowContentAccess(var1x);
               }

            }

            public boolean getAllowContentAccess() {
               return VERSION.SDK_INT >= 24 ? var1.getAllowContentAccess() : false;
            }

            public void setAllowFileAccess(boolean var1x) {
               if (VERSION.SDK_INT >= 24) {
                  var1.setAllowContentAccess(var1x);
               }

            }

            public boolean getAllowFileAccess() {
               return VERSION.SDK_INT >= 24 ? var1.getAllowFileAccess() : false;
            }

            public void setBlockNetworkLoads(boolean var1x) {
               if (VERSION.SDK_INT >= 24) {
                  var1.setBlockNetworkLoads(var1x);
               }

            }

            public boolean getBlockNetworkLoads() {
               return VERSION.SDK_INT >= 24 ? var1.getBlockNetworkLoads() : false;
            }
         };
      }
   }

   public void setServiceWorkerClient(final ServiceWorkerClient var1) {
      if (VERSION.SDK_INT >= 24) {
         android.webkit.ServiceWorkerController.getInstance().setServiceWorkerClient(new android.webkit.ServiceWorkerClient() {
            public WebResourceResponse shouldInterceptRequest(WebResourceRequest var1x) {
               SystemWebViewClient.e var2 = new SystemWebViewClient.e(var1x.getUrl().toString(), var1x.isForMainFrame(), var1x.isRedirect(), var1x.hasGesture(), var1x.getMethod(), var1x.getRequestHeaders());
               com.tencent.smtt.export.external.interfaces.WebResourceResponse var3 = var1.shouldInterceptRequest(var2);
               if (var3 == null) {
                  return null;
               } else {
                  WebResourceResponse var4 = new WebResourceResponse(var3.getMimeType(), var3.getEncoding(), var3.getData());
                  var4.setResponseHeaders(var3.getResponseHeaders());
                  int var5 = var3.getStatusCode();
                  String var6 = var3.getReasonPhrase();
                  if (var5 != var4.getStatusCode() || var6 != null && !var6.equals(var4.getReasonPhrase())) {
                     var4.setStatusCodeAndReasonPhrase(var5, var6);
                  }

                  return var4;
               }
            }
         });
      }

   }
}
