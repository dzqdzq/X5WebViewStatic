package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;

public class WebSettings {
   public static final int LOAD_DEFAULT = -1;
   public static final int LOAD_NORMAL = 0;
   public static final int LOAD_CACHE_ELSE_NETWORK = 1;
   public static final int LOAD_NO_CACHE = 2;
   public static final int LOAD_CACHE_ONLY = 3;
   private IX5WebSettings a = null;
   private android.webkit.WebSettings b = null;
   private boolean c = false;

   WebSettings(IX5WebSettings var1) {
      this.a = var1;
      this.b = null;
      this.c = true;
   }

   WebSettings(android.webkit.WebSettings var1) {
      this.a = null;
      this.b = var1;
      this.c = false;
   }

   /** @deprecated */
   @Deprecated
   public void setNavDump(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setNavDump(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setNavDump", new Class[]{Boolean.TYPE}, var1);
      }

   }

   public synchronized int getMixedContentMode() {
      if (this.c && this.a != null) {
         try {
            return this.a.getMixedContentMode();
         } catch (Throwable var2) {
            var2.printStackTrace();
            return -1;
         }
      } else if (VERSION.SDK_INT < 21) {
         return -1;
      } else {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getMixedContentMode", new Class[0]);
         return var1 == null ? -1 : (Integer)var1;
      }
   }

   /** @deprecated */
   @Deprecated
   public boolean getNavDump() {
      if (this.c && this.a != null) {
         return this.a.getNavDump();
      } else if (!this.c && this.b != null) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getNavDump");
         return var1 == null ? false : (Boolean)var1;
      } else {
         return false;
      }
   }

   public void setSupportZoom(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setSupportZoom(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setSupportZoom(var1);
      }

   }

   public boolean supportZoom() {
      if (this.c && this.a != null) {
         return this.a.supportZoom();
      } else {
         return !this.c && this.b != null ? this.b.supportZoom() : false;
      }
   }

   @TargetApi(3)
   public void setBuiltInZoomControls(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setBuiltInZoomControls(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setBuiltInZoomControls(var1);
      }

   }

   @TargetApi(3)
   public boolean getBuiltInZoomControls() {
      if (this.c && this.a != null) {
         return this.a.getBuiltInZoomControls();
      } else {
         return !this.c && this.b != null ? this.b.getBuiltInZoomControls() : false;
      }
   }

   @TargetApi(11)
   public void setDisplayZoomControls(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setDisplayZoomControls(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT < 11) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setDisplayZoomControls", new Class[]{Boolean.TYPE}, var1);
      }

   }

   @TargetApi(11)
   public boolean getDisplayZoomControls() {
      if (this.c && this.a != null) {
         return this.a.getDisplayZoomControls();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getDisplayZoomControls");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @TargetApi(3)
   public void setAllowFileAccess(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setAllowFileAccess(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setAllowFileAccess(var1);
      }

   }

   @TargetApi(3)
   public boolean getAllowFileAccess() {
      if (this.c && this.a != null) {
         return this.a.getAllowFileAccess();
      } else {
         return !this.c && this.b != null ? this.b.getAllowFileAccess() : false;
      }
   }

   @TargetApi(11)
   public void setAllowContentAccess(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setAllowContentAccess(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT < 11) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setAllowContentAccess", new Class[]{Boolean.TYPE}, var1);
      }

   }

   @TargetApi(21)
   public void setMixedContentMode(int var1) {
      if (!this.c || this.a == null) {
         if (!this.c && this.b != null) {
            if (VERSION.SDK_INT >= 21) {
               com.tencent.smtt.utils.i.a((Object)this.b, "setMixedContentMode", new Class[]{Integer.TYPE}, var1);
            }
         }
      }
   }

   @TargetApi(11)
   public boolean getAllowContentAccess() {
      if (this.c && this.a != null) {
         return this.a.getAllowContentAccess();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getAllowContentAccess");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @TargetApi(7)
   public void setLoadWithOverviewMode(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setLoadWithOverviewMode(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setLoadWithOverviewMode(var1);
      }

   }

   @TargetApi(7)
   public boolean getLoadWithOverviewMode() {
      if (this.c && this.a != null) {
         return this.a.getLoadWithOverviewMode();
      } else {
         return !this.c && this.b != null ? this.b.getLoadWithOverviewMode() : false;
      }
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(11)
   public void setEnableSmoothTransition(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setEnableSmoothTransition(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT >= 11) {
            com.tencent.smtt.utils.i.a((Object)this.b, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, var1);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public boolean enableSmoothTransition() {
      if (this.c && this.a != null) {
         return this.a.enableSmoothTransition();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 11) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "enableSmoothTransition");
            return var1 == null ? false : (Boolean)var1;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setUseWebViewBackgroundForOverscrollBackground(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setUseWebViewBackgroundForOverscrollBackground(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public boolean getUseWebViewBackgroundForOverscrollBackground() {
      if (this.c && this.a != null) {
         return this.a.getUseWebViewBackgroundForOverscrollBackground();
      } else if (!this.c && this.b != null) {
         Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getUseWebViewBackgroundForOverscrollBackground");
         return var1 == null ? false : (Boolean)var1;
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setSaveFormData(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setSaveFormData(var1);
      } else if (!this.c && this.b != null) {
         this.b.setSaveFormData(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public boolean getSaveFormData() {
      if (this.c && this.a != null) {
         return this.a.getSaveFormData();
      } else {
         return !this.c && this.b != null ? this.b.getSaveFormData() : false;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setSavePassword(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setSavePassword(var1);
      } else if (!this.c && this.b != null) {
         this.b.setSavePassword(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public boolean getSavePassword() {
      if (this.c && this.a != null) {
         return this.a.getSavePassword();
      } else {
         return !this.c && this.b != null ? this.b.getSavePassword() : false;
      }
   }

   @TargetApi(14)
   public synchronized void setTextZoom(int var1) {
      if (this.c && this.a != null) {
         this.a.setTextZoom(var1);
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT < 14) {
            return;
         }

         try {
            this.b.setTextZoom(var1);
         } catch (Exception var3) {
            com.tencent.smtt.utils.i.a((Object)this.b, "setTextZoom", new Class[]{Integer.TYPE}, var1);
         }
      }

   }

   @TargetApi(14)
   public synchronized int getTextZoom() {
      if (this.c && this.a != null) {
         return this.a.getTextZoom();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT < 14) {
            return 0;
         } else {
            try {
               return this.b.getTextZoom();
            } catch (Exception var3) {
               Object var2 = com.tencent.smtt.utils.i.a((Object)this.b, "getTextZoom");
               return var2 == null ? 0 : (Integer)var2;
            }
         }
      } else {
         return 0;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setTextSize(WebSettings.TextSize var1) {
      if (this.c && this.a != null) {
         this.a.setTextSize(IX5WebSettings.TextSize.valueOf(var1.name()));
      } else if (!this.c && this.b != null) {
         this.b.setTextSize(android.webkit.WebSettings.TextSize.valueOf(var1.name()));
      }

   }

   /** @deprecated */
   @Deprecated
   public WebSettings.TextSize getTextSize() {
      if (this.c && this.a != null) {
         return WebSettings.TextSize.valueOf(this.a.getTextSize().name());
      } else {
         return !this.c && this.b != null ? WebSettings.TextSize.valueOf(this.b.getTextSize().name()) : null;
      }
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(7)
   public void setDefaultZoom(WebSettings.ZoomDensity var1) {
      if (this.c && this.a != null) {
         this.a.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(var1.name()));
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDefaultZoom(android.webkit.WebSettings.ZoomDensity.valueOf(var1.name()));
      }

   }

   /** @deprecated */
   @Deprecated
   @TargetApi(7)
   public WebSettings.ZoomDensity getDefaultZoom() {
      if (this.c && this.a != null) {
         return WebSettings.ZoomDensity.valueOf(this.a.getDefaultZoom().name());
      } else {
         return !this.c && this.b != null ? WebSettings.ZoomDensity.valueOf(this.b.getDefaultZoom().name()) : null;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setLightTouchEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setLightTouchEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setLightTouchEnabled(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public boolean getLightTouchEnabled() {
      if (this.c && this.a != null) {
         return this.a.getLightTouchEnabled();
      } else {
         return !this.c && this.b != null ? this.b.getLightTouchEnabled() : false;
      }
   }

   public void setUserAgent(String var1) {
      if (this.c && this.a != null) {
         this.a.setUserAgent(var1);
      } else if (!this.c && this.b != null) {
         this.b.setUserAgentString(var1);
      }

   }

   @TargetApi(3)
   public String getUserAgentString() {
      if (this.c && this.a != null) {
         return this.a.getUserAgentString();
      } else {
         return !this.c && this.b != null ? this.b.getUserAgentString() : "";
      }
   }

   @TargetApi(3)
   public void setUserAgentString(String var1) {
      if (this.c && this.a != null) {
         this.a.setUserAgentString(var1);
      } else if (!this.c && this.b != null) {
         this.b.setUserAgentString(var1);
      }

   }

   public void setUseWideViewPort(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setUseWideViewPort(var1);
      } else if (!this.c && this.b != null) {
         this.b.setUseWideViewPort(var1);
      }

   }

   public synchronized boolean getUseWideViewPort() {
      if (this.c && this.a != null) {
         return this.a.getUseWideViewPort();
      } else {
         return !this.c && this.b != null ? this.b.getUseWideViewPort() : false;
      }
   }

   public void setSupportMultipleWindows(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setSupportMultipleWindows(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setSupportMultipleWindows(var1);
      }

   }

   public synchronized boolean supportMultipleWindows() {
      if (this.c && this.a != null) {
         return this.a.supportMultipleWindows();
      } else {
         return !this.c && this.b != null ? this.b.supportMultipleWindows() : false;
      }
   }

   public void setLayoutAlgorithm(WebSettings.LayoutAlgorithm var1) {
      if (this.c && this.a != null) {
         this.a.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(var1.name()));
      } else if (!this.c && this.b != null) {
         this.b.setLayoutAlgorithm(android.webkit.WebSettings.LayoutAlgorithm.valueOf(var1.name()));
      }

   }

   public synchronized WebSettings.LayoutAlgorithm getLayoutAlgorithm() {
      if (this.c && this.a != null) {
         return WebSettings.LayoutAlgorithm.valueOf(this.a.getLayoutAlgorithm().name());
      } else {
         return !this.c && this.b != null ? WebSettings.LayoutAlgorithm.valueOf(this.b.getLayoutAlgorithm().name()) : null;
      }
   }

   public synchronized void setStandardFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setStandardFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setStandardFontFamily(var1);
      }

   }

   public synchronized String getStandardFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getStandardFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getStandardFontFamily() : "";
      }
   }

   public synchronized void setFixedFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setFixedFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setFixedFontFamily(var1);
      }

   }

   public synchronized String getFixedFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getFixedFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getFixedFontFamily() : "";
      }
   }

   public synchronized void setSansSerifFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setSansSerifFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setSansSerifFontFamily(var1);
      }

   }

   public synchronized String getSansSerifFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getSansSerifFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getSansSerifFontFamily() : "";
      }
   }

   public synchronized void setSerifFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setSerifFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setSerifFontFamily(var1);
      }

   }

   public synchronized String getSerifFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getSerifFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getSerifFontFamily() : "";
      }
   }

   public synchronized void setCursiveFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setCursiveFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setCursiveFontFamily(var1);
      }

   }

   public synchronized String getCursiveFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getCursiveFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getCursiveFontFamily() : "";
      }
   }

   public synchronized void setFantasyFontFamily(String var1) {
      if (this.c && this.a != null) {
         this.a.setFantasyFontFamily(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setFantasyFontFamily(var1);
      }

   }

   public synchronized String getFantasyFontFamily() {
      if (this.c && this.a != null) {
         return this.a.getFantasyFontFamily();
      } else {
         return !this.c && this.b != null ? this.b.getFantasyFontFamily() : "";
      }
   }

   public synchronized void setMinimumFontSize(int var1) {
      if (this.c && this.a != null) {
         this.a.setMinimumFontSize(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setMinimumFontSize(var1);
      }

   }

   public synchronized int getMinimumFontSize() {
      if (this.c && this.a != null) {
         return this.a.getMinimumFontSize();
      } else {
         return !this.c && this.b != null ? this.b.getMinimumFontSize() : 0;
      }
   }

   public synchronized void setMinimumLogicalFontSize(int var1) {
      if (this.c && this.a != null) {
         this.a.setMinimumLogicalFontSize(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setMinimumLogicalFontSize(var1);
      }

   }

   public synchronized int getMinimumLogicalFontSize() {
      if (this.c && this.a != null) {
         return this.a.getMinimumLogicalFontSize();
      } else {
         return !this.c && this.b != null ? this.b.getMinimumLogicalFontSize() : 0;
      }
   }

   public synchronized void setDefaultFontSize(int var1) {
      if (this.c && this.a != null) {
         this.a.setDefaultFontSize(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDefaultFontSize(var1);
      }

   }

   public synchronized int getDefaultFontSize() {
      if (this.c && this.a != null) {
         return this.a.getDefaultFontSize();
      } else {
         return !this.c && this.b != null ? this.b.getDefaultFontSize() : 0;
      }
   }

   public synchronized void setDefaultFixedFontSize(int var1) {
      if (this.c && this.a != null) {
         this.a.setDefaultFixedFontSize(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDefaultFixedFontSize(var1);
      }

   }

   public synchronized int getDefaultFixedFontSize() {
      if (this.c && this.a != null) {
         return this.a.getDefaultFixedFontSize();
      } else {
         return !this.c && this.b != null ? this.b.getDefaultFixedFontSize() : 0;
      }
   }

   public void setLoadsImagesAutomatically(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setLoadsImagesAutomatically(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setLoadsImagesAutomatically(var1);
      }

   }

   public synchronized boolean getLoadsImagesAutomatically() {
      if (this.c && this.a != null) {
         return this.a.getLoadsImagesAutomatically();
      } else {
         return !this.c && this.b != null ? this.b.getLoadsImagesAutomatically() : false;
      }
   }

   public void setBlockNetworkImage(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setBlockNetworkImage(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setBlockNetworkImage(var1);
      }

   }

   public synchronized boolean getBlockNetworkImage() {
      if (this.c && this.a != null) {
         return this.a.getBlockNetworkImage();
      } else {
         return !this.c && this.b != null ? this.b.getBlockNetworkImage() : false;
      }
   }

   @TargetApi(8)
   public synchronized void setBlockNetworkLoads(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setBlockNetworkLoads(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT >= 8) {
            this.b.setBlockNetworkLoads(var1);
         }
      }

   }

   @TargetApi(8)
   public synchronized boolean getBlockNetworkLoads() {
      if (this.c && this.a != null) {
         return this.a.getBlockNetworkLoads();
      } else if (!this.c && this.b != null) {
         return VERSION.SDK_INT >= 8 ? this.b.getBlockNetworkLoads() : false;
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   public void setJavaScriptEnabled(boolean var1) {
      try {
         if (this.c && this.a != null) {
            this.a.setJavaScriptEnabled(var1);
         } else {
            if (this.c || this.b == null) {
               return;
            }

            this.b.setJavaScriptEnabled(var1);
         }
      } catch (Throwable var3) {
         var3.printStackTrace();
      }

   }

   @TargetApi(16)
   public void setAllowUniversalAccessFromFileURLs(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setAllowUniversalAccessFromFileURLs(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, var1);
      }

   }

   @TargetApi(16)
   public void setAllowFileAccessFromFileURLs(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setAllowFileAccessFromFileURLs(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setPluginsEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setPluginsEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setPluginsEnabled", new Class[]{Boolean.TYPE}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   @TargetApi(8)
   public synchronized void setPluginState(WebSettings.PluginState var1) {
      if (this.c && this.a != null) {
         this.a.setPluginState(IX5WebSettings.PluginState.valueOf(var1.name()));
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT >= 8) {
            android.webkit.WebSettings.PluginState var2 = android.webkit.WebSettings.PluginState.valueOf(var1.name());
            com.tencent.smtt.utils.i.a((Object)this.b, "setPluginState", new Class[]{android.webkit.WebSettings.PluginState.class}, var2);
         }
      }

   }

   /** @deprecated */
   @Deprecated
   public synchronized void setPluginsPath(String var1) {
      if (this.c && this.a != null) {
         this.a.setPluginsPath(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setPluginsPath", new Class[]{String.class}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   @TargetApi(5)
   public void setDatabasePath(String var1) {
      if (this.c && this.a != null) {
         this.a.setDatabasePath(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setDatabasePath", new Class[]{String.class}, var1);
      }

   }

   /** @deprecated */
   @Deprecated
   @TargetApi(5)
   public void setGeolocationDatabasePath(String var1) {
      if (this.c && this.a != null) {
         this.a.setGeolocationDatabasePath(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setGeolocationDatabasePath(var1);
      }

   }

   @TargetApi(7)
   public void setAppCacheEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setAppCacheEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setAppCacheEnabled(var1);
      }

   }

   @TargetApi(7)
   public void setAppCachePath(String var1) {
      if (this.c && this.a != null) {
         this.a.setAppCachePath(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setAppCachePath(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   @TargetApi(7)
   public void setAppCacheMaxSize(long var1) {
      if (this.c && this.a != null) {
         this.a.setAppCacheMaxSize(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setAppCacheMaxSize(var1);
      }

   }

   @TargetApi(5)
   public void setDatabaseEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setDatabaseEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDatabaseEnabled(var1);
      }

   }

   @TargetApi(7)
   public void setDomStorageEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setDomStorageEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDomStorageEnabled(var1);
      }

   }

   @TargetApi(7)
   public synchronized boolean getDomStorageEnabled() {
      if (this.c && this.a != null) {
         return this.a.getDomStorageEnabled();
      } else {
         return !this.c && this.b != null ? this.b.getDomStorageEnabled() : false;
      }
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(5)
   public synchronized String getDatabasePath() {
      if (this.c && this.a != null) {
         return this.a.getDatabasePath();
      } else {
         return !this.c && this.b != null ? this.b.getDatabasePath() : "";
      }
   }

   @TargetApi(5)
   public synchronized boolean getDatabaseEnabled() {
      if (this.c && this.a != null) {
         return this.a.getDatabaseEnabled();
      } else {
         return !this.c && this.b != null ? this.b.getDatabaseEnabled() : false;
      }
   }

   @TargetApi(5)
   public void setGeolocationEnabled(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setGeolocationEnabled(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setGeolocationEnabled(var1);
      }

   }

   public synchronized boolean getJavaScriptEnabled() {
      if (this.c && this.a != null) {
         return this.a.getJavaScriptEnabled();
      } else {
         return !this.c && this.b != null ? this.b.getJavaScriptEnabled() : false;
      }
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(8)
   public synchronized boolean getPluginsEnabled() {
      if (this.c && this.a != null) {
         return this.a.getPluginsEnabled();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT <= 17) {
            Object var2 = com.tencent.smtt.utils.i.a((Object)this.b, "getPluginsEnabled");
            return var2 == null ? false : (Boolean)var2;
         } else if (VERSION.SDK_INT == 18) {
            android.webkit.WebSettings.PluginState var1 = this.b.getPluginState();
            return android.webkit.WebSettings.PluginState.ON == var1;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   /** @deprecated */
   @Deprecated
   @TargetApi(8)
   public synchronized WebSettings.PluginState getPluginState() {
      if (this.c && this.a != null) {
         return WebSettings.PluginState.valueOf(this.a.getPluginState().name());
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 8) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getPluginState");
            return var1 == null ? null : WebSettings.PluginState.valueOf(((android.webkit.WebSettings.PluginState)var1).name());
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   /** @deprecated */
   @Deprecated
   public synchronized String getPluginsPath() {
      if (this.c && this.a != null) {
         return this.a.getPluginsPath();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT <= 17) {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getPluginsPath");
            return var1 == null ? null : (String)var1;
         } else {
            return "";
         }
      } else {
         return "";
      }
   }

   public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setJavaScriptCanOpenWindowsAutomatically(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setJavaScriptCanOpenWindowsAutomatically(var1);
      }

   }

   public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
      if (this.c && this.a != null) {
         return this.a.getJavaScriptCanOpenWindowsAutomatically();
      } else {
         return !this.c && this.b != null ? this.b.getJavaScriptCanOpenWindowsAutomatically() : false;
      }
   }

   public synchronized void setDefaultTextEncodingName(String var1) {
      if (this.c && this.a != null) {
         this.a.setDefaultTextEncodingName(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setDefaultTextEncodingName(var1);
      }

   }

   public synchronized String getDefaultTextEncodingName() {
      if (this.c && this.a != null) {
         return this.a.getDefaultTextEncodingName();
      } else {
         return !this.c && this.b != null ? this.b.getDefaultTextEncodingName() : "";
      }
   }

   @TargetApi(17)
   public static String getDefaultUserAgent(Context var0) {
      if (X5CoreEngine.a().b()) {
         return X5CoreEngine.a().c().i(var0);
      } else if (VERSION.SDK_INT < 17) {
         return null;
      } else {
         Object var1 = com.tencent.smtt.utils.i.a(android.webkit.WebSettings.class, "getDefaultUserAgent", new Class[]{Context.class}, var0);
         return null == var1 ? null : (String)var1;
      }
   }

   @TargetApi(17)
   public boolean getMediaPlaybackRequiresUserGesture() {
      if (this.c && this.a != null) {
         return this.a.getMediaPlaybackRequiresUserGesture();
      } else if (!this.c && this.b != null) {
         if (VERSION.SDK_INT < 17) {
            return false;
         } else {
            Object var1 = com.tencent.smtt.utils.i.a((Object)this.b, "getMediaPlaybackRequiresUserGesture");
            return var1 == null ? false : (Boolean)var1;
         }
      } else {
         return false;
      }
   }

   @TargetApi(17)
   public void setMediaPlaybackRequiresUserGesture(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setMediaPlaybackRequiresUserGesture(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         if (VERSION.SDK_INT < 17) {
            return;
         }

         com.tencent.smtt.utils.i.a((Object)this.b, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, var1);
      }

   }

   public void setNeedInitialFocus(boolean var1) {
      if (this.c && this.a != null) {
         this.a.setNeedInitialFocus(var1);
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setNeedInitialFocus(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void setRenderPriority(WebSettings.RenderPriority var1) {
      if (this.c && this.a != null) {
         this.a.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(var1.name()));
      } else {
         if (this.c || this.b == null) {
            return;
         }

         this.b.setRenderPriority(android.webkit.WebSettings.RenderPriority.valueOf(var1.name()));
      }

   }

   public void setCacheMode(int var1) {
      if (this.c && this.a != null) {
         this.a.setCacheMode(var1);
      } else if (!this.c && this.b != null) {
         this.b.setCacheMode(var1);
      }

   }

   public int getCacheMode() {
      if (this.c && this.a != null) {
         return this.a.getCacheMode();
      } else {
         return !this.c && this.b != null ? this.b.getCacheMode() : 0;
      }
   }

   public void setSafeBrowsingEnabled(boolean var1) {
      if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 26) {
            this.b.setSafeBrowsingEnabled(var1);
         }
      } else if (this.c && this.a != null) {
         try {
            this.a.setSafeBrowsingEnabled(var1);
         } catch (Throwable var3) {
            var3.printStackTrace();
         }
      }

   }

   public boolean getSafeBrowsingEnabled() {
      if (!this.c && this.b != null) {
         if (VERSION.SDK_INT >= 26) {
            return this.b.getSafeBrowsingEnabled();
         }
      } else if (this.c && this.a != null) {
         try {
            return this.a.getSafeBrowsingEnabled();
         } catch (Throwable var2) {
            var2.printStackTrace();
         }
      }

      return false;
   }

   public static enum ZoomDensity {
      FAR(150),
      MEDIUM(100),
      CLOSE(75);

      int value;

      private ZoomDensity(int var3) {
         this.value = var3;
      }
   }

   public static enum TextSize {
      SMALLEST(50),
      SMALLER(75),
      NORMAL(100),
      LARGER(125),
      LARGEST(150);

      int value;

      private TextSize(int var3) {
         this.value = var3;
      }
   }

   public static enum RenderPriority {
      NORMAL,
      HIGH,
      LOW;
   }

   public static enum PluginState {
      ON,
      ON_DEMAND,
      OFF;
   }

   public static enum LayoutAlgorithm {
      NORMAL,
      SINGLE_COLUMN,
      NARROW_COLUMNS;
   }
}
