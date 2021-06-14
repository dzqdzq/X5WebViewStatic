package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.IOException;
import java.util.UnknownFormatConversionException;

public class e {
   static int a = 5;
   static int b = 16;
   static char[] c;
   static String d;
   static long e;

   public static String a(Context var0, String var1) throws Exception {
      com.tencent.smtt.utils.c var2 = new com.tencent.smtt.utils.c(var1);
      var2.a(c);
      var2.a(c[a] == 1);
      var2.a(e);
      char[] var3 = a(var2);
      return a(new String(var3));
   }

   private static String a(String var0) {
      String[] var1 = var0.split(new String("\u0000"));
      int var2 = 0;

      String var3;
      String var4;
      do {
         if (var2 >= var1.length) {
            return "";
         }

         var3 = var1[var2++];
         var4 = var1[var2++];
      } while(!var3.equals(d));

      return var4;
   }

   public static char[] a(com.tencent.smtt.utils.c var0) throws IOException {
      char[] var21 = new char[4];
      char[] var22 = new char[4];
      var0.a(var21);
      if (var21[0] == 'o' && var21[1] == 'a' && var21[2] == 't') {
         var0.a(var22);
         int var1 = var0.b();
         int var2 = var0.b();
         int var3 = var0.b();
         int var4 = var0.b();
         int var5 = var0.b();
         int var6 = var0.b();
         int var7 = var0.b();
         int var8 = var0.b();
         if (var22[1] <= '4') {
            int var9 = var0.b();
            int var10 = var0.b();
            int var11 = var0.b();
         }

         int var12 = var0.b();
         int var13 = var0.b();
         int var14 = var0.b();
         int var15 = var0.b();
         int var16 = var0.b();
         int var17 = var0.b();
         int var18 = var0.b();
         int var19 = var0.b();
         char[] var20 = new char[var19];
         var0.a(var20);
         return var20;
      } else {
         throw new UnknownFormatConversionException(String.format("Invalid art magic %c%c%c", var21[0], var21[1], var21[2]));
      }
   }

   static {
      c = new char[b];
      d = "dex2oat-cmdline";
      e = 4096L;
   }
}
