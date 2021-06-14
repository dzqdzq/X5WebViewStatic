package com.tencent.smtt.sdk.a1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
   public static String toLocaleString(long var0) {
      return String.format(Locale.getDefault(), "%d(%s)", var0, (new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault())).format(new Date(var0)));
   }
}
