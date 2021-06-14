package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.nio.channels.FileLock;

class X5CoreEngine {
   private static X5CoreEngine a;
   private WebCoreProxy b;
   private boolean c;
   private boolean d;
   private static FileLock e = null;

   private X5CoreEngine() {
   }

   public static X5CoreEngine a() {
      if (a == null) {
         Class var0 = X5CoreEngine.class;
         synchronized(X5CoreEngine.class) {
            if (a == null) {
               a = new X5CoreEngine();
            }
         }
      }

      return a;
   }

   public boolean b() {
      return QbSdk.a ? false : this.c;
   }

   public WebCoreProxy a(boolean var1) {
      return var1 ? this.b : this.c();
   }

   public WebCoreProxy c() {
      return QbSdk.a ? null : this.b;
   }

   public synchronized void a(Context var1) {
      TbsLog.i("X5CoreEngine", "init #1");
      SDKEngine var2 = SDKEngine.a(true);
      var2.init(var1, false, false);
      StringBuilder var3 = new StringBuilder();
      Throwable var4 = null;
      TbsWizard var5 = var2.a();
      if (var2.b() && var5 != null) {
         if (!this.d) {
            this.b = new WebCoreProxy(var5.b());

            try {
               this.c = this.b.a();
               if (!this.c) {
                  var3.append("can not use X5 by x5corewizard return false");
               }
            } catch (NoSuchMethodException var10) {
               this.c = true;
            } catch (Throwable var11) {
               this.c = false;
               var4 = var11;
               var3.append("can not use x5 by throwable " + Log.getStackTraceString(var11));
            }

            if (this.c) {
               CookieManager.getInstance().a(var1, true, true);
               CookieManager.getInstance().a();
            }
         }
      } else {
         this.c = false;
         var3.append("can not use X5 by !tbs available");
      }

      TbsLog.i("X5CoreEngine", "init  mCanUseX5 is " + this.c);
      if (!this.c) {
         TbsLog.e("X5CoreEngine", "mCanUseX5 is false --> report");
         if (var2.b() && var5 != null && var4 == null) {
            try {
               DexLoader var6 = var5.b();
               Object var7 = null;
               if (var6 != null) {
                  var7 = var6.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0]);
               }

               if (var7 instanceof Throwable) {
                  Throwable var8 = (Throwable)var7;
                  var3.append("#" + var8.getMessage() + "; cause: " + var8.getCause() + "; th: " + var8);
               }

               if (var7 instanceof String) {
                  var3.append("failure detail:" + var7);
               }
            } catch (Throwable var9) {
               var9.printStackTrace();
            }

            if (var3 != null && var3.toString().contains("isPreloadX5Disabled:-10000")) {
               TbsCoreLoadStat.getInstance().a(var1, 408, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + var3.toString()));
            } else {
               TbsCoreLoadStat.getInstance().a(var1, 407, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + var3.toString()));
            }
         } else if (var2.b()) {
            TbsCoreLoadStat.getInstance().a(var1, 409, new Throwable("mCanUseX5=false, available true, reason: " + var4));
         } else {
            TbsCoreLoadStat.getInstance().a(var1, 410, new Throwable("mCanUseX5=false, available false, reason: " + var4));
         }
      } else {
         TbsLog.i("X5CoreEngine", "init  sTbsCoreLoadFileLock is " + e);
         if (e == null) {
            this.b(var1);
         }
      }

      this.d = true;
   }

   boolean d() {
      return this.d;
   }

   public FileLock b(Context var1) {
      TbsLog.i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
      if (e != null) {
         return e;
      } else {
         Class var2 = X5CoreEngine.class;
         synchronized(X5CoreEngine.class) {
            if (e == null) {
               e = FileUtil.e(var1);
               if (e == null) {
                  TbsLog.i("X5CoreEngine", "init -- sTbsCoreLoadFileLock failed!");
               } else {
                  TbsLog.i("X5CoreEngine", "init -- sTbsCoreLoadFileLock succeeded: " + e);
               }
            }
         }

         return e;
      }
   }
}
