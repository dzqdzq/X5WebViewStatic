package com.tencent.smtt.sdk;

public class MimeTypeMap {
   private static MimeTypeMap a;

   private MimeTypeMap() {
   }

   public static String getFileExtensionFromUrl(String var0) {
      X5CoreEngine var1 = X5CoreEngine.a();
      return null != var1 && var1.b() ? var1.c().h(var0) : android.webkit.MimeTypeMap.getFileExtensionFromUrl(var0);
   }

   public boolean hasMimeType(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? var2.c().i(var1) : android.webkit.MimeTypeMap.getSingleton().hasMimeType(var1);
   }

   public String getMimeTypeFromExtension(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? var2.c().j(var1) : android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(var1);
   }

   public boolean hasExtension(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? var2.c().k(var1) : android.webkit.MimeTypeMap.getSingleton().hasExtension(var1);
   }

   public String getExtensionFromMimeType(String var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? var2.c().l(var1) : android.webkit.MimeTypeMap.getSingleton().getExtensionFromMimeType(var1);
   }

   public static synchronized MimeTypeMap getSingleton() {
      if (a == null) {
         a = new MimeTypeMap();
      }

      return a;
   }
}
