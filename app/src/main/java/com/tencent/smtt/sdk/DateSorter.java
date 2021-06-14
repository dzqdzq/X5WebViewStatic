package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.interfaces.IX5DateSorter;

public class DateSorter {
   public static int DAY_COUNT = a() ? 5 : 5;
   private android.webkit.DateSorter a;
   private IX5DateSorter b;

   public DateSorter(Context var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      if (null != var2 && var2.b()) {
         this.b = var2.c().h(var1);
      } else {
         this.a = new android.webkit.DateSorter(var1);
      }

   }

   public int getIndex(long var1) {
      X5CoreEngine var3 = X5CoreEngine.a();
      return null != var3 && var3.b() ? this.b.getIndex(var1) : this.a.getIndex(var1);
   }

   public String getLabel(int var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? this.b.getLabel(var1) : this.a.getLabel(var1);
   }

   public long getBoundary(int var1) {
      X5CoreEngine var2 = X5CoreEngine.a();
      return null != var2 && var2.b() ? this.b.getBoundary(var1) : this.a.getBoundary(var1);
   }

   private static boolean a() {
      boolean var0 = false;
      X5CoreEngine var1 = X5CoreEngine.a();
      if (null != var1 && var1.b()) {
         var0 = true;
      }

      return var0;
   }
}
