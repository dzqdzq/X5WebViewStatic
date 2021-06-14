package com.tencent.smtt.sdk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Cookies {
   public static final String a;
   static File b;

   public static File a(Context var0) {
      if (b == null && var0 != null) {
         b = new File(var0.getDir("webview", 0), "Cookies");
      }

      if (b == null) {
         b = new File("/data/data/" + var0.getPackageName() + File.separator + "app_webview" + File.separator + "Cookies");
      }

      return b;
   }

   public static boolean b(Context var0) {
      if (var0 == null) {
         return false;
      } else {
         FileUtil.a(a(var0), false);
         return true;
      }
   }

   public static SQLiteDatabase c(Context var0) {
      if (var0 == null) {
         return null;
      } else {
         File var1 = a(var0);
         if (var1 == null) {
            return null;
         } else {
            SQLiteDatabase var2 = null;

            try {
               var2 = SQLiteDatabase.openDatabase(var1.getAbsolutePath(), (CursorFactory)null, 0);
            } catch (Exception var4) {
            }

            if (var2 == null) {
               TbsLog.i(a, "dbPath is not exist!");
            }

            return var2;
         }
      }
   }

   public static ArrayList<String> a(SQLiteDatabase var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList();
         String var2 = "select * from sqlite_master where type='table'";
         Cursor var3 = null;

         try {
            var3 = var0.rawQuery(var2, (String[])null);
            if (var3.moveToFirst()) {
               do {
                  String var4 = var3.getString(1);
                  String var5 = var3.getString(4);
                  var1.add(var4);
                  a(var0, var4);
               } while(var3.moveToNext());
            }
         } catch (Throwable var9) {
         } finally {
            if (var3 != null) {
               var3.close();
            }

            if (var0 != null && var0.isOpen()) {
               var0.close();
            }

         }

         return var1;
      }
   }

   private static String a(SQLiteDatabase var0, String var1) {
      String var2 = "select * from " + var1;
      Cursor var3 = var0.rawQuery(var2, (String[])null);
      int var4 = var3.getCount();
      int var5 = var3.getColumnCount();
      StringBuilder var6 = new StringBuilder();
      var6.append("raws:" + var4 + ",columns:" + var5 + "\n");
      if (var4 > 0 && var3.moveToFirst()) {
         do {
            var6.append("\n");

            for(int var7 = 0; var7 < var5; ++var7) {
               String var8 = null;

               try {
                  var8 = var3.getString(var7);
               } catch (Exception var10) {
                  continue;
               }

               var6.append(var8).append(",");
            }

            var6.append("\n");
         } while(var3.moveToNext());
      }

      return var6.toString();
   }

   public static int d(Context var0) {
      long var1 = System.currentTimeMillis();
      SQLiteDatabase var3 = null;
      Cursor var4 = null;
      int var5 = 0;

      try {
         var3 = c(var0);
         if (var3 == null) {
            byte var15 = -1;
            return var15;
         }

         String var6 = "select * from meta";
         var4 = var3.rawQuery(var6, (String[])null);
         int var7 = var4.getCount();
         int var8 = var4.getColumnCount();
         if (var7 > 0 && var4.moveToFirst()) {
            do {
               if (var4.getString(0).equals("version")) {
                  String var9 = var4.getString(1);
                  var5 = Integer.parseInt(var9);
                  break;
               }
            } while(var4.moveToNext());
         }
      } catch (Throwable var13) {
      } finally {
         if (var4 != null) {
            var4.close();
         }

         if (var3 != null && var3.isOpen()) {
            var3.close();
         }

      }

      return var5;
   }

   public static void a(Context var0, CookieManager.A1 var1, String var2, boolean var3, boolean var4) {
      if (var0 != null) {
         if (var1 != CookieManager.A1.b || !TextUtils.isEmpty(var2)) {
            String[] var5 = var2.split(",");
            if (var5 != null && var5.length >= 1) {
               SQLiteDatabase var6 = c(var0);
               if (var6 != null) {
                  Cursor var7 = null;
                  HashMap var8 = new HashMap();

                  String var11;
                  try {
                     String var9 = "select * from cookies";
                     var7 = var6.rawQuery(var9, (String[])null);
                     int var10 = var7.getCount();
                     if (var10 > 0 && var7.moveToFirst()) {
                        do {
                           var11 = var7.getString(var7.getColumnIndex("host_key"));
                           if (var1 == CookieManager.A1.b) {
                              boolean var12 = false;
                              String[] var13 = var5;
                              int var14 = var5.length;

                              for(int var15 = 0; var15 < var14; ++var15) {
                                 String var16 = var13[var15];
                                 if (var11.equals(var16)) {
                                    var12 = true;
                                    break;
                                 }
                              }

                              if (!var12) {
                                 continue;
                              }
                           }

                           StringBuilder var25 = new StringBuilder();
                           var25.append(var7.getString(var7.getColumnIndex("value")));
                           var25.append(";").append(var7.getString(var7.getColumnIndex("name")));
                           var25.append(";").append(var7.getInt(var7.getColumnIndex("expires_utc")));
                           var25.append(";").append(var7.getInt(var7.getColumnIndex("priority")));
                           String var26 = var25.toString();
                           var8.put(var11, var26);
                        } while(var7.moveToNext());
                     }
                  } catch (Throwable var20) {
                     Log.e(a, "getCookieDBVersion exception:" + var20.toString());
                  } finally {
                     if (var7 != null) {
                        var7.close();
                     }

                     if (var6 != null && var6.isOpen()) {
                        var6.close();
                     }

                  }

                  if (!var8.isEmpty()) {
                     b(var0);
                     Iterator var22 = var8.entrySet().iterator();

                     while(var22.hasNext()) {
                        Entry var24 = (Entry)var22.next();
                        var11 = (String)var24.getKey();
                        String var27 = (String)var24.getValue();
                        CookieManager.getInstance().setCookie(var11, var27, true);
                     }

                     if (VERSION.SDK_INT >= 21) {
                        CookieManager.getInstance().flush();
                     } else {
                        CookieSyncManager.getInstance().sync();
                     }

                     if (var3) {
                        a(c(var0));
                        int var23 = d(var0);
                        if (var23 != -1) {
                           CookieManager.getInstance();
                           CookieManager.setROMCookieDBVersion(var0, var23);
                        }
                     }

                  }
               }
            }
         }
      }
   }

   static {
      a = CookieManager.LOGTAG;
   }
}
