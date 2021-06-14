package com.tencent.smtt.sdk;

import com.tencent.smtt.export.external.jscore.interfaces.IX5JsError;

public class JsError {
   private final IX5JsError a;

   protected JsError(IX5JsError var1) {
      this.a = var1;
   }

   public String getMessage() {
      return this.a.getMessage();
   }

   public String getStack() {
      return this.a.getStack();
   }
}
