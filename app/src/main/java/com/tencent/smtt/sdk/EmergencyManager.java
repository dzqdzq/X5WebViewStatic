package com.tencent.smtt.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.a1.Command;
import com.tencent.smtt.sdk.a1.DateUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.AppUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmergencyManager {
   private static String a = "EmergencyManager";
   private static int b = 0;
   private static int c = 1;
   private static int d = 2;
   private static int e = 3;
   private static int f = 4;
   private static int g = 5;
   private static EmergencyManager h;
   private long i = 60000L;
   private long j = 86400000L;
   private boolean k = false;

   private EmergencyManager() {
   }

   public static synchronized EmergencyManager a() {
      if (h == null) {
         h = new EmergencyManager();
      }

      return h;
   }

   public void dispatchEmergencyCommand(Context var1, Integer var2, Map<Integer, String> var3) {
      com.tencent.smtt.sdk.c.a().b(var1);
      TbsLog.e(a, "Dispatch emergency commands on tbs extension");
      QbSdk.a(var1, var2, var3);
      SDKEngine var4 = SDKEngine.a(true);
      if (var4 != null) {
         TbsWizard var5 = var4.a();
         if (var5 != null) {
            DexLoader var6 = var5.b();
            if (null != var6) {
               TbsLog.e(a, "Dispatch emergency commands on tbs shell");
               Class[] var7 = new Class[]{Integer.class, Map.class};
               Object[] var8 = new Object[]{var2, var3};
               var6.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "dispatchEmergencyCommand", var7, var8);
            } else {
               TbsLog.e(a, "Dex loader is null, cancel commands dispatching of tbs shell");
            }

         }
      }
   }

   public void a(Context var1) {
      if (!this.k) {
         this.k = true;
         com.tencent.smtt.sdk.a1.g var2 = com.tencent.smtt.sdk.a1.g.a();
         if (!var2.b()) {
            var2.a(var1);

            try {
               long var3 = com.tencent.smtt.sdk.a1.g.a().b(var1, "emergence_timestamp");
               long var5 = com.tencent.smtt.sdk.a1.g.a().b(var1, "emergence_req_interval");
               long var7 = System.currentTimeMillis();
               long var9 = var7 - var3;
               long var11 = Math.min(Math.max(this.i, var5), this.j);
               if (var9 > var11) {
                  TbsLog.d(a, "Emergency configuration is out of date, attempt to query again, " + var9 / 1000L + " seconds has past");
                  com.tencent.smtt.sdk.a1.g.a().a(var1, "emergence_timestamp", var7);
                  this.b(var1);
               } else {
                  this.a(var1, c, new ArrayList());
                  TbsLog.d(a, "Emergency configuration is up to date, " + var9 / 1000L + " seconds has past, need " + Math.abs(var9 - var11) / 1000L + " more seconds for an another request");
               }
            } catch (Exception var16) {
               this.a(var1, g, new ArrayList());
               TbsLog.d(a, "Unexpected exception happened when query emergency configuration: " + var16.getMessage());
            } finally {
               com.tencent.smtt.sdk.a1.g.a().c();
            }
         } else {
            this.a(var1, f, new ArrayList());
         }
      }

   }

   private void b(final Context var1) {
      com.tencent.smtt.sdk.a1.c var2 = new com.tencent.smtt.sdk.a1.c();
      var2.a(AppUtil.a(var1));
      var2.b(AppUtil.c(var1));
      var2.a(AppUtil.b(var1));
      var2.c(AppUtil.a());
      var2.d("x5webview");
      var2.b(QbSdk.getTbsSdkVersion());
      var2.c(QbSdk.getTbsVersion(var1));
      List var3 = com.tencent.smtt.sdk.a1.g.a().a(var1, "emergence_ids");
      Iterator var4 = var3.iterator();
      ArrayList var5 = new ArrayList();

      while(var4.hasNext()) {
         try {
            String var6 = (String)var4.next();
            if (!TextUtils.isEmpty(var6)) {
               String[] var7 = com.tencent.smtt.sdk.a1.g.a(var6);
               if (var7 != null && var7.length == 4) {
                  int var8 = Integer.parseInt(var7[0]);
                  long var9 = Long.parseLong(var7[3]);
                  long var11 = System.currentTimeMillis();
                  if (var11 < var9) {
                     var5.add(var8);
                  }
               }
            }
         } catch (Exception var13) {
            var13.printStackTrace();
         }
      }

      var2.a((List)var5);
      com.tencent.smtt.sdk.a1.e var14 = new com.tencent.smtt.sdk.a1.e(var1, com.tencent.smtt.utils.m.a(var1).g(), var2.a());
      var14.a(new com.tencent.smtt.sdk.a1.e.a() {
         public void a(String var1x) {
            com.tencent.smtt.sdk.a1.d var2 = com.tencent.smtt.sdk.a1.d.a(var1x);
            if (var2 != null && var2.a() == 0) {
               com.tencent.smtt.sdk.a1.g.a().a(var1, "emergence_req_interval", var2.b());
               List var3 = var2.c();
               if (var3 != null) {
                  EmergencyManager.this.a(var1, EmergencyManager.b, var3);
               } else {
                  EmergencyManager.this.a(var1, EmergencyManager.d, new ArrayList());
               }
            } else {
               EmergencyManager.this.a(var1, EmergencyManager.e, new ArrayList());
            }

         }
      });
   }

   private void a(Context var1, int var2, List<Command> var3) {
      LinkedHashMap var4 = new LinkedHashMap();
      com.tencent.smtt.sdk.a1.g var5 = com.tencent.smtt.sdk.a1.g.a();
      List var6 = var5.a(var1, "emergence_ids");
      HashSet var7 = new HashSet();
      Iterator var8;
      if (var6 != null && !var6.isEmpty()) {
         var8 = var6.iterator();

         while(var8.hasNext()) {
            String var9 = (String)var8.next();
            String[] var10 = com.tencent.smtt.sdk.a1.g.a(var9);
            if (var10 != null && var10.length == 4) {
               var7.add(Integer.parseInt(var10[0]));
            }
         }
      }

      var8 = var3.iterator();

      while(var8.hasNext()) {
         Command var14 = (Command)var8.next();
         int var13 = var14.b();
         int var11 = var14.a();
         if (var7.contains(var11)) {
            TbsLog.d(a, "Command has been executed: " + var14.toString() + ", ignored");
         } else if (var14.isExpired()) {
            TbsLog.d(a, "Command is out of date: " + var14.toString() + ", now: " + DateUtil.toLocaleString(System.currentTimeMillis()));
         } else {
            var4.put(var13, var14.c());
            String var12 = com.tencent.smtt.sdk.a1.g.a(new String[]{String.valueOf(var11), String.valueOf(var14.b()), String.valueOf(var14.c()), String.valueOf(var14.d())});
            var5.a(var1, "emergence_ids", var12);
         }
      }

      this.dispatchEmergencyCommand(var1, var2, var4);
   }
}
