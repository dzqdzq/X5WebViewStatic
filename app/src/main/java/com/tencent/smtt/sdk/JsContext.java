package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.net.URL;

public final class JsContext {
   private final JsVirtualMachine a;
   private final IX5JsContext b;
   private JsContext.ExceptionHandler c;
   private String d;

   public JsContext(Context var1) {
      this(new JsVirtualMachine(var1));
   }

   public JsContext(JsVirtualMachine var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("The virtualMachine value can not be null");
      } else {
         this.a = var1;
         this.b = this.a.a();

         try {
            this.b.setPerContextData(this);
         } catch (AbstractMethodError var3) {
         }

      }
   }

   public void addJavascriptInterface(Object var1, String var2) {
      this.b.addJavascriptInterface(var1, var2);
   }

   public void destroy() {
      this.b.destroy();
   }

   public void evaluateJavascript(String var1, android.webkit.ValueCallback<String> var2) {
      this.evaluateJavascript(var1, var2, (URL)null);
   }

   public void evaluateJavascript(String var1, android.webkit.ValueCallback<String> var2, URL var3) {
      this.b.evaluateJavascript(var1, var2, var3);
   }

   public JsValue evaluateScript(String var1) {
      return this.evaluateScript(var1, (URL)null);
   }

   public JsValue evaluateScript(String var1, URL var2) {
      IX5JsValue var3 = this.b.evaluateScript(var1, var2);
      return var3 == null ? null : new JsValue(this, var3);
   }

   public void evaluateScriptAsync(String var1, final android.webkit.ValueCallback<JsValue> var2, URL var3) {
      android.webkit.ValueCallback var4;
      if (var2 == null) {
         var4 = null;
      } else {
         var4 = new android.webkit.ValueCallback<IX5JsValue>() {
            public void a(IX5JsValue var1) {
               var2.onReceiveValue(var1 == null ? null : new JsValue(JsContext.this, var1));
            }

            // $FF: synthetic method
            public void onReceiveValue(IX5JsValue var1) {
               this.a(var1);
            }
         };
      }

      this.b.evaluateScriptAsync(var1, var4, var3);
   }

   public JsContext.ExceptionHandler exceptionHandler() {
      return this.c;
   }

   public String name() {
      return this.d;
   }

   public void removeJavascriptInterface(String var1) {
      this.b.removeJavascriptInterface(var1);
   }

   public void setExceptionHandler(JsContext.ExceptionHandler var1) {
      this.c = var1;
      if (var1 == null) {
         this.b.setExceptionHandler((android.webkit.ValueCallback)null);
      } else {
         this.b.setExceptionHandler(new android.webkit.ValueCallback<IX5JsError>() {
            public void a(IX5JsError var1) {
               JsContext.this.c.handleException(JsContext.this, new JsError(var1));
            }

            // $FF: synthetic method
            public void onReceiveValue(IX5JsError var1) {
               this.a((IX5JsError)var1);
            }
         });
      }

   }

   public void setName(String var1) {
      this.d = var1;
      this.b.setName(var1);
   }

   public void stealValueFromOtherCtx(String var1, JsContext var2, String var3) {
      this.b.stealValueFromOtherCtx(var1, var2.b, var3);
   }

   public int getNativeBufferId() {
      return this.b.getNativeBufferId();
   }

   public byte[] getNativeBuffer(int var1) {
      return this.b.getNativeBuffer(var1);
   }

   public int setNativeBuffer(int var1, byte[] var2) {
      return this.b.setNativeBuffer(var1, var2);
   }

   public JsVirtualMachine virtualMachine() {
      return this.a;
   }

   public static JsContext current() {
      return (JsContext)X5JsCore.a();
   }

   public interface ExceptionHandler {
      void handleException(JsContext var1, JsError var2);
   }
}
