package com.tencent.smtt.sdk;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/** @deprecated */
@Deprecated
public final class CacheManager {
   /** @deprecated */
   @Deprecated
   public static File getCacheFileBaseDir() {
      X5CoreEngine var0 = X5CoreEngine.a();
      return null != var0 && var0.b() ? (File)var0.c().g() : (File)com.tencent.smtt.utils.i.a("android.webkit.CacheManager", "getCacheFileBaseDir");
   }

   /** @deprecated */
   @Deprecated
   public static boolean cacheDisabled() {
      X5CoreEngine var0 = X5CoreEngine.a();
      if (null != var0 && var0.b()) {
         return (Boolean)var0.c().c();
      } else {
         Object var1 = com.tencent.smtt.utils.i.a("android.webkit.CacheManager", "cacheDisabled");
         return var1 == null ? false : (Boolean)var1;
      }
   }

   /** @deprecated */
   public static Object getCacheFile(String var0, Map<String, String> var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         return var2.c().g();
      } else {
         try {
            return com.tencent.smtt.utils.i.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", new Class[]{String.class, Map.class}, var0, var1);
         } catch (Exception var4) {
            return null;
         }
      }
   }

   public static InputStream getCacheFile(String var0, boolean var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? var2.c().a(var0, var1) : null;
   }
}
