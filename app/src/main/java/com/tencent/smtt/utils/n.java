package com.tencent.smtt.utils;

import android.content.Context;
import com.tencent.smtt.sdk.QbSdk;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class n {
   private Context b = null;
   private File c = null;
   public boolean a = false;
   private boolean d = false;
   private static n e = null;
   private File f = null;

   public static synchronized n a(Context var0) {
      if (e == null) {
         e = new n(var0);
      }

      return e;
   }

   private n(Context var1) {
      this.b = var1.getApplicationContext();
      this.b();
   }

   public static synchronized n a() {
      return e;
   }

   public synchronized void b() {
      FileInputStream var1 = null;
      BufferedInputStream var2 = null;

      try {
         if (this.f == null) {
            this.f = this.d();
         }

         if (this.f != null) {
            var1 = new FileInputStream(this.f);
            var2 = new BufferedInputStream(var1);
            Properties var3 = new Properties();
            var3.load(var2);
            String var4 = var3.getProperty("setting_forceUseSystemWebview", "");
            if (!"".equals(var4)) {
               this.a = Boolean.parseBoolean(var4);
            }

            return;
         }
      } catch (Throwable var14) {
         var14.printStackTrace();
         return;
      } finally {
         try {
            if (var2 != null) {
               var2.close();
            }
         } catch (Exception var13) {
            var13.printStackTrace();
         }

      }

   }

   private File d() {
      File var1 = null;

      try {
         File var2;
         if (this.c == null) {
            var2 = QbSdk.getTbsFolderDir(this.b);
            this.c = new File(var2, "core_private");
            if (this.c == null || !this.c.isDirectory()) {
               return null;
            }
         }

         var2 = new File(this.c, "debug.conf");
         if (var2 != null && !var2.exists()) {
            var2.createNewFile();
         }

         var1 = var2;
      } catch (Throwable var3) {
         var3.printStackTrace();
      }

      return var1;
   }

   public void a(boolean var1) {
      this.d = var1;
      this.c();
   }

   public void c() {
      FileOutputStream var1 = null;
      FileInputStream var2 = null;
      BufferedInputStream var3 = null;
      BufferedOutputStream var4 = null;

      try {
         File var5 = this.d();
         if (var5 == null) {
            return;
         }

         var2 = new FileInputStream(var5);
         var3 = new BufferedInputStream(var2);
         Properties var6 = new Properties();
         var6.load(var3);
         var6.setProperty("setting_forceUseSystemWebview", Boolean.toString(this.a));
         var6.setProperty("result_systemWebviewForceUsed", Boolean.toString(this.d));
         var1 = new FileOutputStream(var5);
         var4 = new BufferedOutputStream(var1);
         var6.store(var4, (String)null);
      } catch (Throwable var21) {
         var21.printStackTrace();
      } finally {
         try {
            var3.close();
         } catch (Exception var20) {
            var20.printStackTrace();
         }

         try {
            var4.close();
         } catch (Exception var19) {
            var19.printStackTrace();
         }

      }

   }
}
