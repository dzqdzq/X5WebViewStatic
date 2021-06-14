package com.tencent.smtt.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class c implements Closeable {
   private final RandomAccessFile a;
   private final File b;
   private final byte[] c;
   private boolean d;

   public c(String var1) throws FileNotFoundException {
      this(new File(var1));
   }

   public c(File var1) throws FileNotFoundException {
      this.c = new byte[8];
      this.b = var1;
      this.a = new RandomAccessFile(this.b, "r");
   }

   public void a(boolean var1) {
      this.d = var1;
   }

   public void a(long var1) throws IOException {
      this.a.seek(var1);
   }

   public final int a(byte[] var1) throws IOException {
      return this.a.read(var1);
   }

   public final int a(char[] var1) throws IOException {
      byte[] var2 = new byte[var1.length];
      int var3 = this.a.read(var2);

      for(int var4 = 0; var4 < var1.length; ++var4) {
         var1[var4] = (char)var2[var4];
      }

      return var3;
   }

   public final short a() throws IOException {
      short var1 = this.a.readShort();
      return this.d ? (short)((var1 & 255) << 8 | (var1 & '\uff00') >>> 8) : var1;
   }

   public final int b() throws IOException {
      int var1 = this.a.readInt();
      return this.d ? (var1 & 255) << 24 | (var1 & '\uff00') << 8 | (var1 & 16711680) >>> 8 | (var1 & -16777216) >>> 24 : var1;
   }

   public final long c() throws IOException {
      if (this.d) {
         this.a.readFully(this.c, 0, 8);
         return (long)this.c[7] << 56 | (long)(this.c[6] & 255) << 48 | (long)(this.c[5] & 255) << 40 | (long)(this.c[4] & 255) << 32 | (long)(this.c[3] & 255) << 24 | (long)(this.c[2] & 255) << 16 | (long)(this.c[1] & 255) << 8 | (long)(this.c[0] & 255);
      } else {
         return this.a.readLong();
      }
   }

   public void close() {
      try {
         this.a.close();
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }
}
