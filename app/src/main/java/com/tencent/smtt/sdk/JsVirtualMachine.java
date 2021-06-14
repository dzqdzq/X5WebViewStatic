package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Looper;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsVirtualMachine;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

public final class JsVirtualMachine {
   private final Context a;
   private final IX5JsVirtualMachine b;
   private final HashSet<WeakReference<JsVirtualMachine.a>> c;

   public JsVirtualMachine(Context var1) {
      this(var1, (Looper)null);
   }

   public JsVirtualMachine(Context var1, Looper var2) {
      this.c = new HashSet();
      this.a = var1;
      this.b = X5JsCore.a(var1, var2);
   }

   public boolean isFallback() {
      return this.b == null;
   }

   protected IX5JsContext a() {
      Object var1;
      if (this.b == null) {
         JsVirtualMachine.a var2 = new JsVirtualMachine.a(this.a);
         this.c.add(new WeakReference(var2));
         var1 = var2;
      } else {
         var1 = this.b.createJsContext();
      }

      return (IX5JsContext)var1;
   }

   public void destroy() {
      if (this.b != null) {
         this.b.destroy();
      } else {
         Iterator var1 = this.c.iterator();

         while(var1.hasNext()) {
            WeakReference var2 = (WeakReference)var1.next();
            if (var2.get() != null) {
               ((JsVirtualMachine.a)var2.get()).destroy();
            }
         }

      }
   }

   public Looper getLooper() {
      return this.b != null ? this.b.getLooper() : Looper.myLooper();
   }

   public void onPause() {
      if (this.b != null) {
         this.b.onPause();
      } else {
         Iterator var1 = this.c.iterator();

         while(var1.hasNext()) {
            WeakReference var2 = (WeakReference)var1.next();
            if (var2.get() != null) {
               ((JsVirtualMachine.a)var2.get()).a();
            }
         }

      }
   }

   public void onResume() {
      if (this.b != null) {
         this.b.onResume();
      } else {
         Iterator var1 = this.c.iterator();

         while(var1.hasNext()) {
            WeakReference var2 = (WeakReference)var1.next();
            if (var2.get() != null) {
               ((JsVirtualMachine.a)var2.get()).b();
            }
         }

      }
   }

   private static class a implements IX5JsContext {
      private WebView a;

      public a(Context var1) {
         this.a = new WebView(var1);
         this.a.getSettings().setJavaScriptEnabled(true);
      }

      public void a() {
         if (this.a != null) {
            this.a.onPause();
         }
      }

      public void b() {
         if (this.a != null) {
            this.a.onResume();
         }
      }

      public void addJavascriptInterface(Object var1, String var2) {
         if (this.a != null) {
            this.a.addJavascriptInterface(var1, var2);
            this.a.loadUrl("about:blank");
         }
      }

      public void destroy() {
         if (this.a != null) {
            this.a.clearHistory();
            this.a.clearCache(true);
            this.a.loadUrl("about:blank");
            this.a.freeMemory();
            this.a.pauseTimers();
            this.a.destroy();
            this.a = null;
         }
      }

      public void evaluateJavascript(String var1, final android.webkit.ValueCallback<String> var2, URL var3) {
         if (this.a != null) {
            this.a.evaluateJavascript(var1, var2 == null ? null : new ValueCallback<String>() {
               public void a(String var1) {
                  var2.onReceiveValue(var1);
               }

               // $FF: synthetic method
               public void onReceiveValue(String var1) {
                  this.a((String)var1);
               }
            });
         }
      }

      public IX5JsValue evaluateScript(String var1, URL var2) {
         if (this.a == null) {
            return null;
         } else {
            this.a.evaluateJavascript(var1, (ValueCallback)null);
            return null;
         }
      }

      public void evaluateScriptAsync(String var1, final android.webkit.ValueCallback<IX5JsValue> var2, URL var3) {
         if (this.a != null) {
            this.a.evaluateJavascript(var1, var2 == null ? null : new ValueCallback<String>() {
               public void a(String var1) {
                  var2.onReceiveValue((IX5JsValue)null);
               }

               // $FF: synthetic method
               public void onReceiveValue(String var1) {
                  this.a((String)var1);
               }
            });
         }
      }

      public void removeJavascriptInterface(String var1) {
         if (this.a != null) {
            this.a.removeJavascriptInterface(var1);
         }
      }

      public void setExceptionHandler(android.webkit.ValueCallback<IX5JsError> var1) {
      }

      public void setPerContextData(Object var1) {
      }

      public void setName(String var1) {
      }

      public void stealValueFromOtherCtx(String var1, IX5JsContext var2, String var3) {
      }

      public int getNativeBufferId() {
         return -1;
      }

      public byte[] getNativeBuffer(int var1) {
         return null;
      }

      public int setNativeBuffer(int var1, byte[] var2) {
         return -1;
      }
   }
}
