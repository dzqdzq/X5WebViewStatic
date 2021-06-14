package com.tencent.smtt.sdk.a1;

import org.json.JSONObject;

public class Command {
   private int id;
   private int cmd;
   private String extra;
   private long expiration;

   private Command() {
   }

   public int a() {
      return this.id;
   }

   public int b() {
      return this.cmd;
   }

   public String c() {
      return this.extra;
   }

   public long d() {
      return this.expiration;
   }

   public static Command getCommand(JSONObject var0) {
      Command var1 = null;
      if (var0 != null) {
         var1 = new Command();
         var1.id = var0.optInt("id", -1);
         var1.cmd = var0.optInt("cmd_id", -1);
         var1.extra = var0.optString("ext_params", "");
         var1.expiration = 1000L * var0.optLong("expiration", 0L);
      }

      return var1;
   }

   public String toString() {
      return "[id=" + this.id + ", cmd=" + this.cmd + ", extra='" + this.extra + '\'' + ", expiration=" + DateUtil.toLocaleString(this.expiration) + ']';
   }

   public boolean isExpired() {
      long var1 = System.currentTimeMillis();
      return var1 > this.expiration;
   }
}
