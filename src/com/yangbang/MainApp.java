package com.yangbang;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.yangbang.service.Alarmreceiver;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.utils.SharedPreferencesUtil;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangbang on 13-10-9.
 */
public class MainApp extends Application{

    private static MainApp globalContext;

    public static List<DataPProperty> homeDatalists = new ArrayList();

    public static ProgressDialog progressDialog = null;

    public static boolean isActive = false;

    public static int[] drawables = {
            R.layout.header_light1,R.layout.header_light2,R.layout.header_light3,R.layout.header_light4,R.layout.header_light5,
            R.layout.header_light6,R.layout.header_light7,R.layout.header_light8,R.layout.header_light9,R.layout.header_light10,
            R.layout.header_light11,R.layout.header_light12,R.layout.header_light13,R.layout.header_light14,R.layout.header_light15,
            R.layout.header_light16,R.layout.header_light17,R.layout.header_light18,R.layout.header_light19,R.layout.header_light20,
            R.layout.header_light21,R.layout.header_light22,R.layout.header_light23,R.layout.header_light24,R.layout.header_light25,
            R.layout.header_light26,R.layout.header_light27
    };

    private static int id;

    public static int count;

    public static int getDrawable(){

        id++;

        int temp = id%20;

        int result = drawables[temp];

        return result;
    }

    public static int getDrawableNotAdd(){

        int temp = id%20;

        int result = drawables[temp];

        return result;
    }

    public static List<DataPProperty> getData(){

        if(homeDatalists.size()==0){

            progressDialog.show();
            ItemParser itemParser = new ItemParser();
            itemParser.parse(globalContext);

            progressDialog.dismiss();
        }

        return homeDatalists;
    }

    public static List<DataPProperty> getDatas(){


        return homeDatalists;
    }


    public static boolean Debug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        Intent intent = new Intent(this, Alarmreceiver.class);
        intent.setAction("arui.alarm.action");
        PendingIntent sender = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        long firstime = SystemClock.elapsedRealtime();
        AlarmManager am = (AlarmManager) this
                .getSystemService(Context.ALARM_SERVICE);

        // 10秒一个周期，不停的发送广播
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstime,
                10 * 1000, sender);


        SharedPreferences sharedPreferences = getSharedPreferences("sp",MODE_APPEND|MODE_WORLD_WRITEABLE);

        SharedPreferencesUtil.setSharedPreferences(sharedPreferences);

        globalContext = this;

        progressDialog = new ProgressDialog(this);
    }

    public static MainApp getInstance() {
        return globalContext;
    }
}
