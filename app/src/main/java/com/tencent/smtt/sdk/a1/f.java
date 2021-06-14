package com.tencent.smtt.sdk.a1;

import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public class f {
   private static String a = "EmergencyManager";
   private final File b;
   private final FileOutputStream c;
   private final FileLock d;

   private f(File var1, FileOutputStream var2, FileLock var3) {
      this.b = var1;
      this.c = var2;
      this.d = var3;
   }

   public static f a(File var0) {
      FileOutputStream var1 = null;

      f var3;
      try {
         var1 = new FileOutputStream(var0);
         FileLock var2 = var1.getChannel().tryLock();
         if (var2 == null) {
            return null;
         }

         TbsLog.i(a, "Created lock file: " + var0.getAbsolutePath());
         var3 = new f(var0, var1, var2);
      } catch (Throwable var14) {
         TbsLog.e(a, "Failed to try to acquire lock: " + var0.getAbsolutePath() + " error: " + var14.getMessage());
         return null;
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var13) {
               TbsLog.e(a, "Failed to close: " + var13.getMessage());
            }
         }

      }

      return var3;
   }

   public void a() throws IOException {
      TbsLog.i(a, "Deleting lock file: " + this.b.getAbsolutePath());
      this.d.release();
      this.c.close();
      if (!this.b.delete()) {
         throw new IOException("Failed to delete lock file: " + this.b.getAbsolutePath());
      }
   }

   public void b() {
      try {
         this.a();
      } catch (IOException var2) {
      }

   }
}
