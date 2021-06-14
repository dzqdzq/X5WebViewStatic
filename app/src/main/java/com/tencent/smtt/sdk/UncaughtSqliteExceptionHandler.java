package com.tencent.smtt.sdk;

import android.database.sqlite.SQLiteException;
import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtSqliteExceptionHandler implements UncaughtExceptionHandler {
   public void uncaughtException(Thread var1, Throwable var2) {
      if (!(var2 instanceof SQLiteException)) {
         throw new RuntimeException(var2);
      }
   }
}
