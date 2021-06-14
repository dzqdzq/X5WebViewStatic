package com.tencent.smtt.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;

public class k implements Runnable {
   public static String a = "TBSFileLock";
   File b = null;
   RandomAccessFile c = null;
   FileLock d = null;
   long e = 0L;
   private static Object f = new Object();
   private static Object g = new Object();
   private static HashMap<k, Object> h = null;
   private static Handler i = null;

   public k(File var1, String var2) {
      this.b = new File(var1, "." + var2 + ".lock");
   }

   Handler a() {
      if (i == null) {
         Class var1 = k.class;
         synchronized(k.class) {
            if (i == null) {
               HandlerThread var2 = new HandlerThread("QBFileLock.Thread");
               var2.start();
               Looper var3 = var2.getLooper();
               i = new Handler(var3);
            }
         }
      }

      return i;
   }

   public synchronized void b() {
      try {
         this.c = new RandomAccessFile(this.b, "rw");
      } catch (Exception var7) {
         var7.printStackTrace();
      }

      if (this.c != null) {
         FileChannel var1 = this.c.getChannel();
         if (var1 != null) {
            if (this.e > 0L) {
               this.a().postDelayed(this, this.e);
            }

            FileLock var2 = null;
            long var3 = System.currentTimeMillis();

            while(true) {
               try {
                  var2 = var1.lock();
                  if (var2 != null) {
                     break;
                  }
               } catch (Exception var8) {
                  var8.printStackTrace();
                  Log.d(a, ">>> lock failed, sleep...");
               }

               try {
                  Thread.sleep(50L);
               } catch (InterruptedException var6) {
                  var6.printStackTrace();
               }

               if (Math.abs(System.currentTimeMillis() - var3) >= 1000L) {
                  Log.d(a, ">>> lock timeout, quit...");
                  break;
               }
            }

            this.d = var2;
            Log.d(a, ">>> lock [" + this.b.getName() + "] cost: " + (System.currentTimeMillis() - var3));
         }
      }

      if (this.d != null) {
         this.c();
      }

   }

   void c() {
      synchronized(g) {
         if (h == null) {
            h = new HashMap();
         }

         h.put(this, f);
      }
   }

   void d() {
      synchronized(g) {
         if (h != null) {
            h.remove(this);
         }
      }
   }

   public void e() {
      this.a(true);
   }

   public synchronized void a(boolean var1) {
      Log.d(a, ">>> release lock: " + this.b.getName());
      if (this.d != null) {
         try {
            this.d.release();
         } catch (Exception var4) {
            var4.printStackTrace();
         }

         this.d = null;
      }

      if (this.c != null) {
         try {
            this.c.close();
         } catch (Exception var3) {
            var3.printStackTrace();
         }

         this.c = null;
      }

      if (i != null && this.e > 0L) {
         i.removeCallbacks(this);
      }

      if (var1) {
         this.d();
      }

   }

   public void run() {
      Log.d(a, ">>> releaseLock on TimeOut");
      this.e();
   }
}
