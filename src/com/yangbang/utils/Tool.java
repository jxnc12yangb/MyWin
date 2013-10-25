package com.yangbang.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.Toast;

import com.yangbang.xiaohua.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tool {
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);

		return m.matches();
	}

	public static boolean isEmail(String strEmail) {
		String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strEmail);
		return m.matches();
	}

    /**
     * list 以逗号隔开字符串
     * @param list
     * @return
     */
    public static String listToString(List list) {
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    sb.append(list.get(i) + ",");
                } else {
                    sb.append(list.get(i));
                }
            }
        }
        return sb.toString();
    }

    // 是否全是数字
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// 是否有网络
	public static boolean hasNetWork(Context con) {
		boolean flag = true;
		ConnectivityManager connectivityManager = (ConnectivityManager) con
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null || !networkInfo.isAvailable()) {
			Toast.makeText(con, "没有网络", Toast.LENGTH_LONG).show();
			flag = false;
		}
		return flag;
	}

	// 是否有wify
	public boolean isWiFiActive(Context con) {
		ConnectivityManager connectivity = (ConnectivityManager) con
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getTypeName().equals("WIFI")
							&& info[i].isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean isEmptyText(Context context, String tip, String mName) {

		if (mName == null || mName.equals("")) {

			Toast.makeText(context, tip, Toast.LENGTH_LONG).show();

			return true;

		} else {
			return false;
		}

	}

	/**
	 * 检查是否存在SDCard
	 * 
	 * @return
	 */
	public static boolean hasSdcard() {
		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

    /**
     * 在状态栏显示通知
     */
    public static void showNotification(Context context,Class classs){
        // 创建一个NotificationManager的引用
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);

        String[] tips = context.getResources().getStringArray(R.array.tip);

        int id = 0 + (int)(Math.random() * 10);

        // 定义Notification的各种属性
        Notification notification =new Notification(R.drawable.ic_launcher,
                "笑你妹妹:"+tips[id], System.currentTimeMillis());
        //FLAG_AUTO_CANCEL   该通知能被状态栏的清除按钮给清除掉
        //FLAG_NO_CLEAR      该通知不能被状态栏的清除按钮给清除掉
        //FLAG_ONGOING_EVENT 通知放置在正在运行
        //FLAG_INSISTENT     是否一直进行，比如音乐一直播放，知道用户响应
      /*  notification.flags |= Notification.FLAG_ONGOING_EVENT; // 将此通知放到通知栏的"Ongoing"即"正在运行"组中
        notification.flags |= Notification.FLAG_NO_CLEAR; // 表明在点击了通知栏中的"清除通知"后，此通知不清除，经常与FLAG_ONGOING_EVENT一起使用*/
        notification.flags |= Notification.FLAG_SHOW_LIGHTS|Notification.DEFAULT_SOUND;
        //DEFAULT_ALL     使用所有默认值，比如声音，震动，闪屏等等
        //DEFAULT_LIGHTS  使用默认闪光提示
        //DEFAULT_SOUNDS  使用默认提示声音
        //DEFAULT_VIBRATE 使用默认手机震动，需加上<uses-permission android:name="android.permission.VIBRATE" />权限
        notification.defaults = Notification.DEFAULT_LIGHTS;
        //叠加效果常量
        //notification.defaults=Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND;
        notification.ledARGB = Color.BLUE;
        notification.ledOnMS =5000; //闪光时间，毫秒

        // 设置通知的事件消息
        CharSequence contentTitle ="笑你妹妹"; // 通知栏标题



        CharSequence contentText = tips[id]; // 通知栏内容
        Intent notificationIntent =new Intent(context,classs); // 点击该通知后要跳转的Activity
        PendingIntent contentItent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentItent);

        // 把Notification传递给NotificationManager
        notificationManager.notify(0, notification);
    }

    //删除通知
    public static void clearNotification(Context context){
        // 启动后删除之前我们定义的通知
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

    }

}
