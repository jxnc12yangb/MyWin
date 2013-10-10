package com.yangbang.xiaohua;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.yangbang.xiaohua.R;

public class Animations{
	Animation DelDown,DelUp;
	public Animation getDownAnimation(Context context){
		return AnimationUtils.loadAnimation(context, R.anim.del_down);
	}
}