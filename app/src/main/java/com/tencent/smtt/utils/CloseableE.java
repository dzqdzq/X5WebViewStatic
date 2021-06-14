package com.tencent.smtt.utils;

import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UnknownFormatConversionException;

public class CloseableE implements Closeable {
   public abstract static class a {
      short a;
      short b;
      int c;
      int d;
      short e;
      short f;
      short g;
      short h;
      short i;
      short j;

      abstract long a();

      abstract long b();
   }
   public abstract static class k {
      int g;
      int h;
      int i;
      int j;

      public abstract int a();

      public abstract long b();
   }
   abstract static class l {
      int c;
      char d;
      char e;
      short f;
   }
   static class e extends CloseableE.l{
      int a;
      int b;
   }
   static class g extends CloseableE.j {
      long a;
      long b;
      long c;
      long d;
      long e;
      long f;
   }

   static class c extends CloseableE.j {
      int a;
      int b;
      int c;
      int d;
      int e;
      int f;
   }

   public static class j {
      int g;
      int h;
   }

   static class i extends CloseableE.l{
      long a;
      long b;
   }

   static class h extends CloseableE.k {
      long a;
      long b;
      long c;
      long d;
      long e;
      long f;

      public int a() {
         return (int)this.d;
      }

      public long b() {
         return this.c;
      }
   }

   static class d extends CloseableE.k {
      int a;
      int b;
      int c;
      int d;
      int e;
      int f;

      public int a() {
         return this.d;
      }

      public long b() {
         return (long)this.c;
      }
   }

   static class f extends CloseableE.a {
      long k;
      long l;
      long m;

      long a() {
         return this.m;
      }

      long b() {
         return this.l;
      }
   }

   static class b extends CloseableE.a {
      int k;
      int l;
      int m;

      long a() {
         return (long)this.m;
      }

      long b() {
         return (long)this.l;
      }
   }

   static final char[] a = new char[]{'\u007f', 'E', 'L', 'F', '\u0000'};
   final char[] b = new char[16];
   private final com.tencent.smtt.utils.c g;
   private final CloseableE.a h;
   private final CloseableE.k[] i;
   private byte[] j;
   boolean c;
   CloseableE.j[] d;
   CloseableE.l[] e;
   byte[] f;

   final boolean a() {
      return this.b[0] == a[0];
   }

   final char b() {
      return this.b[4];
   }

   final char c() {
      return this.b[5];
   }

   public final boolean d() {
      return this.b() == 2;
   }

   public final boolean e() {
      return this.c() == 1;
   }

   public CloseableE(File var1) throws IOException, UnknownFormatConversionException {
      com.tencent.smtt.utils.c var2 = this.g = new com.tencent.smtt.utils.c(var1);
      var2.a(this.b);
      if (!this.a()) {
         throw new UnknownFormatConversionException("Invalid elf magic: " + var1);
      } else {
         var2.a(this.e());
         boolean var3 = this.d();
         if (var3) {
            CloseableE.f var4 = new CloseableE.f();
            var4.a = var2.a();
            var4.b = var2.a();
            var4.c = var2.b();
            var4.k = var2.c();
            var4.l = var2.c();
            var4.m = var2.c();
            this.h = var4;
         } else {
            CloseableE.b var9 = new CloseableE.b();
            var9.a = var2.a();
            var9.b = var2.a();
            var9.c = var2.b();
            var9.k = var2.b();
            var9.l = var2.b();
            var9.m = var2.b();
            this.h = var9;
         }

         CloseableE.a var10 = this.h;
         var10.d = var2.b();
         var10.e = var2.a();
         var10.f = var2.a();
         var10.g = var2.a();
         var10.h = var2.a();
         var10.i = var2.a();
         var10.j = var2.a();
         this.i = new CloseableE.k[var10.i];

         for(int var5 = 0; var5 < var10.i; ++var5) {
            long var6 = var10.a() + (long)(var5 * var10.h);
            var2.a(var6);
            if (var3) {
               CloseableE.h var8 = new CloseableE.h();
               var8.g = var2.b();
               var8.h = var2.b();
               var8.a = var2.c();
               var8.b = var2.c();
               var8.c = var2.c();
               var8.d = var2.c();
               var8.i = var2.b();
               var8.j = var2.b();
               var8.e = var2.c();
               var8.f = var2.c();
               this.i[var5] = var8;
            } else {
               CloseableE.d var13 = new CloseableE.d();
               var13.g = var2.b();
               var13.h = var2.b();
               var13.a = var2.b();
               var13.b = var2.b();
               var13.c = var2.b();
               var13.d = var2.b();
               var13.i = var2.b();
               var13.j = var2.b();
               var13.e = var2.b();
               var13.f = var2.b();
               this.i[var5] = var13;
            }
         }

         if (var10.j > -1 && var10.j < this.i.length) {
            CloseableE.k var11 = this.i[var10.j];
            if (var11.h == 3) {
               int var12 = var11.a();
               this.j = new byte[var12];
               var2.a(var11.b());
               var2.a(this.j);
               if (this.c) {
                  this.f();
               }

            } else {
               throw new UnknownFormatConversionException("Wrong string section e_shstrndx=" + var10.j);
            }
         } else {
            throw new UnknownFormatConversionException("Invalid e_shstrndx=" + var10.j);
         }
      }
   }

   private void f() throws IOException {
      CloseableE.a var1 = this.h;
      com.tencent.smtt.utils.c var2 = this.g;
      boolean var3 = this.d();
      CloseableE.k var4 = this.a(".dynsym");
      int var5;
      if (var4 != null) {
         var2.a(var4.b());
         var5 = var4.a() / (var3 ? 24 : 16);
         this.e = new CloseableE.l[var5];
         char[] var6 = new char[1];

         for(int var7 = 0; var7 < var5; ++var7) {
            if (var3) {
               CloseableE.i var8 = new CloseableE.i();
               var8.c = var2.b();
               var2.a(var6);
               var8.d = var6[0];
               var2.a(var6);
               var8.e = var6[0];
               var8.a = var2.c();
               var8.b = var2.c();
               var8.f = var2.a();
               this.e[var7] = var8;
            } else {
               CloseableE.e var11 = new CloseableE.e();
               var11.c = var2.b();
               var11.a = var2.b();
               var11.b = var2.b();
               var2.a(var6);
               var11.d = var6[0];
               var2.a(var6);
               var11.e = var6[0];
               var11.f = var2.a();
               this.e[var7] = var11;
            }
         }

         CloseableE.k var10 = this.i[var4.i];
         var2.a(var10.b());
         this.f = new byte[var10.a()];
         var2.a(this.f);
      }

      this.d = new CloseableE.j[var1.g];

      for(var5 = 0; var5 < var1.g; ++var5) {
         long var9 = var1.b() + (long)(var5 * var1.f);
         var2.a(var9);
         if (var3) {
            CloseableE.g var12 = new CloseableE.g();
            var12.g = var2.b();
            var12.h = var2.b();
            var12.a = var2.c();
            var12.b = var2.c();
            var12.c = var2.c();
            var12.d = var2.c();
            var12.e = var2.c();
            var12.f = var2.c();
            this.d[var5] = var12;
         } else {
            CloseableE.c var13 = new CloseableE.c();
            var13.g = var2.b();
            var13.h = var2.b();
            var13.a = var2.b();
            var13.b = var2.b();
            var13.c = var2.b();
            var13.d = var2.b();
            var13.e = var2.b();
            var13.f = var2.b();
            this.d[var5] = var13;
         }
      }

   }

   public final CloseableE.k a(String var1) {
      CloseableE.k[] var2 = this.i;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         CloseableE.k var5 = var2[var4];
         if (var1.equals(this.a(var5.g))) {
            return var5;
         }
      }

      return null;
   }

   public final String a(int var1) {
      if (var1 == 0) {
         return "SHN_UNDEF";
      } else {
         int var3;
         for(var3 = var1; this.j[var3] != 0; ++var3) {
         }

         return new String(this.j, var1, var3 - var1);
      }
   }

   public void close() {
      this.g.close();
   }

   public static boolean a(File var0) {
      long var1 = 0L;

      try {
         RandomAccessFile var3 = new RandomAccessFile(var0, "r");
         var1 = (long)var3.readInt();
         var3.close();
      } catch (Throwable var4) {
         var4.printStackTrace();
         return false;
      }

      return var1 == 2135247942L;
   }

   public static boolean b(File var0) {
      Object var1 = null;
      if (g() && a(var0)) {
         try {
            new CloseableE(var0);
         } catch (IOException var3) {
            Log.e("ELF", "checkElfFile IOException: " + var3);
            return false;
         } catch (UnknownFormatConversionException var4) {
            Log.e("ELF", "checkElfFile UnknownFormatConversionException: " + var4);
         } catch (Throwable var5) {
            Log.e("ELF", "checkElfFile Throwable: " + var5);
         }
      }

      return true;
   }

   private static boolean g() {
      String var0 = System.getProperty("java.vm.version");
      return var0 != null && var0.startsWith("2");
   }



}
