package com.yangbang.text;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.view.Menu;
import com.baidu.mobads.InterstitialAd;
import com.baidu.mobads.InterstitialAdListener;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.ActivityDemo;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.xiaohua.R;

public class TextDetailActivity extends ActivityDemo {

    private FadingActionBarHelper mFadingHelper;
    public InterstitialAd interAd;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(MainApp.Debug)Log.e("TextDetailActivity","onCreate");
        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {

            if(MainApp.Debug)Log.e("TextDetailActivity","android.R.id.content==null");
            TextDetailFragment list = new TextDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.position, getIntent().getIntExtra(Constant.position, 0));
            bundle.putInt(Constant.position2,getIntent().getIntExtra(Constant.position2,0));
            bundle.putInt(Constant.position3,getIntent().getIntExtra(Constant.position3,0));

            String type = getIntent().getStringExtra(Constant.type);

            if(type==null){

            }else{
                bundle.putString(Constant.type,"");
            }

            list.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(android.R.id.content,list,"ArrayListFragment").commit();
        }

        int actionBarBg = R.drawable.ab_background_light;

        interAd=new InterstitialAd(this);
        interAd.setListener(new InterstitialAdListener(){

            @Override
            public void onAdClick(InterstitialAd arg0) {
                Log.i("InterstitialAd","onAdClick");
            }

            @Override
            public void onAdDismissed() {
                Log.i("InterstitialAd","onAdDismissed");
                interAd.loadAd();
            }

            @Override
            public void onAdFailed(String arg0) {
                Log.i("InterstitialAd","onAdFailed");
            }

            @Override
            public void onAdPresent() {
                Log.i("InterstitialAd","onAdPresent");
            }

            @Override
            public void onAdReady() {
                Log.i("InterstitialAd","onAdReady");
            }

        });
        interAd.loadAd();


     /*   mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.activity_listview)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(MainApp.Debug) Log.e("text6","onCreateOptionsMenu TextActivity");
        return false;
    }




}
