package com.yangbang.text;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import com.yangbang.Constant;
import com.yangbang.FragmentActivityDemo;
import com.yangbang.fragments.ArrayListFragment;

public class TextActivity extends FragmentActivityDemo{

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
	protected void initViews() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub
		
	}
	
	
}
