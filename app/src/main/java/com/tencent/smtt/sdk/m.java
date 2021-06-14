package com.tencent.smtt.sdk;

import android.content.Context;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class m {
   private static m a = null;
   private static Context b = null;

   private m() {
   }

   static m a(Context var0) {
      if (a == null) {
         Class var1 = m.class;
         synchronized(m.class) {
            if (a == null) {
               a = new m();
            }
         }
      }

      b = var0.getApplicationContext();
      return a;
   }

   void a(int var1, int var2) {
      this.a("copy_core_ver", var1);
      this.a("copy_status", var2);
   }

   void b(int var1, int var2) {
      this.a("tpatch_ver", var1);
      this.a("tpatch_status", var2);
   }

   File a() {
      o.a();
      File var1 = o.t(b);
      File var2 = new File(var1, "tbscoreinstall.txt");
      if (var2 != null && !var2.exists()) {
         try {
            var2.createNewFile();
         } catch (IOException var4) {
            var4.printStackTrace();
            return null;
         }
      }

      return var2;
   }

   private Properties e() {
      FileInputStream var1 = null;
      BufferedInputStream var2 = null;
      Properties var3 = null;

      try {
         File var4 = this.a();
         var3 = new Properties();
         if (var4 != null && var3 != null) {
            var1 = new FileInputStream(var4);
            var2 = new BufferedInputStream(var1);
            var3.load(var2);
         }

         Properties var5 = var3;
         return var5;
      } catch (Exception var15) {
         var15.printStackTrace();
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var14) {
               var14.printStackTrace();
            }
         }

      }

      return var3;
   }

   int b() {
      return this.c("install_core_ver");
   }

   int c() {
      return this.b("install_status");
   }

   void a(int var1) {
      this.a("dexopt_retry_num", var1);
   }

   void b(int var1) {
      this.a("unzip_retry_num", var1);
   }

   void a(String var1) {
      this.a("install_apk_path", var1);
   }

   void c(int var1, int var2) {
      this.a("install_core_ver", var1);
      this.a("install_status", var2);
   }

   void c(int var1) {
      this.a("incrupdate_status", var1);
   }

   int d() {
      return this.b("incrupdate_status");
   }

   void d(int var1) {
      this.a("unlzma_status", var1);
   }

   int b(String var1) {
      Properties var2 = this.e();
      return var2 != null && var2.getProperty(var1) != null ? Integer.parseInt(var2.getProperty(var1)) : -1;
   }

   void a(String var1, String var2) {
      FileOutputStream var3 = null;

      try {
         Properties var4 = this.e();
         if (var4 != null) {
            var4.setProperty(var1, var2);
            File var5 = this.a();
            if (var5 != null) {
               var3 = new FileOutputStream(var5);
               var4.store(var3, "update " + var1 + " and status!");
            }
         }
      } catch (Exception var14) {
         var14.printStackTrace();
      } finally {
         if (var3 != null) {
            try {
               var3.close();
            } catch (IOException var13) {
               var13.printStackTrace();
            }
         }

      }

   }

   void a(String var1, int var2) {
      this.a(var1, String.valueOf(var2));
   }

   int c(String var1) {
      Properties var2 = this.e();
      return var2 != null && var2.getProperty(var1) != null ? Integer.parseInt(var2.getProperty(var1)) : 0;
   }

   String d(String var1) {
      Properties var2 = this.e();
      return var2 != null && var2.getProperty(var1) != null ? var2.getProperty(var1) : null;
   }
}
