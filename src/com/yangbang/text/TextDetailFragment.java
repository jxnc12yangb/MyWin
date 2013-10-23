package com.yangbang.text;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.baidu.mobads.InterstitialAd;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.Constant;
import com.yangbang.FragmentDemo;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.DataProperty;
import com.yangbang.utils.SharedPreferencesUtil;
import com.yangbang.xiaohua.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**10-12 15:24:26.895: E/AndroidRuntime(14711): Caused by: java.lang.ClassNotFoundException: com.yangbang.MiLaucherActivity

 * Created by yangbang on 13-10-9.
 */
@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TextDetailFragment extends FragmentDemo implements View.OnClickListener {
    private TextView textView;
    private int position;
    private int position2;
    private int position3;
    private ImageView left;
    private ImageView middle;
    private ImageView right;
    private MenuItem itemPage;
    private Bundle mArguments;
    private FadingActionBarHelper mFadingHelper;
    private ImageView middle2;
    private String type;
    private View actionL;
    private String TAG = "TextDetailFragment";
    private String content;
    private InterstitialAd interAd;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {

        View view =  inflater.inflate(R.layout.text_detail,container,false);
        textView = (TextView) view.findViewById(R.id.textView);

        left = (ImageView)view.findViewById(R.id.left);
        middle = (ImageView)view.findViewById(R.id.middle);
        right = (ImageView)view.findViewById(R.id.right);
        middle2 = (ImageView)view.findViewById(R.id.middle2);

        actionL = view.findViewById(R.id.actionL);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    public String replaceBlank(String str) {
                String dest = "";
               if (str!=null) {
                       Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                       Matcher m = p.matcher(str);
                        dest = m.replaceAll("");
                    }
                return dest;
            }

    @SuppressLint("NewApi")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
    protected void initData() {


        position = getArguments().getInt(Constant.position);
        position2 = getArguments().getInt(Constant.position2);
        position3 = getArguments().getInt(Constant.position3);
        type = getArguments().getString(Constant.type);

        if(type!=null){
            actionL.setVisibility(View.GONE);
        }

        if(MainApp.Debug)Log.e("TextDetailFragment", "onActivityCreated" +position+","+position2+","+position3);

        DataPProperty dataPProperty = MainApp.getData().get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);
        DataItem dataItem = dataProperty.getDataItems().get(position3);
        content = dataItem.getContent();

        textView.setText(content);


       // itemPage.setTitle((position3+1)+"/"+dataProperty.getDataItems().size());


        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(getActivity());
        iconicFontDrawable.setIcon(EntypoIcon.LEFT);
        iconicFontDrawable.setIconColor(Color.GREEN);

        iconicFontDrawable.setIntrinsicWidth(10);

        IconicFontDrawable iconicFontDrawable2 = new IconicFontDrawable(getActivity());
        iconicFontDrawable2.setIcon(EntypoIcon.SHARE);
        iconicFontDrawable2.setIconColor(Color.GREEN);

        IconicFontDrawable iconicFontDrawable3 = new IconicFontDrawable(getActivity());
        iconicFontDrawable3.setIcon(EntypoIcon.RIGHT);
        iconicFontDrawable3.setIconColor(Color.GREEN);

        IconicFontDrawable iconicFontDrawable4 = new IconicFontDrawable(getActivity());
        iconicFontDrawable4.setIcon(FontAwesomeIcon.HEART_EMPTY);
        iconicFontDrawable4.setIconColor(Color.GREEN);


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            left.setBackgroundDrawable(iconicFontDrawable);
            middle.setBackgroundDrawable(iconicFontDrawable2);
            right.setBackgroundDrawable(iconicFontDrawable3);
            middle2.setBackgroundDrawable(iconicFontDrawable4);
        } else {
            left.setBackground(iconicFontDrawable);
            middle.setBackground(iconicFontDrawable2);
            right.setBackground(iconicFontDrawable3);
            middle2.setBackground(iconicFontDrawable4);
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getSherlockActivity().getActionBar().setDisplayHomeAsUpEnabled(true);



        if(type==null){
            itemPage = menu.add("Menu 1a");
            itemPage.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

            DataPProperty dataPProperty = MainApp.getData().get(position);
            DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);

            itemPage.setTitle((position3+1)+"/"+dataProperty.getDataItems().size());

            getSherlockActivity().getActionBar().setTitle(MainApp.getData().get(position).getDataProperties().get(position2).getDataItems().get(position3).getValue());

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
            /*FragmentManager fm = getSherlockActivity().getSupportFragmentManager();

            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack(fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getId(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);

                *//*fmsetCustomAnimations(R.anim.fragment_slide_left_enter,
                        R.anim.fragment_slide_left_exit,
                        R.anim.fragment_slide_right_enter,
                        R.anim.fragment_slide_right_exit)*//*
            }*/

            getActivity().finish();

            return true;

        }else if(item.getItemId()==itemPage.getItemId()){

        }

        return false;
    }

    public void shareText(String title, String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, title);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, title));
    }


    @Override
    protected void initEvents() {
        left.setOnClickListener(this);
        middle.setOnClickListener(this);
        right.setOnClickListener(this);
        middle2.setOnClickListener(this);
    }

    public void showAd(){

        MainApp.count++;

        if(interAd!=null&&getActivity()!=null){
            if(MainApp.count>=30){
                if(interAd.isAdReady()){
                    interAd.showAd(getActivity());
                    MainApp.count=0;
                }else{
                    interAd.loadAd();
                }
            }
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mArguments = getArguments();

        TextDetailActivity activity1 = (TextDetailActivity) activity;
        interAd = activity1.interAd;

        showAd();
        //  int actionBarBg = mArguments != null ? R.drawable.ab_background_light : R.drawable.ab_background_light;



     /*   mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.text_detail)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);
        getSherlockActivity().getActionBar().setDisplayHomeAsUpEnabled(true);


        mFadingHelper.initActionBar(activity);*/

    }


    public void previous(){


        Log.e("text6", "TextDetailFragment" +position+","+position2+","+position3);

        DataPProperty dataPProperty = MainApp.getData().get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);

        if(position3>0){

            showAd();
            position3 -= 1;

            DataItem dataItem = dataProperty.getDataItems().get(position3);
            String content = dataItem.getContent();

            textView.setText(content);

            itemPage.setTitle((position3+1)+"/"+dataProperty.getDataItems().size());

            getSherlockActivity().getActionBar().setTitle(MainApp.getData().get(position).getDataProperties().get(position2).getDataItems().get(position3).getValue());
        }else{

          //  ToastS("已经是第一页");

        }

    }

    public void next(){



        DataPProperty dataPProperty = MainApp.getData().get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);

        Log.e("text6", "TextDetailFragment" +position+","+position2+","+position3);

        if((position3)<dataProperty.getDataItems().size()-1){

            showAd();

            position3 += 1;

            DataItem dataItem = dataProperty.getDataItems().get(position3);
            content = dataItem.getContent();

            textView.setText(content);

            itemPage.setTitle((position3+1)+"/"+dataProperty.getDataItems().size());

            getSherlockActivity().getActionBar().setTitle(MainApp.getData().get(position).getDataProperties().get(position2).getDataItems().get(position3).getValue());
        }else{

         //   ToastS("已经是最后一页");
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.left:
                previous();
                break;
            case R.id.right:
                next();
                break;
            case R.id.middle:
                shareText("笑你妹妹","笑你妹妹："+content);
                break;
            case R.id.middle2:
                String sets = SharedPreferencesUtil.getSharedPreferences(Constant.favs,null);
                StringBuilder builder = new StringBuilder();


                if(sets==null|| sets.equals("")){
                    builder.append(position+":"+position2+":"+position3);
                }else{
                    builder.append(sets);

                    builder.append(",");
                    builder.append(position+":"+position2+":"+position3);
                }


                SharedPreferencesUtil.commitResult(Constant.favs,builder.toString());

                ToastS("收藏成功");
                break;
        }
    }
}
