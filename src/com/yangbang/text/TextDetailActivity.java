package com.yangbang.text;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.xiaohua.R;

public class TextDetailActivity extends SherlockFragmentActivity {

    private FadingActionBarHelper mFadingHelper;

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
            list.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(android.R.id.content,list,"ArrayListFragment").commit();
        }

        int actionBarBg = R.drawable.ab_background_light;

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
