    package com.yangbang.service;

    import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.baidu.mobads.AdView;
import com.baidu.mobads.AdViewListener;
import com.yangbang.MainApp;
import com.yangbang.xiaohua.R;

import org.json.JSONObject;

    public class DaemonService extends Service {


        private WindowManager.LayoutParams wmParams;
        private WindowManager mWindowManager;
        private View mFloatLayout;

        @Override
        public IBinder onBind(Intent intent) {  
            return null;  
        }  
      
        @Override  
        public void onCreate() {  
            super.onCreate();  

            if(MainApp.Debug) Log.e("service", "DaemonService:onCreate");

        }  
      
        @Override  
        public void onStart(Intent intent, int startId) {  

           // if(MainApp.Debug) Log.e("service", "DaemonService:onStart");

            // 这里可以做Service该做的事
            if(MainApp.isActive && !Showing){
                showAdviews();
            }else if(!MainApp.isActive&&Showing){
                dismissAdviews();
            }


        }

        @Override
        public void onDestroy() {
            super.onDestroy();


        }

        public static boolean Showing = false;

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            return super.onStartCommand(intent, flags, startId);
        }

        public void dismissAdviews(){

            if(MainApp.Debug)Log.e("service", " dismissAdviews()");

            Showing = false;

            if(mFloatLayout != null)
            {
                //移除悬浮窗口
                if(mWindowManager!=null){
                    mWindowManager.removeView(mFloatLayout);
                    mFloatLayout = null;
                }

            }
        }

        public void showAdviews(){

            if(MainApp.Debug)Log.e("service", " showAdviews()");
            Showing = true;

            wmParams = new WindowManager.LayoutParams();
            //获取的是WindowManagerImpl.CompatModeWrapper
            mWindowManager = (WindowManager)getApplication().getSystemService(getApplication().WINDOW_SERVICE);
           // Log.i(TAG, "mWindowManager--->" + mWindowManager);
            //设置window type
            wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            //设置图片格式，效果为背景透明
            wmParams.format = PixelFormat.RGBA_8888;
            //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
            wmParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            //调整悬浮窗显示的停靠位置为左侧置顶
            wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
            // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
            wmParams.x = 0;
            wmParams.y = 0;

            //设置悬浮窗口长宽数据
            wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

         /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/

            LayoutInflater inflater = LayoutInflater.from(this);
            //获取浮动窗口视图所在布局
            mFloatLayout =  inflater.inflate(R.layout.adview, null);
            // 创建广告View
            AdView adView = new AdView(this);
            // 设置监听器
            adView.setListener(new AdViewListener() {
                public void onAdSwitch() {
                    Log.w("", "onAdSwitch");
                }
                public void onAdShow(JSONObject info) {
                    Log.w("", "onAdShow " + info.toString());
                }
                public void onAdReady(AdView adView) {
                    Log.w("", "onAdReady " + adView);
                }
                public void onAdFailed(String reason) {
                    Log.w("", "onAdFailed " + reason);
                }
                public void onAdClick(JSONObject info) {
                    Log.w("", "onAdClick " + info.toString());
                }
                public void onVideoStart() {
                    Log.w("", "onVideoStart");
                }
                public void onVideoFinish() {
                    Log.w("", "onVideoFinish");
                }
                @Override
                public void onVideoClickAd() {
                    Log.w("", "onVideoFinish");
                }
                @Override
                public void onVideoClickClose() {
                    Log.w("", "onVideoFinish");
                }
                @Override
                public void onVideoClickReplay() {
                    Log.w("", "onVideoFinish");
                }
                @Override
                public void onVideoError() {
                    Log.w("", "onVideoFinish");
                }
            });
            //获取浮动窗口视图所在布局
         //   mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
            //添加mFloatLayout
            mWindowManager.addView(mFloatLayout, wmParams);
            //浮动窗口按钮

            mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                    .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            if(MainApp.Debug) Log.e("service", "Width/2--->" + mFloatLayout.getMeasuredWidth()/2);
            if(MainApp.Debug) Log.e("service", "Height/2--->" + mFloatLayout.getMeasuredHeight()/2);
            //Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredWidth()/2);
            //Log.i(TAG, "Height/2--->" + mFloatView.getMeasuredHeight()/2);
        }
    }  