package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.tencent.smtt.export.external.DexLoader;

class t extends FrameLayout implements OnErrorListener {
   private Object a;
   private TbsPlayerProxy b;
   private VideoView c;
   private Context d = null;
   private String e;

   public t(Context var1) {
      super(var1.getApplicationContext());
      this.d = var1;
   }

   void a(Bundle var1, Object var2) {
      this.b(var1, var2);
   }

   private void b(Bundle var1, Object var2) {
      this.a();
      boolean var3 = false;
      if (this.b()) {
         var1.putInt("callMode", var1.getInt("callMode"));
         var3 = this.b.a(this.a, var1, this, var2);
      }

      if (!var3) {
         if (this.c != null) {
            this.c.stopPlayback();
         }

         if (this.c == null) {
            this.c = new VideoView(this.getContext());
         }

         this.e = var1.getString("videoUrl");
         this.c.setVideoURI(Uri.parse(this.e));
         this.c.setOnErrorListener(this);
         Intent var4 = new Intent("com.tencent.smtt.tbs.video.PLAY");
         var4.addFlags(268435456);
         Context var5 = this.getContext().getApplicationContext();
         var4.setPackage(var5.getPackageName());
         var5.startActivity(var4);
      }

   }

   void a() {
      this.setBackgroundColor(-16777216);
      if (this.b == null) {
         SDKEngine.a(true).init(this.getContext().getApplicationContext(), false, false);
         TbsWizard var1 = SDKEngine.a(true).a();
         DexLoader var2 = null;
         if (var1 != null) {
            var2 = var1.b();
         }

         if (var2 != null && QbSdk.canLoadVideo(this.getContext())) {
            this.b = new TbsPlayerProxy(var2);
         }
      }

      if (this.b != null && this.a == null) {
         this.a = this.b.a(this.getContext().getApplicationContext());
      }

   }

   public boolean b() {
      return this.b != null && this.a != null;
   }

   public void a(Activity var1) {
      if (!this.b() && this.c != null) {
         if (this.c.getParent() == null) {
            Window var2 = var1.getWindow();
            FrameLayout var3 = (FrameLayout)var2.getDecorView();
            var2.addFlags(1024);
            var2.addFlags(128);
            var3.setBackgroundColor(-16777216);
            MediaController var4 = new MediaController(var1);
            var4.setMediaPlayer(this.c);
            this.c.setMediaController(var4);
            LayoutParams var5 = new LayoutParams(-1, -1);
            var5.gravity = 17;
            var3.addView(this.c, var5);
         }

         if (VERSION.SDK_INT >= 8) {
            this.c.start();
         }
      }

   }

   void a(Activity var1, int var2) {
      if (var2 == 3 && !this.b() && this.c != null) {
         this.c.pause();
      }

      if (var2 == 4) {
         this.d = null;
         if (!this.b() && this.c != null) {
            this.c.stopPlayback();
            this.c = null;
         }
      }

      if (var2 == 2 && !this.b()) {
         this.d = var1;
         this.a(var1);
      }

      if (this.b()) {
         this.b.a(this.a, var1, var2);
      }

   }

   public boolean onError(MediaPlayer var1, int var2, int var3) {
      try {
         if (this.d instanceof Activity) {
            Activity var4 = (Activity)this.d;
            if (!var4.isFinishing()) {
               var4.finish();
            }
         }

         Context var8 = this.getContext();
         if (var8 != null) {
            Toast.makeText(var8, "播放失败，请选择其它播放器播放", 1).show();
            Context var5 = var8.getApplicationContext();
            Intent var6 = new Intent("android.intent.action.VIEW");
            var6.addFlags(268435456);
            var6.setDataAndType(Uri.parse(this.e), "video/*");
            var5.startActivity(var6);
         }

         return true;
      } catch (Throwable var7) {
         return false;
      }
   }

   public void c() {
      if (this.b()) {
         this.b.onUserStateChanged(this.a);
      }

   }
}
