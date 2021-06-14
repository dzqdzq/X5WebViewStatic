package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class c {
   private String a = "EmergenceMsgPublisher";
   private String b = "tbs_emergence";
   private String c = "emergence_executed_ids";
   private String d = "emergence_ids";
   private static c e;
   private final Map<Integer, c.a> f = new LinkedHashMap();
   private static boolean g = false;

   private c() {
   }

   public void a(Context var1, Integer var2, c.a var3) {
      Map var4 = this.a(var1);
      Iterator var5 = var4.keySet().iterator();

      while(var5.hasNext()) {
         Integer var6 = (Integer)var5.next();
         c.b var7 = (c.b)var4.get(var6);
         if (var7 == null) {
            this.a("Unexpected null command!");
         } else if (var7.b == var2) {
            this.a(var1, var7, var3);
            return;
         }
      }

      if (!g) {
         this.f.put(var2, var3);
         this.a("Emergence config did not arrived yet, code[" + var2 + "] has been suspended");
      }

   }

   public static c a() {
      if (e == null) {
         e = new c();
      }

      return e;
   }

   public Map<Integer, c.b> a(Context var1) {
      HashMap var2 = new HashMap();
      SharedPreferences var3 = var1.getSharedPreferences(this.b, 0);
      if (var1 == null) {
         this.a("Unexpected null context!");
         return var2;
      } else {
         String var4 = var3.getString(this.d, "");
         if (TextUtils.isEmpty(var4)) {
            this.a("Empty local emergence ids!");
            return var2;
         } else {
            this.a("Local emergence ids: " + var4);
            String[] var5 = var4.split(";");
            if (var5 != null) {
               String[] var6 = var5;
               int var7 = var5.length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  String var9 = var6[var8];
                  if (!TextUtils.isEmpty(var9)) {
                     String[] var10 = var9.split(",");
                     if (var10 != null && var10.length == 4) {
                        c.b var11 = new c.b();

                        try {
                           var11.a = Integer.parseInt(var10[0]);
                           var11.b = Integer.parseInt(var10[1]);
                           var11.c = String.valueOf(var10[2]);
                           var11.d = Long.parseLong(var10[3]);
                        } catch (Throwable var16) {
                        }

                        long var12 = System.currentTimeMillis();
                        if (var12 < var11.d) {
                           var2.put(var11.a, var11);
                        }
                     }
                  }
               }
            }

            String var17 = var3.getString(this.c, "");
            this.a("Executed ids: " + var17);
            String[] var18 = var17.split(",");
            if (var18 != null) {
               ArrayList var19 = new ArrayList();
               String[] var20 = var18;
               int var22 = var18.length;

               for(int var23 = 0; var23 < var22; ++var23) {
                  String var24 = var20[var23];
                  if (!TextUtils.isEmpty(var24)) {
                     int var13 = -1;

                     try {
                        var13 = Integer.parseInt(var24);
                     } catch (Throwable var15) {
                        var15.printStackTrace();
                     }

                     if (var13 > 0) {
                        var19.add(var13);
                     }
                  }
               }

               for(int var21 = 0; var21 < var19.size(); ++var21) {
                  var2.remove(var19.get(var21));
               }
            }

            return var2;
         }
      }
   }

   public void b(Context var1) {
      Map var2 = this.a(var1);
      this.a("On notify emergence, validate commands: " + var2);
      g = true;
      Iterator var3 = this.f.keySet().iterator();

      while(var3.hasNext()) {
         Integer var4 = (Integer)var3.next();
         Iterator var5 = var2.keySet().iterator();

         while(var5.hasNext()) {
            Integer var6 = (Integer)var5.next();
            if (((c.b)var2.get(var6)).b == var4) {
               this.a(var1, (c.b)var2.get(var6), (c.a)this.f.get(var4));
            }
         }
      }

      if (!this.f.isEmpty()) {
         this.a("Emergency code[" + this.f.keySet() + "] did not happen, clear suspended queries");
         this.f.clear();
      }

   }

   private void a(String var1) {
   }

   private void a(Context var1, c.b var2, c.a var3) {
      if (var3 != null) {
         this.a("Executed command: " + var2.b + ", extra: " + var2.c);
         var3.a(var2.c);
         SharedPreferences var4 = var1.getSharedPreferences(this.b, 4);
         String var5 = var4.getString(this.c, "");
         HashSet var6 = new HashSet();
         if (!TextUtils.isEmpty(var5)) {
            String[] var7 = var5.split(",");
            if (var7 != null && var7.length > 0) {
               try {
                  String[] var8 = var7;
                  int var9 = var7.length;

                  for(int var10 = 0; var10 < var9; ++var10) {
                     String var11 = var8[var10];
                     Integer var12 = Integer.parseInt(var11);
                     var6.add(var12);
                  }
               } catch (Throwable var13) {
                  var13.printStackTrace();
               }
            }
         }

         var6.add(var2.a);
         StringBuilder var14 = new StringBuilder();
         Iterator var15 = var6.iterator();

         while(var15.hasNext()) {
            Integer var17 = (Integer)var15.next();
            var14.append(var17);
            var14.append(",");
         }

         Editor var16 = var4.edit();
         var16.putString(this.c, var14.toString());
         var16.commit();
      }

   }

   public static class b {
      public int a = -1;
      public int b = -1;
      public String c = "";
      public long d = -1L;

      public String toString() {
         return "{seqId=" + this.a + ", code=" + this.b + ", extra='" + this.c + '\'' + ", expired=" + this.d + '}';
      }
   }

   public interface a {
      void a(String var1);
   }
}
