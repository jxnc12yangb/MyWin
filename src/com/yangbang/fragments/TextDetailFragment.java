package com.yangbang.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yangbang.Constant;
import com.yangbang.FragmentDemo;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.DataProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;

/**
 * Created by yangbang on 13-10-9.
 */
public class TextDetailFragment extends FragmentDemo{
    private TextView textView;
    private int position;
    private int position2;
    private int position3;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.text_detail, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvents() {

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

    }
}
