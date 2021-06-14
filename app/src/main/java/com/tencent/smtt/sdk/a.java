package com.tencent.smtt.sdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class a {
   private static int b = 0;
   public static int a = 600;

   public static int a() {
      if (b > 0) {
         return b;
      } else {
         int var0 = 0;
         String var1 = "/proc/meminfo";
         String var2 = "";
         boolean var3 = true;
         BufferedReader var4 = null;

         try {
            FileReader var5 = new FileReader(var1);
            var4 = new BufferedReader(var5, 8192);

            while((var2 = var4.readLine()) != null) {
               int var20 = var2.indexOf("MemTotal:");
               if (-1 != var20) {
                  String var6 = var2.substring(var20 + "MemTotal:".length()).trim();
                  if (null != var6 && var6.length() != 0 && var6.contains("k")) {
                     var0 = Integer.parseInt(var6.substring(0, var6.indexOf("k")).trim()) / 1024;
                  }
                  break;
               }
            }
         } catch (IOException var17) {
            var17.printStackTrace();
         } catch (Throwable var18) {
            var18.printStackTrace();
         } finally {
            if (var4 != null) {
               try {
                  var4.close();
               } catch (IOException var16) {
                  var16.printStackTrace();
               }
            }

         }

         b = var0;
         return b;
      }
   }
}
