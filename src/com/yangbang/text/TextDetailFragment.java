package com.yangbang.text;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.Icon;
import com.yangbang.Constant;
import com.yangbang.FragmentDemo;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.DataProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;
/*import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.Icon;*/

/**10-12 15:24:26.895: E/AndroidRuntime(14711): Caused by: java.lang.ClassNotFoundException: com.yangbang.MiLaucherActivity

 * Created by yangbang on 13-10-9.
 */
public class TextDetailFragment extends FragmentDemo{
    private TextView textView;
    private int position;
    private int position2;
    private int position3;
    private View left;
    private View middle;
    private View right;

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.text_detail, container, false);
        textView = (TextView) view.findViewById(R.id.textView);

        left = view.findViewById(R.id.left);
        middle = view.findViewById(R.id.middle);
        right = view.findViewById(R.id.right);

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


        List<Icon> icons = new ArrayList<Icon>();
        icons.addAll(Arrays.asList(EntypoIcon.values()));





        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(getActivity());
        iconicFontDrawable.setIcon(icons.get(0));
        iconicFontDrawable.setIconColor(Color.rgb(80,80,80));

        iconicFontDrawable.setIntrinsicWidth(70);



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            left.setBackgroundDrawable(iconicFontDrawable);
            middle.setBackgroundDrawable(iconicFontDrawable);
            right.setBackgroundDrawable(iconicFontDrawable);
        } else {
            left.setBackground(iconicFontDrawable);
            middle.setBackground(iconicFontDrawable);
            right.setBackground(iconicFontDrawable);
        }

    }
}
