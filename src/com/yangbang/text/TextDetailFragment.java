package com.yangbang.text;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.yangbang.Constant;
import com.yangbang.FragmentDemo;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.DataProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;

import java.util.Random;

/**10-12 15:24:26.895: E/AndroidRuntime(14711): Caused by: java.lang.ClassNotFoundException: com.yangbang.MiLaucherActivity

 * Created by yangbang on 13-10-9.
 */
public class TextDetailFragment extends FragmentDemo implements View.OnClickListener {
    private TextView textView;
    private int position;
    private int position2;
    private int position3;
    private ImageView left;
    private ImageView middle;
    private ImageView right;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.text_detail, container, false);
        textView = (TextView) view.findViewById(R.id.textView);

        left = (ImageView)view.findViewById(R.id.left);
        middle = (ImageView)view.findViewById(R.id.middle);
        right = (ImageView)view.findViewById(R.id.right);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {



        MenuItem item;
        item = menu.add("Menu 1a");
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        item = menu.add("Menu 1b");
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    protected void initEvents() {
        left.setOnClickListener(this);
        middle.setOnClickListener(this);
        right.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        position = getArguments().getInt(Constant.position);
        position2 = getArguments().getInt(Constant.position2);
        position3 = getArguments().getInt(Constant.position3);

        DataPProperty dataPProperty = ItemParser.homeDatalist.get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);
        DataItem dataItem = dataProperty.getDataItems().get(position3);
        String content = dataItem.getContent();

        textView.setText(content);

        Random random = new Random();
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

        iconicFontDrawable2.setIntrinsicWidth(10);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            left.setBackgroundDrawable(iconicFontDrawable);
            middle.setBackgroundDrawable(iconicFontDrawable2);
            right.setBackgroundDrawable(iconicFontDrawable3);
        } else {
            left.setBackground(iconicFontDrawable);
            middle.setBackground(iconicFontDrawable2);
            right.setBackground(iconicFontDrawable3);
        }

    }

    public void previous(){

        DataPProperty dataPProperty = ItemParser.homeDatalist.get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);

        if(position3>0){

            position3 -= 1;

            DataItem dataItem = dataProperty.getDataItems().get(position3);
            String content = dataItem.getContent();

            textView.setText(content);
        }else{

            ToastS("已经是第一页");

        }

    }

    public void next(){

        DataPProperty dataPProperty = ItemParser.homeDatalist.get(position);
        DataProperty dataProperty = dataPProperty.getDataProperties().get(position2);

        if((position3)<dataPProperty.getDataProperties().size()-1){

            position3 += 1;

            DataItem dataItem = dataProperty.getDataItems().get(position3);
            String content = dataItem.getContent();

            textView.setText(content);
        }else{

            ToastS("已经是最后一页");
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
                break;
        }
    }
}
