package com.tencent.smtt.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AppUtil {
   public static String a = "";
   public static String b = "";
   public static String c = "";
   public static String d = "";
   public static String e = "";
   public static String f = "";
   private static boolean g = false;
   private static boolean h = false;
   private static boolean i = false;
   private static boolean j = false;
   private static boolean k = false;

   public static String a(Context var0) {
      String var1 = null;

      try {
         var1 = var0.getPackageName();
      } catch (Exception var3) {
      }

      return var1;
   }

   public static int b(Context var0) {
      return VERSION.SDK_INT;
   }

   public static String a() {
      if (!k) {
         String var0 = Build.MODEL;

         try {
            f = new String(var0.getBytes("UTF-8"), "ISO8859-1");
         } catch (Exception var2) {
            f = var0;
         }

         k = true;
      }

      return f;
   }

   public static String c(Context var0) {
      String var1 = null;

      try {
         String var2 = var0.getPackageName();
         PackageManager var3 = var0.getPackageManager();
         PackageInfo var4 = var3.getPackageInfo(var2, 0);
         var1 = var4.versionName;
      } catch (Exception var5) {
      }

      return var1;
   }

   public static int d(Context var0) {
      int var1 = 0;

      try {
         String var2 = var0.getPackageName();
         PackageManager var3 = var0.getPackageManager();
         PackageInfo var4 = var3.getPackageInfo(var2, 0);
         var1 = var4.versionCode;
      } catch (Exception var5) {
      }

      return var1;
   }

   public static String a(Context var0, String var1) {
      String var2 = null;

      try {
         String var3 = var0.getPackageName();
         PackageManager var4 = var0.getPackageManager();
         ApplicationInfo var5 = var4.getApplicationInfo(var3, 128);
         var2 = String.valueOf(var5.metaData.get(var1));

         try {
            int var6 = Integer.parseInt(var2);
            var2 = String.valueOf(Integer.toHexString(var6));
         } catch (Exception var7) {
         }
      } catch (Exception var8) {
      }

      return var2;
   }

   public static void saveGuid(Context var0, String var1) {
      Log.d("0816", "saveGuid guid is " + var1);

      try {
         TbsDownloadConfig var2 = TbsDownloadConfig.getInstance(var0);
         var2.mSyncMap.put("tbs_guid", var1);
         var2.commit();
      } catch (Exception var3) {
      }

   }

   public static String getGuid(Context var0) {
      String var1 = "";

      try {
         TbsDownloadConfig var2 = TbsDownloadConfig.getInstance(var0);
         var1 = var2.mPreferences.getString("tbs_guid", "");
      } catch (Exception var3) {
      }

      Log.d("0816", "getGuid guid is " + var1);
      return var1;
   }

   public static boolean getImeiEnable(final Context var0) {
      boolean var1 = false;

      try {
         SharedPreferences var3 = var0.getSharedPreferences("sai", 0);
         var1 = var3.getBoolean("gi", false);
         Log.d("AppUtil", "getImeiEnable is " + var1);
         TbsLog.i("AppUtil", "getImeiEnable is " + var1);
         com.tencent.smtt.sdk.c var4 = com.tencent.smtt.sdk.c.a();
         var4.a(var0, 1001, new com.tencent.smtt.sdk.c.a() {
            public void a(String var1) {
               SharedPreferences var2 = var0.getSharedPreferences("sai", 0);
               Editor var3 = var2.edit();
               var3.putBoolean("gi", true);
               var3.commit();
               TbsLog.e("TBSEmergency", "Execute command [1001](" + var1 + ")");
            }
         });
      } catch (Throwable var5) {
         TbsLog.i("AppUtil", "stack is " + Log.getStackTraceString(var5));
      }

      return var1;
   }

   public static String getImei(Context var0) {
      if (!QbSdk.isEnableSensitiveApi()) {
         TbsLog.i("AppUtil", "getImei isEnableSensitiveApi = false");
         return "";
      } else {
         if (!g) {
            try {
               TelephonyManager var1 = (TelephonyManager)var0.getSystemService("phone");
               a = var1.getDeviceId();
            } catch (Exception var2) {
               TbsLog.i(var2);
            }

            g = true;
         }

         return a;
      }
   }

   public static String getImsi(Context var0) {
      if (!QbSdk.isEnableSensitiveApi()) {
         TbsLog.i("AppUtil", "getImsi isEnableSensitiveApi = false");
         return "";
      } else {
         if (!h) {
            try {
               TelephonyManager var1 = (TelephonyManager)var0.getSystemService("phone");
               b = var1.getSubscriberId();
            } catch (Exception var2) {
               TbsLog.i(var2);
            }

            h = true;
         }

         return b;
      }
   }

   public static String getCpuArch() {
      if (!i) {
         String var0 = null;
         InputStreamReader var1 = null;
         BufferedReader var2 = null;

         try {
            Process var3 = Runtime.getRuntime().exec("getprop ro.product.cpu.abi");
            var1 = new InputStreamReader(var3.getInputStream());
            var2 = new BufferedReader(var1);
            String var4 = var2.readLine();
            if (var4.contains("x86")) {
               var0 = toString("i686");
            } else {
               var0 = toString(System.getProperty("os.arch"));
            }
         } catch (Throwable var17) {
            var0 = toString(System.getProperty("os.arch"));
            var17.printStackTrace();
         } finally {
            if (TextUtils.isEmpty(var0)) {
               c = var0;
            }

            try {
               if (var2 != null) {
                  var2.close();
               }
            } catch (IOException var16) {
            }

            try {
               if (var1 != null) {
                  var1.close();
               }
            } catch (IOException var15) {
            }

         }

         i = true;
      }

      return c;
   }

   public static String i(Context var0) {
      return "02:00:00:00:00:00";
   }

   private static String toString(String var0) {
      return var0 == null ? "" : var0;
   }

   public static String getAndroidID(Context var0) {
      if (!QbSdk.isEnableSensitiveApi()) {
         TbsLog.i("AppUtil", "getAndroidID isEnableSensitiveApi = false");
         return "";
      } else {
         if (!j) {
            try {
               e = Secure.getString(var0.getContentResolver(), "android_id");
            } catch (Exception var2) {
               var2.printStackTrace();
            }

            j = true;
         }

         return e;
      }
   }

   public static String getSignatureFromApk(Context var0, boolean var1, File var2) {
      String var3 = "";
      if (var2 != null && var2.exists()) {
         if (var1) {
            label131: {
               RandomAccessFile var4 = null;

               String var7;
               try {
                  byte[] var5 = new byte[2];
                  var4 = new RandomAccessFile(var2, "r");
                  var4.read(var5);
                  String var6 = new String(var5);
                  if (var6.equalsIgnoreCase("PK")) {
                     break label131;
                  }

                  var7 = "";
               } catch (Exception var20) {
                  var20.printStackTrace();
                  break label131;
               } finally {
                  try {
                     var4.close();
                  } catch (IOException var18) {
                     var18.printStackTrace();
                  }

               }

               return var7;
            }
         }

         try {
            if (var0.getApplicationContext().getPackageName().contains("com.jd.jrapp")) {
               TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #1");
               var3 = a(var2);
               if (var3 != null) {
                  TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #2");
                  return var3;
               }
            }
         } catch (Throwable var19) {
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #3");
         }

         TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  #4");
         var3 = getSign(var0, var2);
         TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  android api signature=" + var3);
         if (var3 == null) {
            var3 = a(var2);
            TbsLog.i("AppUtil", "[AppUtil.getSignatureFromApk]  java get signature=" + var3);
         }

         return var3;
      } else {
         return "";
      }
   }

   private static String getSign(Context var0, File var1) {
      String var2 = null;

      try {
         PackageInfo var3 = null;
         var3 = var0.getPackageManager().getPackageArchiveInfo(var1.getAbsolutePath(), 65);
         Signature var4 = null;
         if (var3 != null) {
            if (var3.signatures != null && var3.signatures.length > 0) {
               var4 = var3.signatures[0];
            } else {
               TbsLog.w("AppUtil", "[getSignatureFromApk] pkgInfo is not null BUT signatures is null!");
            }
         }

         if (var4 != null) {
            var2 = var4.toCharsString();
         }
      } catch (Exception var5) {
         TbsLog.i("AppUtil", "getSign " + var1 + "failed");
      }

      return var2;
   }

   private static String a(File var0) {
      String var1 = null;

      try {
         JarFile var2 = new JarFile(var0);
         JarEntry var3 = var2.getJarEntry("AndroidManifest.xml");
         byte[] var4 = new byte[8192];
         Certificate[] var5 = getCertificates(var2, var3, var4);
         var1 = a(var5[0].getEncoded());
         Enumeration var6 = var2.entries();
         String var7 = null;
         Certificate[] var8 = null;

         while(var6.hasMoreElements()) {
            JarEntry var9 = (JarEntry)var6.nextElement();
            String var10 = var9.getName();
            if (var10 != null) {
               var8 = getCertificates(var2, var9, var4);
               var7 = null;
               if (var8 != null) {
                  var7 = a(var8[0].getEncoded());
               }

               if (var7 == null) {
                  if (!var10.startsWith("META-INF/")) {
                     var1 = null;
                     break;
                  }
               } else {
                  boolean var11 = var7.equals(var1);
                  if (!var11) {
                     var1 = null;
                     break;
                  }
               }
            }
         }
      } catch (Exception var12) {
         var1 = null;
         var12.printStackTrace();
      }

      return var1;
   }

   private static Certificate[] getCertificates(JarFile var0, JarEntry var1, byte[] var2) throws Exception {
      InputStream var3 = var0.getInputStream(var1);

      while(var3.read(var2, 0, var2.length) != -1) {
      }

      var3.close();
      return var1 != null ? var1.getCertificates() : null;
   }

   private static String a(byte[] var0) {
      byte[] var1 = var0;
      int var2 = var0.length;
      int var3 = var2 * 2;
      char[] var4 = new char[var3];

      for(int var5 = 0; var5 < var2; ++var5) {
         byte var6 = var1[var5];
         int var7 = var6 >> 4 & 15;
         var4[var5 * 2] = (char)(var7 >= 10 ? 97 + var7 - 10 : 48 + var7);
         var7 = var6 & 15;
         var4[var5 * 2 + 1] = (char)(var7 >= 10 ? 97 + var7 - 10 : 48 + var7);
      }

      return new String(var4);
   }

   public static boolean is64Bit() {
      try {
         if (VERSION.SDK_INT < 21) {
            return false;
         }

         Class var0 = Class.forName("dalvik.system.VMRuntime");
         if (var0 == null) {
            return false;
         }

         Method var1 = var0.getDeclaredMethod("getRuntime");
         if (var1 == null) {
            return false;
         }

         Object var2 = var1.invoke((Object)null);
         if (var2 == null) {
            return false;
         }

         Method var3 = var0.getDeclaredMethod("is64Bit");
         if (var3 == null) {
            return false;
         }

         Object var4 = var3.invoke(var2);
         if (var4 instanceof Boolean) {
            return (Boolean)var4;
         }
      } catch (Throwable var5) {
         var5.printStackTrace();
      }

      return false;
   }
}
