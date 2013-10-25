package com.yangbang.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.yangbang.MainApp;
import com.yangbang.MiLaucherActivity;
import com.yangbang.utils.Tool;

public class Alarmreceiver extends BroadcastReceiver {  
    @Override  
    public void onReceive(Context context, Intent intent) {

        if(MainApp.Debug) Log.e("services", "Alarmreceiver:onReceive");

        if (intent.getAction().equals("arui.alarm.action")) {

         //   if(MainApp.Debug) Log.e("service", "Alarmreceiver:onReceive:arui.alarm.action");

            Intent i = new Intent();  
            i.setClass(context, DaemonService.class);  
            // 启动service   
            // 多次调用startService并不会启动多个service 而是会多次调用onStart  
            context.startService(i);
        }else if(intent.getAction().equals("arui.alarm.action2")){
            Tool.showNotification(context, MiLaucherActivity.class);
        }
    }  
} 