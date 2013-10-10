package com.yangbang;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yangbang on 13-7-26. Activity 样版 抽象类
 */
public abstract class ActivityDemo extends Activity {



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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TAG = this.getClass().getName();
		initViews();
		initEvents();
		initData();
	}


	/**
	 * 初始化view
	 */
	protected abstract void initViews();

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 初始化事件
	 */
	protected abstract void initEvents();
}
