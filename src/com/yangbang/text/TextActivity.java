package com.yangbang.text;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.yangbang.Constant;
import com.yangbang.xiaohua.R;

public class TextActivity extends SherlockFragmentActivity {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportFragmentManager().findFragmentById(android.R.id.content) == null) {

            int position = getIntent().getIntExtra(Constant.position,0);
            ArrayListFragment list = new ArrayListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(Constant.position,position);
            list.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().add(android.R.id.content,list,"ArrayListFragment").commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }


	
	
}
