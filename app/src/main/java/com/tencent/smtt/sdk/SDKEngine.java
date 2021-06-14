package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

class SDKEngine {
   private TbsWizard c = null;
   private TbsWizard d = null;
   private static SDKEngine e = null;
   private boolean f = false;
   private boolean g = false;
   private static int h = 0;
   static int a = 0;
   static boolean b = false;
   private static int i = 3;
   private File j = null;
   private static String k = null;

   private SDKEngine() {
   }

   public static SDKEngine a(boolean var0) {
      if (e == null && var0) {
         Class var1 = SDKEngine.class;
         synchronized(SDKEngine.class) {
            if (e == null) {
               e = new SDKEngine();
            }
         }
      }

      return e;
   }

   public synchronized void init(Context var1, boolean var2, boolean var3) {
      TbsLog.addLog(999, (String)null);
      TbsLog.initIfNeed(var1);
      TbsLog.i("SDKEngine", "init -- context: " + var1 + ", isPreIniting: " + var3);
      ++a;
      TbsCoreLoadStat.getInstance().a();
      o.a().b(var1, a == 1);
      o.a().l(var1);
      TbsShareManager.forceToLoadX5ForThirdApp(var1, true);
      boolean var4 = QbSdk.a(var1, var2, var3);
      boolean var5 = VERSION.SDK_INT >= 7;
      boolean var6 = var4 && var5;
      if (var6) {
         long var7 = System.currentTimeMillis();
         var6 = o.a().isTbsCoreLegal(var1, d());
         TbsLog.i("SDKEngine", "isTbsCoreLegal: " + var6 + "; cost: " + (System.currentTimeMillis() - var7));
      }

      if (var6) {
         if (this.f) {
            return;
         }

         try {
            File var13 = null;
            File var8 = null;
            Context var9 = null;
            boolean var10;
            if (TbsShareManager.isThirdPartyApp(var1)) {
               TbsLog.addLog(995, (String)null);
               var10 = TbsShareManager.j(var1);
               if (!var10) {
                  this.f = false;
                  QbSdk.a(var1, "SDKEngine::useSystemWebView by error_host_unavailable");
                  return;
               }

               var13 = new File(TbsShareManager.c(var1));
               var8 = o.a().r(var1);
               var9 = TbsShareManager.e(var1);
               if (var8 == null) {
                  this.f = false;
                  QbSdk.a(var1, "SDKEngine::useSystemWebView by error_tbs_core_dexopt_dir null!");
                  return;
               }
            } else {
               TbsLog.addLog(996, (String)null);
               var13 = o.a().r(var1);
               var8 = var13;
               var10 = h == 25436 || h == 25437;
               if (var10) {
                  var9 = var1.getApplicationContext();
               } else {
                  var9 = var1;
               }

               if (var13 == null) {
                  this.f = false;
                  QbSdk.a(var1, "SDKEngine::useSystemWebView by tbs_core_share_dir null!");
                  return;
               }
            }

            String[] var16 = QbSdk.getDexLoaderFileList(var1, var9, var13.getAbsolutePath());

            for(int var11 = 0; var11 < var16.length; ++var11) {
            }

            String var17 = null;
            if (TbsShareManager.getHostCorePathAppDefined() != null) {
               var17 = TbsShareManager.getHostCorePathAppDefined();
            } else {
               var17 = var8.getAbsolutePath();
            }

            TbsLog.i("SDKEngine", "SDKEngine init optDir is " + var17);
            if (this.d != null) {
               this.c = this.d;
               this.c.continueInit(var1, var9, var13.getAbsolutePath(), var17, var16, QbSdk.d);
            } else {
               this.c = new TbsWizard(var1, var9, var13.getAbsolutePath(), var17, var16, QbSdk.d);
            }

            this.f = true;
         } catch (Throwable var12) {
            TbsLog.e("SDKEngine", "useSystemWebView by exception: " + var12);
            if (var12 == null) {
               TbsCoreLoadStat.getInstance().a(var1, 326);
            } else {
               TbsCoreLoadStat.getInstance().a(var1, 327, var12);
            }

            this.f = false;
            QbSdk.a(var1, "SDKEngine::useSystemWebView by exception: " + var12);
         }
      } else {
         String var14 = "can_load_x5=" + var4 + "; is_compatible=" + var5;
         TbsLog.e("SDKEngine", "SDKEngine.init canLoadTbs=false; failure: " + var14);
         if (!QbSdk.a || !this.f) {
            this.f = false;
            TbsCoreLoadStat.getInstance().a(var1, 405, new Throwable(var14));
         }
      }

      EmergencyManager var15 = EmergencyManager.a();
      var15.a(var1);
      this.j = o.t(var1);
      this.g = true;
   }

   public TbsWizard a() {
      return this.f ? this.c : null;
   }

   public boolean b() {
      return this.f;
   }

   TbsWizard c() {
      return this.c;
   }

   public static int d() {
      return h;
   }

   static void a(int var0) {
      h = var0;
   }

   public String e() {
      return this.c != null && !QbSdk.a ? this.c.a() : "system webview get nothing...";
   }

   boolean f() {
      if (b) {
         if (k == null) {
            return false;
         }

         int var1 = this.i();
         if (var1 == 0) {
            this.b(1);
         } else {
            if (var1 + 1 > i) {
               return false;
            }

            this.b(var1 + 1);
         }
      }

      return b;
   }

   boolean b(boolean var1) {
      b = var1;
      return var1;
   }

   boolean g() {
      return this.g;
   }

   void a(String var1) {
      k = var1;
   }

   private int i() {
      FileInputStream var1 = null;
      BufferedInputStream var2 = null;

      try {
         File var3 = new File(this.j, "count.prop");
         if (var3.exists()) {
            var1 = new FileInputStream(var3);
            var2 = new BufferedInputStream(var1);
            Properties var20 = new Properties();
            var20.load(var2);
            String var5 = var20.getProperty(k, "1");
            int var6 = Integer.valueOf(var5);
            int var7 = var6;
            return var7;
         }

         byte var4 = 0;
         return var4;
      } catch (Exception var18) {
         var18.printStackTrace();
      } finally {
         if (var2 != null) {
            try {
               var2.close();
            } catch (IOException var17) {
               var17.printStackTrace();
            }
         }

      }

      return 0;
   }

   private void b(int var1) {
      String var2 = String.valueOf(var1);
      Properties var3 = new Properties();
      var3.setProperty(k, var2);

      try {
         var3.store(new FileOutputStream(new File(this.j, "count.prop")), (String)null);
      } catch (FileNotFoundException var5) {
         var5.printStackTrace();
      } catch (IOException var6) {
         var6.printStackTrace();
      }

   }

   public boolean h() {
      return QbSdk.useSoftWare();
   }
}
