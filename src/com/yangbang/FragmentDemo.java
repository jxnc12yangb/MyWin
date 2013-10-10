package com.yangbang;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yangbang.xiaohua.R;

/**
 * Created by yangbang on 13-7-23.
 * Fragment 样版 抽象类
 */
public abstract class FragmentDemo extends Fragment{

    protected Button mBack;
    protected Button mTitle;
    protected Button mNext;

    protected String TAG;
    protected boolean DEBUG = true;

    protected void Loge(String string) {
        Log.e(TAG, string);
    }

    protected void ToastS(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_SHORT).show();
    }

    protected void ToastL(String string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getName();
        initViews();
        initEvents();
        initData();

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return  initViews(inflater,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEvents();
        initData();
    }

    /**
     * 初始化view
     */
    protected abstract View initViews(LayoutInflater inflater,ViewGroup container);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化事件
     */
    protected abstract void initEvents();

}
