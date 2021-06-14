package com.tencent.smtt.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.smtt.sdk.QbSdk;
import java.io.File;

public class p {
   private static File a = null;

   public static long a() {
      File var0 = Environment.getDataDirectory();
      StatFs var1 = new StatFs(var0.getPath());
      long var2 = (long)var1.getBlockSize();
      long var4 = (long)var1.getAvailableBlocks();
      return var2 * var4;
   }

   @TargetApi(9)
   public static boolean a(Context var0) {
      if (var0 == null) {
         return false;
      } else {
         if (a == null) {
            try {
               if (!var0.getApplicationInfo().processName.contains("com.tencent.mm")) {
                  return false;
               }

               File var1 = null;
               var1 = QbSdk.getTbsFolderDir(var0);
               if (var1 == null || !var1.isDirectory()) {
                  return false;
               }

               File var2 = new File(var1, "share");
               if (var2 != null) {
                  if (!var2.isDirectory()) {
                     boolean var3 = var2.mkdir();
                     if (!var3) {
                        return false;
                     }
                  }

                  a = var2;
                  var2.setExecutable(true, false);
                  return true;
               }
            } catch (Exception var4) {
               var4.printStackTrace();
               return false;
            }
         }

         return true;
      }
   }
}
