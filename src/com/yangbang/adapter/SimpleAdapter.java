package com.yangbang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.atermenji.android.iconicdroid.icon.Icon;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataItemPosition;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.utils.SharedPreferencesUtil;
import com.yangbang.xiaohua.R;

import java.util.List;
import java.util.Set;

/**
 * Created by yangbang on 13-10-17.
 */
public class SimpleAdapter extends BaseAdapter{  private final IconicFontDrawable iconicFontDrawable;
    private Context context;

    private Icon[] icons = {EntypoIcon.ADDRESS,EntypoIcon.USER,EntypoIcon.USERS,EntypoIcon.MAP,EntypoIcon.TOOLS,EntypoIcon.NEW,EntypoIcon.LANGUAGE, FontAwesomeIcon.GITHUB};
    private List<DataItemPosition> mDatas;


    public SimpleAdapter(Context context,List<DataItemPosition> dataItemPositionList){
        iconicFontDrawable = new IconicFontDrawable(context);

        iconicFontDrawable.setIconColor(Color.GREEN);

        iconicFontDrawable.setIntrinsicWidth(10);

        this.mDatas = dataItemPositionList;

    }

    public void remove(DataItemPosition item){
        mDatas.remove(item);
        notifyDataSetChanged();
    }

    public void insert(DataItemPosition item, int index) {
        mDatas.add(index,item);
        this.notifyDataSetChanged();
    }

    private IconicFontDrawable getDrawable(int position){
        iconicFontDrawable.setIcon(EntypoIcon.LEFT);
        return iconicFontDrawable;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mDatas.size();
    }

    @Override
    public DataItemPosition getItem(int position) {
        // TODO Auto-generated method stub
        return mDatas.get(position);
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.list_item_click_remove,null);

            TextView title = (TextView)convertView.findViewById(R.id.text);
            ImageView selectIV = (ImageView) convertView.findViewById(R.id.drag_handle);
            ImageView click_remove = (ImageView) convertView.findViewById(R.id.click_remove);

            viewHolder.textView = title;
            viewHolder.selectIV = selectIV;
            viewHolder.imageView = click_remove;

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.textView.setText(mDatas.get(position).getDataItem().getValue());
        viewHolder.imageView.setOnClickListener(new MyClick(mDatas.get(position)));

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            viewHolder.selectIV.setBackgroundDrawable(getDrawable(mDatas.get(position).position1));
        } else {
            viewHolder.selectIV.setBackground(getDrawable(mDatas.get(position).position1));
        }

        return convertView;
    }

    public class MyClick implements View.OnClickListener {

        private DataItemPosition dataItemPosition;

        public MyClick(DataItemPosition dataItemPosition){
            this.dataItemPosition = dataItemPosition;
        }

        @Override
        public void onClick(View v) {
            remove(dataItemPosition);

            Set<String> sets = SharedPreferencesUtil.getSetSharedPreferences(Constant.favs, null);

            List<DataPProperty> data = MainApp.getData();

            StringBuilder builder = new StringBuilder();
            builder.append(dataItemPosition.getPosition1()+","+dataItemPosition.getPosition2()+","+dataItemPosition.getPosition3());
            sets.remove(builder.toString());

            SharedPreferencesUtil.commitResult(Constant.favs,sets);

            Set<String> setss = SharedPreferencesUtil.getSetSharedPreferences(Constant.favs, null);
        }
    }

    private class ViewHolder {

        ImageView selectIV;
        TextView textView;
        ImageView imageView;

    }
}
