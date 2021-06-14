package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public class m {
   private Context a = null;
   private File b = null;
   private static m c = null;
   private String d = "https://log.tbs.qq.com/ajax?c=pu&v=2&k=";
   private String e = "https://log.tbs.qq.com/ajax?c=pu&tk=";
   private String f = "https://log.tbs.qq.com/ajax?c=dl&k=";
   private String g = "https://cfg.imtt.qq.com/tbs?v=2&mk=";
   private String h = "https://log.tbs.qq.com/ajax?c=ul&v=2&k=";
   private String i = "https://mqqad.html5.qq.com/adjs";
   private String j = "https://log.tbs.qq.com/ajax?c=ucfu&k=";
   private String k = "https://tbsrecovery.imtt.qq.com/getconfig";

   public static synchronized m a(Context var0) {
      if (c == null) {
         c = new m(var0);
      }

      return c;
   }

   public static synchronized m a() {
      return c;
   }

   @TargetApi(11)
   private m(Context var1) {
      TbsLog.w("TbsCommonConfig", "TbsCommonConfig constructing...");
      this.a = var1.getApplicationContext();
      this.h();
   }

   private synchronized void h() {
      BufferedInputStream var1 = null;

      try {
         File var2 = this.i();
         if (var2 == null) {
            TbsLog.e("TbsCommonConfig", "Config file is null, default values will be applied");
            return;
         }

         FileInputStream var17 = new FileInputStream(var2);
         var1 = new BufferedInputStream(var17);
         Properties var4 = new Properties();
         var4.load(var1);
         String var5 = var4.getProperty("pv_post_url", "");
         if (!"".equals(var5)) {
            this.d = var5;
         }

         var5 = var4.getProperty("tbs_download_stat_post_url", "");
         if (!"".equals(var5)) {
            this.f = var5;
         }

         var5 = var4.getProperty("tbs_downloader_post_url", "");
         if (!"".equals(var5)) {
            this.g = var5;
         }

         var5 = var4.getProperty("tbs_log_post_url", "");
         if (!"".equals(var5)) {
            this.h = var5;
         }

         var5 = var4.getProperty("tips_url", "");
         if (!"".equals(var5)) {
            this.i = var5;
         }

         var5 = var4.getProperty("tbs_cmd_post_url", "");
         if (!"".equals(var5)) {
            this.j = var5;
         }

         var5 = var4.getProperty("tbs_emergency_post_url", "");
         if (!"".equals(var5)) {
            this.k = var5;
         }

         var5 = var4.getProperty("pv_post_url_tk", "");
         if (!"".equals(var5)) {
            this.e = var5;
         }
      } catch (Throwable var15) {
         StringWriter var3 = new StringWriter();
         var15.printStackTrace(new PrintWriter(var3));
         TbsLog.e("TbsCommonConfig", "exceptions occurred1:" + var3.toString());
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var14) {
               var14.printStackTrace();
            }
         }

      }

   }

   private File i() {
      File var1 = null;

      try {
         if (this.b == null) {
            String var2 = this.a.getApplicationContext().getApplicationInfo().packageName;
            if (!TextUtils.isEmpty(var2)) {
               boolean var7 = this.a.getPackageManager().checkPermission("android.permission.READ_EXTERNAL_STORAGE", var2) == 0;
               boolean var4 = this.a.getPackageManager().checkPermission("android.permission.WRITE_EXTERNAL_STORAGE", var2) == 0;
               if (!var7 && !var4) {
                  this.b = new File(FileUtil.a(this.a, 8));
               } else {
                  TbsLog.i("TbsCommonConfig", "no permission,use sdcard default folder");
                  this.b = new File(FileUtil.a(this.a, 5));
               }
            } else {
               this.b = new File(FileUtil.a(this.a, 8));
            }

            if (this.b == null || !this.b.isDirectory()) {
               return null;
            }
         }

         File var6 = new File(this.b, "tbsnet.conf");
         if (!var6.exists()) {
            TbsLog.e("TbsCommonConfig", "Get file(" + var6.getCanonicalPath() + ") failed!");
            return var1;
         }

         var1 = var6;
         TbsLog.w("TbsCommonConfig", "pathc:" + var6.getCanonicalPath());
      } catch (Throwable var5) {
         StringWriter var3 = new StringWriter();
         var5.printStackTrace(new PrintWriter(var3));
         TbsLog.e("TbsCommonConfig", "exceptions occurred2:" + var3.toString());
      }

      return var1;
   }

   public String b() {
      return this.d;
   }

   public String c() {
      return this.f;
   }

   public String d() {
      return this.g;
   }

   public String e() {
      return this.h;
   }

   public String f() {
      return this.e;
   }

   public String g() {
      return this.k;
   }
}
