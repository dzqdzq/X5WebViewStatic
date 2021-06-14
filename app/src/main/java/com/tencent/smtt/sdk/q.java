package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

class q {
   private DexLoader a = null;
   private Object b = null;

   public q(DexLoader var1) {
      this.a = var1;
   }

   public Object a(Context var1, Object var2, Bundle var3) {
      if (this.a != null) {
         this.b = this.a.newInstance("com.tencent.tbs.cache.TbsVideoCacheTaskProxy", new Class[]{Context.class, Object.class, Bundle.class}, var1, var2, var3);
      }

      return this.b;
   }

   public void a() {
      if (this.a != null) {
         this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "pauseTask", new Class[0]);
      }

   }

   public void b() {
      if (this.a != null) {
         this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "resumeTask", new Class[0]);
      }

   }

   public void c() {
      if (this.a != null) {
         this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "stopTask", new Class[0]);
      }

   }

   public void a(boolean var1) {
      if (this.a != null) {
         this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "removeTask", new Class[]{Boolean.TYPE}, var1);
      }

   }

   public long d() {
      if (this.a != null) {
         Object var1 = this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getContentLength", new Class[0]);
         if (var1 instanceof Long) {
            return (Long)var1;
         }
      }

      return 0L;
   }

   public int e() {
      if (this.a != null) {
         Object var1 = this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getDownloadedSize", new Class[0]);
         if (var1 instanceof Integer) {
            return (Integer)var1;
         }
      }

      return 0;
   }

   public int f() {
      if (this.a != null) {
         Object var1 = this.a.invokeMethod(this.b, "com.tencent.tbs.cache.TbsVideoCacheTaskProxy", "getProgress", new Class[0]);
         if (var1 instanceof Integer) {
            return (Integer)var1;
         }
      }

      return 0;
   }
}
