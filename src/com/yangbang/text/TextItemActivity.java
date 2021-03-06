package com.yangbang.text;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.ActivityDemo;
import com.yangbang.Constant;
import com.yangbang.MainApp;

/**
 * Created by yangbang on 13-10-23.
 */
public class TextItemActivity extends ActivityDemo {



    public FadingActionBarHelper mFadingHelper;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*int actionBarBg =  R.drawable.ab_background_light;

        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.activity_listview)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);
        this.getActionBar().setDisplayHomeAsUpEnabled(true);

        mFadingHelper.initActionBar(this);*/


        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {

            int position = getIntent().getIntExtra(Constant.position,0);
            int position2 = getIntent().getIntExtra(Constant.position2,0);

            SherlockFragment list = null;
            Bundle bundle = new Bundle();

            list = new ListDataItemFragment();
            bundle.putInt(Constant.position,position);
            bundle.putInt(Constant.position2,position2);

            list.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(android.R.id.content,list,"ListDataItemFragment").commit();

        }
        //   int actionBarBg = R.drawable.ab_background_light;

     /*   mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.activity_listview)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(MainApp.Debug) Log.e("text6", "onCreateOptionsMenu TextActivity");
        return false;
    }






}
