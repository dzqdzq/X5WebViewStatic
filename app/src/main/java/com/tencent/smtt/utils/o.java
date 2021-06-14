package com.tencent.smtt.utils;

import android.os.Build.VERSION;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class o {
   private o.b a = null;
   private o.b b = null;

   public void a(File var1) {
      this.a = new o.b(var1);
   }

   public void b(File var1) {
      this.b = new o.b(var1);
   }

   public boolean a() {
      if (this.b != null && this.a != null) {
         return this.b.a().size() == this.a.a().size() && this.a(this.a, this.b);
      } else {
         return false;
      }
   }

   private boolean a(o.b var1, o.b var2) {
      if (var1 != null && var1.a() != null && var2 != null && var2.a() != null) {
         Map var3 = var1.a();
         Map var4 = var2.a();
         Iterator var5 = var3.entrySet().iterator();

         o.a var8;
         o.a var9;
         do {
            if (!var5.hasNext()) {
               return true;
            }

            Entry var6 = (Entry)var5.next();
            String var7 = (String)var6.getKey();
            var8 = (o.a)var6.getValue();
            if (!var4.containsKey(var7)) {
               return false;
            }

            var9 = (o.a)var4.get(var7);
         } while(var8.a() == var9.a() && var8.b() == var9.b());

         return false;
      } else {
         return false;
      }
   }

   class b {
      private Map<String, o.a> b = new HashMap();

      Map<String, o.a> a() {
         return this.b;
      }

      b(File var2) {
         this.b.clear();
         this.a(var2);
      }

      private void a(File var1) {
         if (var1.isDirectory()) {
            File[] var2 = var1.listFiles();
            if (var2 == null && VERSION.SDK_INT >= 26) {
               return;
            }

            for(int var3 = 0; var3 < var2.length; ++var3) {
               this.a(var2[var3]);
            }
         } else if (var1.isFile()) {
            this.a(var1.getName(), var1.length(), var1.lastModified());
         }

      }

      private void a(String var1, long var2, long var4) {
         if (var1 != null && var1.length() > 0 && var2 > 0L && var4 > 0L) {
            o.a var6 = o.this.new a(var1, var2, var4);
            if (!this.b.containsKey(var1)) {
               this.b.put(var1, var6);
            }
         }

      }
   }

   class a {
      private String b;
      private long c;
      private long d;

      a(String var2, long var3, long var5) {
         this.b = var2;
         this.c = var3;
         this.d = var5;
      }

      long a() {
         return this.c;
      }

      long b() {
         return this.d;
      }
   }
}
