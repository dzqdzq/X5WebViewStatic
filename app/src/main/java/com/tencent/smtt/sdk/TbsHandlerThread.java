package com.tencent.smtt.sdk;

import android.os.HandlerThread;

class n extends HandlerThread {
   private static n a;

   public n(String var1) {
      super(var1);
   }

   public static synchronized n a() {
      if (a == null) {
         a = new n("TbsHandlerThread");
         a.start();
      }

      return a;
   }
}
