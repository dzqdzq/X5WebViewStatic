package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.Build.VERSION;
import android.util.Log;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsLogReport;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

@SuppressLint({"NewApi"})
public class FileUtil {
   private static final int c = "lib/".length();
   public static String a = null;
   private static RandomAccessFile d = null;
   public static final FileUtil.a b = new FileUtil.a() {
      public boolean a(File var1, File var2) {
         return var1.length() == var2.length() && var1.lastModified() == var2.lastModified();
      }
   };

   public static String a(Context var0, int var1) {
      return a(var0, var0.getApplicationInfo().packageName, var1, true);
   }

   public static String a(Context var0, String var1, int var2, boolean var3) {
      if (var0 == null) {
         return "";
      } else {
         String var4 = "";

         try {
            var4 = Environment.getExternalStorageDirectory() + File.separator;
         } catch (Exception var8) {
            var8.printStackTrace();
         }

         switch(var2) {
         case 1:
            return var4.equals("") ? var4 : var4 + "tencent" + File.separator + "tbs" + File.separator + var1;
         case 2:
            return var4.equals("") ? var4 : var4 + "tbs" + File.separator + "backup" + File.separator + var1;
         case 3:
            return var4.equals("") ? var4 : var4 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + var1;
         case 4:
            if (var4.equals("")) {
               return b(var0, "backup");
            }

            String var5 = var4 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + var1;
            if (var3) {
               File var6 = new File(var5);
               if (!var6.exists() || !var6.canWrite()) {
                  if (!var6.exists()) {
                     var6.mkdirs();
                     boolean var7 = var6.canWrite();
                     if (!var7) {
                        var5 = b(var0, "backup");
                     }
                  } else {
                     var5 = b(var0, "backup");
                  }
               }
            }

            return var5;
         case 5:
            return var4.equals("") ? var4 : var4 + "tencent" + File.separator + "tbs" + File.separator + var1;
         case 6:
            if (a != null) {
               return a;
            }

            a = b(var0, "tbslog");
            return a;
         case 7:
            return var4.equals("") ? var4 : var4 + "tencent" + File.separator + "tbs" + File.separator + "backup" + File.separator + "core";
         case 8:
            return b(var0, "env");
         default:
            return "";
         }
      }
   }

   private static String b(Context var0, String var1) {
      String var2 = "";
      if (var0 == null) {
         return var2;
      } else {
         Context var3 = var0.getApplicationContext();
         if (var3 == null) {
            var3 = var0;
         }

         try {
            var2 = var3.getExternalFilesDir(var1).getAbsolutePath();
         } catch (Throwable var7) {
            var7.printStackTrace();

            try {
               var2 = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "data" + File.separator + var3.getApplicationInfo().packageName + File.separator + "files" + File.separator + var1;
            } catch (Exception var6) {
               var6.printStackTrace();
               return "";
            }
         }

         return var2;
      }
   }

   public static boolean a(Context var0) {
      if (VERSION.SDK_INT < 23) {
         return true;
      } else {
         boolean var1 = false;
         if (var0 != null) {
            var1 = var0.getApplicationContext().checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
         }

         return var1;
      }
   }

   public static boolean unzip(File var0, File var1) throws Exception {
      return a(var0.getPath(), var1.getPath());
   }

   @SuppressLint({"InlinedApi"})
   public static boolean a(String var0, String var1) throws Exception {
      String var2 = Build.CPU_ABI;
      String var3 = VERSION.SDK_INT >= 8 ? Build.CPU_ABI2 : null;
      String var4 = PropertyUtils.getQuickly("ro.product.cpu.upgradeabi", "armeabi");
      return a(var0, var1, var2, var3, var4);
   }

   private static boolean a(String var0, final String var1, String var2, String var3, String var4) throws Exception {
      return a(var0, var2, var3, var4, new FileUtil.b() {
         public boolean a(InputStream var1x, ZipEntry var2, String var3) throws Exception {
            try {
               return FileUtil.b(var1x, var2, var1, var3);
            } catch (Exception var5) {
               throw new Exception("copyFileIfChanged Exception", var5);
            }
         }
      });
   }

   private static boolean a(String var0, String var1, String var2, String var3, FileUtil.b var4) throws Exception {
      ZipFile var5 = null;

      try {
         var5 = new ZipFile(var0);
         boolean var6 = false;
         boolean var7 = false;
         Enumeration var8 = var5.entries();

         while(var8.hasMoreElements()) {
            ZipEntry var9 = (ZipEntry)var8.nextElement();
            String var10 = var9.getName();
            if (var10 != null && !var10.contains("../") && (var10.startsWith("lib/") || var10.startsWith("assets/"))) {
               String var11 = var10.substring(var10.lastIndexOf(47));
               if (var11.endsWith(".so")) {
                  if (var10.regionMatches(c, var1, 0, var1.length()) && var10.charAt(c + var1.length()) == '/') {
                     var6 = true;
                  } else if (var2 != null && var10.regionMatches(c, var2, 0, var2.length()) && var10.charAt(c + var2.length()) == '/') {
                     var7 = true;
                     if (var6) {
                        continue;
                     }
                  } else if (var3 == null || !var10.regionMatches(c, var3, 0, var3.length()) || var10.charAt(c + var3.length()) != '/' || var6 || var7) {
                     continue;
                  }
               }

               InputStream var12 = var5.getInputStream(var9);

               try {
                  if (!var4.a(var12, var9, var11.substring(1))) {
                     boolean var13 = false;
                     return var13;
                  }
               } finally {
                  if (var12 != null) {
                     var12.close();
                  }

               }
            }
         }

         return true;
      } finally {
         if (var5 != null) {
            var5.close();
         }

      }
   }

   @SuppressLint({"NewApi"})
   private static boolean b(InputStream var0, ZipEntry var1, String var2, String var3) throws Exception {
      mkdirs(new File(var2));
      String var4 = var2 + File.separator + var3;
      File var5 = new File(var4);
      FileOutputStream var6 = null;

      try {
         var6 = new FileOutputStream(var5);
         byte[] var7 = new byte[8192];

         int var8;
         while((var8 = var0.read(var7)) > 0) {
            var6.write(var7, 0, var8);
         }
      } catch (IOException var12) {
         b(var5);
         throw new IOException("Couldn't write dst file " + var5, var12);
      } finally {
         if (var6 != null) {
            var6.close();
         }

      }

      if (a(var4, var1.getSize(), var1.getTime(), var1.getCrc())) {
         TbsLog.e("FileHelper", "file is different: " + var4);
         return false;
      } else {
         if (!var5.setLastModified(var1.getTime())) {
            TbsLog.e("FileHelper", "Couldn't set time for dst file " + var5);
         }

         return true;
      }
   }

   private static boolean a(String var0, long var1, long var3, long var5) throws Exception {
      File var7 = new File(var0);
      if (var7.length() != var1) {
         TbsLog.e("FileHelper", "file size doesn't match: " + var7.length() + " vs " + var1);
         return true;
      } else {
         FileInputStream var8 = null;

         boolean var14;
         try {
            var8 = new FileInputStream(var7);
            CRC32 var9 = new CRC32();
            byte[] var10 = new byte[8192];

            int var11;
            while((var11 = var8.read(var10)) > 0) {
               var9.update(var10, 0, var11);
            }

            long var12 = var9.getValue();
            TbsLog.i("FileHelper", "" + var7.getName() + ": crc = " + var12 + ", zipCrc = " + var5);
            if (var12 == var5) {
               return false;
            }

            var14 = true;
         } finally {
            if (var8 != null) {
               var8.close();
            }

         }

         return var14;
      }
   }

   public static boolean b(File var0, File var1) throws Exception {
      return a(var0, var1, (FileFilter)null);
   }

   public static boolean a(File var0, File var1, FileFilter var2) throws Exception {
      return a(var0, var1, var2, b);
   }

   public static boolean a(File var0, File var1, FileFilter var2, FileUtil.a var3) throws Exception {
      if (var0 != null && var1 != null) {
         if (!var0.exists()) {
            return false;
         } else if (var0.isFile()) {
            return b(var0, var1, var2, var3);
         } else {
            File[] var4 = var0.listFiles(var2);
            if (var4 == null) {
               return false;
            } else {
               boolean var5 = true;
               File[] var6 = var4;
               int var7 = var4.length;

               for(int var8 = 0; var8 < var7; ++var8) {
                  File var9 = var6[var8];
                  if (!a(var9, new File(var1, var9.getName()), var2)) {
                     var5 = false;
                  }
               }

               return var5;
            }
         }
      } else {
         return false;
      }
   }

   private static boolean b(File var0, File var1, FileFilter var2, FileUtil.a var3) throws Exception {
      if (var0 != null && var1 != null) {
         if (var2 != null && !var2.accept(var0)) {
            return false;
         } else {
            FileChannel var4 = null;
            FileChannel var5 = null;

            try {
               boolean var15;
               if (!var0.exists() || !var0.isFile()) {
                  var15 = false;
                  return var15;
               } else {
                  if (var1.exists()) {
                     if (var3 != null && var3.a(var0, var1)) {
                        var15 = true;
                        return var15;
                     }

                     b(var1);
                  }

                  File var6 = var1.getParentFile();
                  if (var6.isFile()) {
                     b(var6);
                  }

                  if (!var6.exists() && !var6.mkdirs()) {
                     boolean var16 = false;
                     return var16;
                  } else {
                     var4 = (new FileInputStream(var0)).getChannel();
                     var5 = (new FileOutputStream(var1)).getChannel();
                     long var7 = var4.size();
                     long var9 = var5.transferFrom(var4, 0L, var7);
                     if (var9 == var7) {
                        return true;
                     } else {
                        b(var1);
                        boolean var11 = false;
                        return var11;
                     }
                  }
               }
            } finally {
               if (var4 != null) {
                  var4.close();
               }

               if (var5 != null) {
                  var5.close();
               }

            }
         }
      } else {
         return false;
      }
   }

   public static boolean mkdirs(File var0) {
      if (var0 == null) {
         return false;
      } else if (var0.exists() && var0.isDirectory()) {
         return true;
      } else {
         b(var0);
         return var0.mkdirs();
      }
   }

   public static void b(File var0) {
      a(var0, false);
   }

   public static void a(File var0, boolean var1) {
      a(var0, var1, false);
   }

   public static void a(File var0, boolean var1, boolean var2) {
      TbsLog.i("FileUtils", "delete file,ignore=" + var1 + "isSoftLink=" + var2);
      if (var0 != null) {
         if (var2 || var0.exists()) {
            if ((!var2 || var0.isDirectory()) && !var0.isFile()) {
               File[] var3 = var0.listFiles();
               if (var3 != null) {
                  File[] var4 = var3;
                  int var5 = var3.length;

                  for(int var6 = 0; var6 < var5; ++var6) {
                     File var7 = var4[var6];
                     a(var7, var1, var2);
                  }

                  if (!var1) {
                     var0.delete();
                  }

               }
            } else {
               var0.delete();
            }
         }
      }
   }

   public static void a(File var0, boolean var1, String var2) {
      TbsLog.i("FileUtils", "delete file,ignore=" + var1 + "except" + var2 + var0 + Log.getStackTraceString(new Throwable()));
      if (var0 != null && var0.exists()) {
         if (var0.isFile()) {
            var0.delete();
         } else {
            File[] var3 = var0.listFiles();
            if (var3 != null) {
               File[] var4 = var3;
               int var5 = var3.length;

               for(int var6 = 0; var6 < var5; ++var6) {
                  File var7 = var4[var6];
                  if (!var7.getName().equals(var2)) {
                     a(var7, var1);
                  }
               }

               if (!var1) {
                  var0.delete();
               }

            }
         }
      }
   }

   public static boolean c(File var0) {
      return var0 != null && var0.exists() && var0.isFile() && var0.length() > 0L;
   }

   public static long a(InputStream var0, OutputStream var1) throws IOException, OutOfMemoryError {
      if (var0 == null) {
         return -1L;
      } else {
         byte[] var2 = new byte[4096];
         long var3 = 0L;

         int var6;
         for(boolean var5 = false; -1 != (var6 = var0.read(var2)); var3 += (long)var6) {
            var1.write(var2, 0, var6);
         }

         return var3;
      }
   }

   public static int copy(InputStream var0, OutputStream var1) throws IOException, OutOfMemoryError {
      long var2 = a(var0, var1);
      return var2 > 2147483647L ? -1 : (int)var2;
   }

   public static FileOutputStream d(File var0) throws IOException {
      if (var0.exists()) {
         if (var0.isDirectory()) {
            throw new IOException("File '" + var0 + "' exists but is a directory");
         }

         if (!var0.canWrite()) {
            throw new IOException("File '" + var0 + "' cannot be written to");
         }
      } else {
         File var1 = var0.getParentFile();
         if (var1 != null && !var1.exists() && !var1.mkdirs()) {
            throw new IOException("File '" + var0 + "' could not be created");
         }
      }

      return new FileOutputStream(var0);
   }

   public static boolean b(Context var0) {
      long var1 = p.a();
      boolean var3 = var1 >= TbsDownloadConfig.getInstance(var0).getDownloadMinFreeSpace();
      if (!var3) {
         TbsLog.e("TbsDownload", "[TbsApkDwonloader.hasEnoughFreeSpace] freeSpace too small,  freeSpace = " + var1);
      }

      return var3;
   }

   public static String c(Context var0) {
      return Environment.getExternalStorageDirectory() + File.separator + "tbs" + File.separator + "file_locks";
   }

   static String d(Context var0) {
      File var1 = QbSdk.getTbsFolderDir(var0);
      File var2 = new File(var1, "core_private");
      if (var2 != null) {
         if (!var2.isDirectory()) {
            boolean var3 = var2.mkdir();
            if (!var3) {
               return null;
            }
         }

         return var2.getAbsolutePath();
      } else {
         return null;
      }
   }

   public static File a(Context var0, boolean var1, String var2) {
      String var3 = null;
      if (var1) {
         var3 = d(var0);
      } else {
         var3 = c(var0);
      }

      File var4 = null;
      File var5 = null;
      if (var3 == null) {
         return null;
      } else {
         var4 = new File(var3);
         if (var4 != null && !var4.exists()) {
            var4.mkdirs();
         }

         if (!var4.canWrite()) {
            return null;
         } else {
            var5 = new File(var4, var2);
            if (!var5.exists()) {
               try {
                  var5.createNewFile();
               } catch (IOException var7) {
                  var7.printStackTrace();
                  return null;
               }
            }

            return var5;
         }
      }
   }

   public static File a(Context var0, String var1) {
      File var2 = var0.getFilesDir();
      File var3 = new File(var2, "tbs");
      File var5 = null;
      if (var3 == null) {
         return null;
      } else {
         if (!var3.exists()) {
            var3.mkdirs();
         }

         if (!var3.canWrite()) {
            TbsLog.e("FileHelper", "getPermanentTbsFile -- no permission!");
            return null;
         } else {
            var5 = new File(var3, var1);
            if (!var5.exists()) {
               try {
                  var5.createNewFile();
               } catch (IOException var7) {
                  TbsLog.e("FileHelper", "getPermanentTbsFile -- exception: " + var7);
                  return null;
               }
            }

            return var5;
         }
      }
   }

   public static FileOutputStream b(Context var0, boolean var1, String var2) {
      File var3 = a(var0, var1, var2);
      if (var3 != null) {
         try {
            return new FileOutputStream(var3);
         } catch (FileNotFoundException var5) {
            var5.printStackTrace();
         }
      }

      return null;
   }

   public static FileLock a(Context var0, FileOutputStream var1) {
      if (var1 == null) {
         return null;
      } else {
         FileLock var2 = null;

         try {
            var2 = var1.getChannel().tryLock();
            if (var2.isValid()) {
               return var2;
            }
         } catch (OverlappingFileLockException var4) {
            var4.printStackTrace();
         } catch (Exception var5) {
            var5.printStackTrace();
         }

         return null;
      }
   }

   public static void freeFileLock(FileLock var0, FileOutputStream var1) {
      if (var0 != null) {
         try {
            FileChannel var2 = var0.channel();
            if (var2 != null && var2.isOpen()) {
               var0.release();
            }
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }

      if (var1 != null) {
         try {
            var1.close();
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   public static FileLock e(Context var0) {
      TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #1");
      FileLock var1 = null;
      String var2 = "tbs_rename_lock";
      File var3 = a(var0, var2);
      TbsLog.i("FileHelper", "getTbsCoreLoadFileLock #4 " + var3);

      try {
         d = new RandomAccessFile(var3.getAbsolutePath(), "r");
         FileChannel var4 = d.getChannel();
         var1 = var4.tryLock(0L, Long.MAX_VALUE, true);
      } catch (Throwable var5) {
         TbsLog.e("FileHelper", "getTbsCoreLoadFileLock -- exception: " + var5);
      }

      if (var1 == null) {
         var1 = g(var0);
      }

      if (var1 == null) {
         TbsLog.i("FileHelper", "getTbsCoreLoadFileLock -- failed: " + var2);
      } else {
         TbsLog.i("FileHelper", "getTbsCoreLoadFileLock -- success: " + var2);
      }

      return var1;
   }

   private static FileLock g(Context var0) {
      FileLock var1 = null;

      try {
         TbsLogReport.TbsLogInfo var2 = TbsLogReport.getInstance(var0).tbsLogInfo();
         var2.setErrorCode(803);
         String var3 = "tbs_rename_lock";
         File var4 = a(var0, var3);
         if (TbsDownloadConfig.getInstance(var0).getTbsCoreLoadRenameFileLockWaitEnable()) {
            int var5;
            for(var5 = 0; var5 < 20 && var1 == null; ++var5) {
               try {
                  try {
                     Thread.sleep(100L);
                  } catch (Exception var7) {
                     var7.printStackTrace();
                  }

                  d = new RandomAccessFile(var4.getAbsolutePath(), "r");
                  FileChannel var6 = d.getChannel();
                  var1 = var6.tryLock(0L, Long.MAX_VALUE, true);
               } catch (Throwable var8) {
               }
            }

            if (var1 != null) {
               var2.setErrorCode(802);
            } else {
               var2.setErrorCode(801);
            }

            TbsLogReport.getInstance(var0).eventReport(TbsLogReport.EventType.TYPE_SDK_REPORT_INFO, var2);
            TbsLog.i("FileHelper", "getTbsCoreLoadFileLock,retry num=" + var5 + "success=" + (var1 == null));
         }
      } catch (Exception var9) {
         var9.printStackTrace();
      }

      return var1;
   }

   public static FileLock f(Context var0) {
      FileLock var1 = null;
      String var2 = "tbs_rename_lock";
      File var3 = a(var0, var2);
      TbsLog.i("FileHelper", "getTbsCoreRenameFileLock #1 " + var3);

      try {
         d = new RandomAccessFile(var3.getAbsolutePath(), "rw");
         FileChannel var4 = d.getChannel();
         var1 = var4.tryLock(0L, Long.MAX_VALUE, false);
      } catch (Throwable var5) {
         TbsLog.e("FileHelper", "getTbsCoreRenameFileLock -- excpetion: " + var2);
      }

      if (var1 == null) {
         TbsLog.i("FileHelper", "getTbsCoreRenameFileLock -- failed: " + var2);
      } else {
         TbsLog.i("FileHelper", "getTbsCoreRenameFileLock -- success: " + var2);
      }

      return var1;
   }

   public static synchronized void a(Context var0, FileLock var1) {
      TbsLog.i("FileHelper", "releaseTbsCoreRenameFileLock -- lock: " + var1);
      FileChannel var2 = var1.channel();
      if (var2 != null && var2.isOpen()) {
         try {
            var1.release();
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }

   }

   public interface b {
      boolean a(InputStream var1, ZipEntry var2, String var3) throws Exception;
   }

   public interface a {
      boolean a(File var1, File var2);
   }
}
