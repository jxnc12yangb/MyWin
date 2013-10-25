package com.yangbang.service;  
  
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;  
  
public class BootBroadcast extends BroadcastReceiver {  
  
    @Override  
    public void onReceive(Context context, Intent mintent) {  
  
        if (Intent.ACTION_BOOT_COMPLETED.equals(mintent.getAction())) {  
            // 启动完成  
            Intent intent = new Intent(context, Alarmreceiver.class);
            intent.setAction("arui.alarm.action");  
            PendingIntent sender = PendingIntent.getBroadcast(context, 0,  
                    intent, 0);  
            long firstime = SystemClock.elapsedRealtime();  
            AlarmManager am = (AlarmManager) context  
                    .getSystemService(Context.ALARM_SERVICE);  
  
            // 10秒一个周期，不停的发送广播  
            am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,  
                    10 * 1000, sender);

            // 启动完成
            Intent intent2 = new Intent(context, Alarmreceiver.class);
            intent2.setAction("arui.alarm.action2");
            PendingIntent sender2 = PendingIntent.getBroadcast(context, 0,
                    intent2, 0);
            long firstime2 = SystemClock.elapsedRealtime();
            AlarmManager am2 = (AlarmManager) context
                    .getSystemService(Context.ALARM_SERVICE);

            // 10秒一个周期，不停的发送广播
            am2.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime2+ 1000 * 24*60*60,
                    1000 * 24*60*60, sender2);


        }  
  
    }  
} 