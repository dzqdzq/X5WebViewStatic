package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.nio.ByteBuffer;

public class X5JsCore {
   private static X5JsCore.a a;
   private static X5JsCore.a b;
   private static X5JsCore.a c;
   private final Context d;
   private Object e = null;
   private WebView f = null;

   private static Object a(String var0, Class<?>[] var1, Object... var2) {
      try {
         X5CoreEngine var3 = X5CoreEngine.a();
         if (null != var3 && var3.b()) {
            DexLoader var4 = var3.c().b();
            return var4.invokeStaticMethod("com.tencent.tbs.tbsshell.WebCoreProxy", var0, var1, var2);
         }

         Log.e("X5JsCore", "X5Jscore#" + var0 + " - x5CoreEngine is null or is not x5core.");
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      return null;
   }

   public static boolean canUseX5JsCoreNewAPI(Context var0) {
      if (c != X5JsCore.a.a) {
         return c == X5JsCore.a.c;
      } else {
         c = X5JsCore.a.b;
         Object var1 = a("canUseX5JsCoreNewAPI", new Class[]{Context.class}, var0);
         if (var1 != null && var1 instanceof Boolean && (Boolean)var1) {
            c = X5JsCore.a.c;
            return true;
         } else {
            return false;
         }
      }
   }

   public static boolean canUseX5JsCore(Context var0) {
      if (a != X5JsCore.a.a) {
         return a == X5JsCore.a.c;
      } else {
         a = X5JsCore.a.b;
         Object var1 = a("canUseX5JsCore", new Class[]{Context.class}, var0);
         if (var1 != null && var1 instanceof Boolean && (Boolean)var1) {
            a("setJsValueFactory", new Class[]{Object.class}, JsValue.a());
            a = X5JsCore.a.c;
            return true;
         } else {
            return false;
         }
      }
   }

   public static boolean canX5JsCoreUseNativeBuffer(Context var0) {
      if (b != X5JsCore.a.a) {
         return b == X5JsCore.a.c;
      } else {
         b = X5JsCore.a.b;
         if (!canUseX5JsCore(var0)) {
            return false;
         } else {
            Object var1 = a("canX5JsCoreUseBuffer", new Class[]{Context.class}, var0);
            if (var1 != null && var1 instanceof Boolean && (Boolean)var1) {
               b = X5JsCore.a.c;
               return true;
            } else {
               return false;
            }
         }
      }
   }

   protected static IX5JsVirtualMachine a(Context var0, Looper var1) {
      if (canUseX5JsCore(var0)) {
         Object var2 = a("createX5JsVirtualMachine", new Class[]{Context.class, Looper.class}, var0, var1);
         if (var2 != null) {
            return (IX5JsVirtualMachine)var2;
         }
      }

      Log.e("X5JsCore", "X5JsCore#createVirtualMachine failure!");
      return null;
   }

   protected static Object a() {
      return a("currentContextData", new Class[0]);
   }

   /** @deprecated */
   @Deprecated
   public X5JsCore(Context var1) {
      this.d = var1;
      if (canUseX5JsCore(var1)) {
         Object var2 = a("createX5JavaBridge", new Class[]{Context.class}, var1);
         if (var2 != null) {
            this.e = var2;
            return;
         }
      }

      Log.e("X5JsCore", "X5JsCore create X5JavaBridge failure, use fallback!");
      this.f = new WebView(var1);
      this.f.getSettings().setJavaScriptEnabled(true);
   }

   /** @deprecated */
   @Deprecated
   public void addJavascriptInterface(Object var1, String var2) {
      if (this.e != null) {
         a("addJavascriptInterface", new Class[]{Object.class, String.class, Object.class}, var1, var2, this.e);
      } else if (this.f != null) {
         this.f.addJavascriptInterface(var1, var2);
         this.f.loadUrl("about:blank");
      }

   }

   /** @deprecated */
   @Deprecated
   public void removeJavascriptInterface(String var1) {
      if (this.e != null) {
         a("removeJavascriptInterface", new Class[]{String.class, Object.class}, var1, this.e);
      } else if (this.f != null) {
         this.f.removeJavascriptInterface(var1);
      }

   }

   /** @deprecated */
   @Deprecated
   public void evaluateJavascript(String var1, ValueCallback<String> var2) {
      if (this.e != null) {
         a("evaluateJavascript", new Class[]{String.class, android.webkit.ValueCallback.class, Object.class}, var1, var2, this.e);
      } else if (this.f != null) {
         this.f.evaluateJavascript(var1, var2);
      }

   }

   /** @deprecated */
   @Deprecated
   public void pauseTimers() {
      if (this.e != null) {
         a("pauseTimers", new Class[]{Object.class}, this.e);
      }

   }

   /** @deprecated */
   @Deprecated
   public void resumeTimers() {
      if (this.e != null) {
         a("resumeTimers", new Class[]{Object.class}, this.e);
      }

   }

   /** @deprecated */
   @Deprecated
   public void pause() {
      if (this.e != null) {
         a("pause", new Class[]{Object.class}, this.e);
      }

   }

   /** @deprecated */
   @Deprecated
   public void resume() {
      if (this.e != null) {
         a("resume", new Class[]{Object.class}, this.e);
      }

   }

   /** @deprecated */
   @Deprecated
   public int getNativeBufferId() {
      if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
         Object var1 = a("getNativeBufferId", new Class[]{Object.class}, this.e);
         if (var1 != null && var1 instanceof Integer) {
            return (Integer)var1;
         }
      }

      return -1;
   }

   /** @deprecated */
   @Deprecated
   public void setNativeBuffer(int var1, ByteBuffer var2) {
      if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
         a("setNativeBuffer", new Class[]{Object.class, Integer.TYPE, ByteBuffer.class}, this.e, var1, var2);
      }

   }

   /** @deprecated */
   @Deprecated
   public ByteBuffer getNativeBuffer(int var1) {
      if (this.e != null && canX5JsCoreUseNativeBuffer(this.d)) {
         Object var2 = a("getNativeBuffer", new Class[]{Object.class, Integer.TYPE}, this.e, var1);
         if (var2 != null && var2 instanceof ByteBuffer) {
            return (ByteBuffer)var2;
         }
      }

      return null;
   }

   /** @deprecated */
   @Deprecated
   public void destroy() {
      if (this.e != null) {
         a("destroyX5JsCore", new Class[]{Object.class}, this.e);
         this.e = null;
      } else if (this.f != null) {
         this.f.clearHistory();
         this.f.clearCache(true);
         this.f.loadUrl("about:blank");
         this.f.freeMemory();
         this.f.pauseTimers();
         this.f.destroy();
         this.f = null;
      }

   }

   static {
      a = X5JsCore.a.a;
      b = X5JsCore.a.a;
      c = X5JsCore.a.a;
   }

   private static enum a {
      a,
      b,
      c;
   }
}
