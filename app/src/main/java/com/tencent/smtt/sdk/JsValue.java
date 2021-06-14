package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.nio.ByteBuffer;

public class JsValue {
   private final JsContext a;
   private final IX5JsValue b;

   protected static IX5JsValue.JsValueFactory a() {
      return new JsValue.a();
   }

   protected JsValue(JsContext var1, IX5JsValue var2) {
      this.a = var1;
      this.b = var2;
   }

   public boolean isUndefined() {
      return this.b.isUndefined();
   }

   public boolean isNull() {
      return this.b.isNull();
   }

   public boolean isArray() {
      return this.b.isArray();
   }

   public boolean isBoolean() {
      return this.b.isBoolean();
   }

   public boolean toBoolean() {
      return this.b.toBoolean();
   }

   public boolean isInteger() {
      return this.b.isInteger();
   }

   public int toInteger() {
      return this.b.toInteger();
   }

   public boolean isNumber() {
      return this.b.isNumber();
   }

   public Number toNumber() {
      return this.b.toNumber();
   }

   public boolean isString() {
      return this.b.isString();
   }

   public String toString() {
      return this.b.toString();
   }

   public boolean isObject() {
      return this.b.isObject();
   }

   public <T> T toObject(Class<T> var1) {
      return this.b.toObject(var1);
   }

   public boolean isJavascriptInterface() {
      return this.b.isJavascriptInterface();
   }

   public Object toJavascriptInterface() {
      return this.b.toJavascriptInterface();
   }

   public boolean isArrayBufferOrArrayBufferView() {
      return this.b.isArrayBufferOrArrayBufferView();
   }

   public ByteBuffer toByteBuffer() {
      return this.b.toByteBuffer();
   }

   public boolean isFunction() {
      return this.b.isFunction();
   }

   public JsValue call(Object... var1) {
      return this.a(this.b.call(var1));
   }

   public JsValue construct(Object... var1) {
      return this.a(this.b.construct(var1));
   }

   public boolean isPromise() {
      return this.b.isPromise();
   }

   public void resolve(Object var1) {
      this.b.resolveOrReject(var1, true);
   }

   public void reject(Object var1) {
      this.b.resolveOrReject(var1, false);
   }

   public JsContext context() {
      return this.a;
   }

   private JsValue a(IX5JsValue var1) {
      return var1 == null ? null : new JsValue(this.a, var1);
   }

   private static class a implements IX5JsValue.JsValueFactory {
      private a() {
      }

      public String getJsValueClassName() {
         return JsValue.class.getName();
      }

      public IX5JsValue unwrap(Object var1) {
         return var1 != null && var1 instanceof JsValue ? ((JsValue)var1).b : null;
      }

      public Object wrap(IX5JsValue var1) {
         if (var1 != null) {
            JsContext var2 = JsContext.current();
            if (var2 != null) {
               return new JsValue(var2, var1);
            }
         }

         return null;
      }

      // $FF: synthetic method
      a(Object var1) {
         this();
      }
   }
}
