package com.yangbang;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.yangbang.service.DaemonService;

import java.util.List;

/**
 * Created by yangbang on 13-7-26. Activity 样版 抽象类
 */
public abstract class ActivityDemo extends SherlockFragmentActivity {



	protected String TAG;
	protected boolean DEBUG = true;

	protected void Loge(String string) {
		Log.e(TAG, string);
	}

	protected void ToastS(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}

	protected void ToastL(String string) {  
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();

        if (!isAppOnForeground()) {
            //app 进入后台

            //全局变量isActive = false 记录当前已经进入后台
            MainApp.isActive = false;
            if(MainApp.Debug)Log.e("service", "ActivityDemo:onStop");
            Intent intent = new Intent(this, DaemonService.class);
            startService(intent);


        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        MainApp.isActive = true;

        if(MainApp.isActive&&!DaemonService.Showing){
            Intent intent = new Intent(this,DaemonService.class);
            startService(intent);
        }
        //if (!isActive) {
        //app 从后台唤醒，进入前台

        //isActive = true;
        //}
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the
        // device

        ActivityManager activityManager = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getName();
		initViews();
		initEvents();
		initData();
	}


	*//**
	 * 初始化view
	 *//*
	protected abstract void initViews();

	*//**
	 * 初始化数据
	 *//*
	protected abstract void initData();

	*//**
	 * 初始化事件
	 *//*
	protected abstract void initEvents();*/
}
