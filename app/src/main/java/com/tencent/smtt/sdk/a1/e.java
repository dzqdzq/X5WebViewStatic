package com.tencent.smtt.sdk.a1;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.smtt.utils.TbsLog;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class e {
   private static String a = "EmergencyManager";
   private String b;
   private String c;
   private String d;
   private Handler e;
   private static final Object f = new Object();
   private static HandlerThread g;
   private static Handler h;

   public e(Context var1, String var2, String var3) {
      this(var1, var2, var3, "POST");
   }

   public e(Context var1, String var2, String var3, String var4) {
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = new Handler(var1.getMainLooper());
   }

   private static Handler b() {
      synchronized(f) {
         if (h == null) {
            g = new HandlerThread("HttpThread");
            g.start();
            h = new Handler(g.getLooper());
         }

         return h;
      }
   }

   public void a(final e.a var1) {
      b().post(new Runnable() {
         public void run() {
            final String var1x = e.this.a(e.this.b);
            if (var1x != null) {
               e.this.e.post(new Runnable() {
                  public void run() {
                     if (var1 != null) {
                        var1.a(var1x);
                     }

                  }
               });
            } else {
               TbsLog.e(com.tencent.smtt.sdk.a1.e.a, "Unexpected result for an empty http response: " + e.this.b);
            }

         }
      });
   }

   public String a(String var1) {
      TbsLog.e(a, "Request url: " + this.b + ",params: " + this.c);

      try {
         URL var2 = new URL(var1.trim());
         HttpURLConnection var3 = (HttpURLConnection)var2.openConnection();
         var3.setRequestMethod(this.d);
         var3.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
         var3.setRequestProperty("Content-Length", this.c.length() + "");
         var3.setDoOutput(true);
         OutputStream var4 = var3.getOutputStream();
         var4.write(this.c.getBytes());
         int var5 = var3.getResponseCode();
         if (200 == var5) {
            InputStream var6 = var3.getInputStream();
            ByteArrayOutputStream var7 = new ByteArrayOutputStream();
            byte[] var8 = new byte[1024];
            boolean var9 = false;

            int var11;
            while(-1 != (var11 = var6.read(var8))) {
               var7.write(var8, 0, var11);
               var7.flush();
            }

            return var7.toString("utf-8");
         }

         TbsLog.e(a, "Bad http request, code: " + var5);
      } catch (Exception var10) {
         TbsLog.e(a, "Http exception: " + var10.getMessage());
      }

      return null;
   }

   public interface a {
      void a(String var1);
   }
}
