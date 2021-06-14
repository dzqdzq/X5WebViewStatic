package com.tencent.smtt.sdk;

public final class URLUtil {
   public static String guessUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().m(var0) : android.webkit.URLUtil.guessUrl(var0);
   }

   public static String composeSearchUrl(String var0, String var1, String var2) {
      X5CoreEngine var3 = X5CoreEngine.a();
      return null != var3 && var3.b() ? var3.c().a(var0, var1, var2) : android.webkit.URLUtil.composeSearchUrl(var0, var1, var2);
   }

   public static byte[] decode(byte[] var0) throws IllegalArgumentException {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().a(var0) : android.webkit.URLUtil.decode(var0);
   }

   public static boolean isAssetUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().n(var0) : android.webkit.URLUtil.isAssetUrl(var0);
   }

   /** @deprecated */
   @Deprecated
   public static boolean isCookielessProxyUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().o(var0) : android.webkit.URLUtil.isCookielessProxyUrl(var0);
   }

   public static boolean isFileUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().p(var0) : android.webkit.URLUtil.isFileUrl(var0);
   }

   public static boolean isAboutUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().q(var0) : android.webkit.URLUtil.isAboutUrl(var0);
   }

   public static boolean isDataUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().r(var0) : android.webkit.URLUtil.isDataUrl(var0);
   }

   public static boolean isJavaScriptUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().s(var0) : android.webkit.URLUtil.isJavaScriptUrl(var0);
   }

   public static boolean isHttpUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().t(var0) : android.webkit.URLUtil.isHttpUrl(var0);
   }

   public static boolean isHttpsUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().u(var0) : android.webkit.URLUtil.isHttpsUrl(var0);
   }

   public static boolean isNetworkUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().v(var0) : android.webkit.URLUtil.isNetworkUrl(var0);
   }

   public static boolean isContentUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().w(var0) : android.webkit.URLUtil.isContentUrl(var0);
   }

   public static boolean isValidUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().x(var0) : android.webkit.URLUtil.isValidUrl(var0);
   }

   public static String stripAnchor(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().y(var0) : android.webkit.URLUtil.stripAnchor(var0);
   }

   public static final String guessFileName(String var0, String var1, String var2) {
      X5CoreEngine var3 = X5CoreEngine.a();
      return null != var3 && var3.b() ? var3.c().b(var0, var1, var2) : android.webkit.URLUtil.guessFileName(var0, var1, var2);
   }
}
