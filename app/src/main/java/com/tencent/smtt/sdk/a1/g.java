package com.tencent.smtt.sdk.a1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class g {
   private static g a;
   private static f b;

   private g() {
   }

   public static synchronized g a() {
      if (a == null) {
         a = new g();
      }

      return a;
   }

   public static String[] a(String var0) {
      return !TextUtils.isEmpty(var0) ? var0.split(",") : null;
   }

   public static String a(String[] var0) {
      StringBuilder var1 = new StringBuilder();
      if (var0 != null && var0.length > 0) {
         if (var0.length > 1) {
            for(int var2 = 0; var2 < var0.length - 1; ++var2) {
               var1.append(var0[var2]).append(",");
            }
         }

         var1.append(var0[var0.length - 1]);
      }

      return var1.toString();
   }

   private synchronized SharedPreferences b(Context var1) {
      return var1.getSharedPreferences("tbs_emergence", 4);
   }

   public void a(Context var1, String var2, String var3) {
      List var4 = this.a(var1, var2);
      var4.add(var3);
      this.a(var1, var2, var4);
   }

   public void a(Context var1) {
      b = f.a(new File(var1.getFilesDir(), "prefs.lock"));
   }

   public boolean b() {
      return b != null;
   }

   public void c() {
      if (b != null) {
         b.b();
         b = null;
      }

   }

   public void a(Context var1, String var2, List<String> var3) {
      SharedPreferences var4 = this.b(var1);
      Editor var5 = var4.edit();
      StringBuilder var6 = new StringBuilder();
      if (var3 != null && !var3.isEmpty()) {
         if (var3.size() > 1) {
            for(int var7 = 0; var7 < var3.size() - 1; ++var7) {
               var6.append((String)var3.get(var7));
               var6.append(";");
            }
         }

         var6.append((String)var3.get(var3.size() - 1));
      }

      var5.putString(var2, var6.toString());
      var5.apply();
      var5.commit();
   }

   public List<String> a(Context var1, String var2) {
      SharedPreferences var3 = this.b(var1);
      String var4 = var3.getString(var2, "");
      ArrayList var5 = new ArrayList();
      String[] var6 = var4.split(";");
      if (var6.length > 0) {
         var5.addAll(Arrays.asList(var6));
      }

      return var5;
   }

   public long b(Context var1, String var2) {
      SharedPreferences var3 = this.b(var1);
      return var3.getLong(var2, -1L);
   }

   public void a(Context var1, String var2, long var3) {
      SharedPreferences var5 = this.b(var1);
      Editor var6 = var5.edit();
      var6.putLong(var2, var3);
      var6.apply();
      var6.commit();
   }
}
