package com.tencent.smtt.sdk.a1;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class d {
   private int a;
   private long b;
   private List<Command> c;

   private d() {
   }

   public static d a(String var0) {
      d var1 = null;
      if (var0 != null) {
         try {
            JSONObject var2 = new JSONObject(var0);
            var1 = new d();
            var1.a = var2.optInt("ret_code", -1);
            var1.b = var2.optLong("next_req_interval", 1000L);
            JSONArray var3 = var2.optJSONArray("cmds");
            if (var3 != null) {
               var1.c = new ArrayList();

               for(int var4 = 0; var4 < var3.length(); ++var4) {
                  JSONObject var5 = var3.optJSONObject(var4);
                  Command var6 = Command.getCommand(var5);
                  if (var6 != null) {
                     var1.c.add(var6);
                  }
               }
            }
         } catch (JSONException var7) {
            var7.printStackTrace();
         }
      }

      return var1;
   }

   public int a() {
      return this.a;
   }

   public long b() {
      return this.b;
   }

   public List<Command> c() {
      return this.c;
   }
}
