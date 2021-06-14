package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.tencent.smtt.export.external.DexLoader;
import dalvik.system.DexClassLoader;

class TbsPlayerProxy {
   protected DexLoader a = null;

   public TbsPlayerProxy(DexLoader var1) {
      this.a = var1;
   }

   public Object a(Context var1) {
      Object var2 = null;
      var2 = this.a.newInstance("com.tencent.tbs.player.TbsPlayerProxy", new Class[]{Context.class, DexClassLoader.class}, var1, this.a.getClassLoader());
      return var2;
   }

   public boolean a(Object var1, Bundle var2, FrameLayout var3, Object var4) {
      Object var5 = null;
      if (var4 != null) {
         var5 = this.a.invokeMethod(var1, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class, Object.class}, var2, var3, var4);
      } else {
         var5 = this.a.invokeMethod(var1, "com.tencent.tbs.player.TbsPlayerProxy", "play", new Class[]{Bundle.class, FrameLayout.class}, var2, var3);
      }

      return var5 instanceof Boolean ? (Boolean)var5 : false;
   }

   public void a(Object var1, Activity var2, int var3) {
      this.a.invokeMethod(var1, "com.tencent.tbs.player.TbsPlayerProxy", "onActivity", new Class[]{Activity.class, Integer.TYPE}, var2, var3);
   }

   public void onUserStateChanged(Object var1) {
      this.a.invokeMethod(var1, "com.tencent.tbs.player.TbsPlayerProxy", "onUserStateChanged", new Class[0]);
   }
}
