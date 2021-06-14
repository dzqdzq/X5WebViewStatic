package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;

class TbsMediaPlayerProxy {
   private DexLoader a = null;
   private Object b = null;

   public TbsMediaPlayerProxy(DexLoader var1, Context var2) {
      this.a = var1;
      this.b = this.a.newInstance("com.tencent.tbs.player.TbsMediaPlayerProxy", new Class[]{Context.class}, var2);
   }

   public boolean a() {
      return this.b != null;
   }

   public void a(SurfaceTexture var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setSurfaceTexture", new Class[]{SurfaceTexture.class}, var1);
   }

   public void a(TbsMediaPlayer.TbsMediaPlayerListener var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setPlayerListener", new Class[]{Object.class}, var1);
   }

   public float b() {
      Float var1 = (Float)this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "getVolume", new Class[0]);
      return var1 != null ? var1 : 0.0F;
   }

   public void a(float var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setVolume", new Class[]{Float.TYPE}, var1);
   }

   public void a(String var1, Bundle var2) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "startPlay", new Class[]{String.class, Bundle.class}, var1, var2);
   }

   public void a(int var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "subtitle", new Class[]{Integer.TYPE}, var1);
   }

   public void b(int var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "audio", new Class[]{Integer.TYPE}, var1);
   }

   public void c() {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "pause", new Class[0]);
   }

   public void d() {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "play", new Class[0]);
   }

   public void a(long var1) {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "seek", new Class[]{Long.TYPE}, var1);
   }

   public void e() {
      this.a.invokeMethod(this.b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "close", new Class[0]);
   }
}
