package com.yangbang.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yangbang.views.LoadingView;
import com.yangbang.xiaohua.R;

/**
 * Created by yangbang on 13-10-9.
 * 启动界面
 */
public class LoadingFragment extends Fragment {
    private View mLoadview;
    private LoadingView main_imageview;
    private Handler mHandler;

    public ILoadingState mILoadingState;

    public interface ILoadingState{
        public boolean getLoadingState();
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initLoadingImages();

        mILoadingState = (ILoadingState)getActivity();

        mHandler = new Handler();
        mHandler.postDelayed(runnable,1000);
        main_imageview.startAnim();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.loading,container,false);

        mLoadview = view.findViewById(R.id.loading);

        main_imageview = (LoadingView) view.findViewById(R.id.main_imageview);

        return view;

    }

    private int time = 3;


    /**
     * 确保loading 的时间 在3秒以上
     */
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            if(time<0 && mILoadingState.getLoadingState()==true){
                getFragmentManager().beginTransaction().remove(LoadingFragment.this).commitAllowingStateLoss();//移除loading 界面
            }else{
                mHandler.postDelayed(runnable,1000);
            }

        }
    };


    private void initLoadingImages() {
        int[] imageIds = new int[6];
        imageIds[0] = R.drawable.loader_frame_1;
        imageIds[1] = R.drawable.loader_frame_2;
        imageIds[2] = R.drawable.loader_frame_3;
        imageIds[3] = R.drawable.loader_frame_4;
        imageIds[4] = R.drawable.loader_frame_5;
        imageIds[5] = R.drawable.loader_frame_6;

        main_imageview.setImageIds(imageIds);
    }

}
