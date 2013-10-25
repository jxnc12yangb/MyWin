package com.yangbang.xiaohua;

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

import java.util.ArrayList;

public class DateAdapter extends BaseAdapter {

    private Context context;
	private ArrayList<String> lstDate;
	private TextView txtAge;
    private ImageView image;

    private Icon[] icons = {EntypoIcon.ADDRESS,EntypoIcon.MAP,EntypoIcon.TOOLS,EntypoIcon.LANGUAGE,EntypoIcon.USERS,EntypoIcon.NEW,EntypoIcon.USER, FontAwesomeIcon.GITHUB};


    public DateAdapter(Context mContext, ArrayList<String> list) {
		this.context = mContext;
		lstDate = list;


	}

	@Override
	public int getCount() {
		return lstDate.size();
	}

	@Override
	public Object getItem(int position) {
		return lstDate.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void exchange(int startPosition, int endPosition) {
		Object endObject = getItem(endPosition);
		Object startObject = getItem(startPosition);
		lstDate.add(startPosition, (String) endObject);
		lstDate.remove(startPosition + 1);
		lstDate.add(endPosition, (String) startObject);
		lstDate.remove(endPosition + 1);
	}

    private IconicFontDrawable getDrawable(int position){


        IconicFontDrawable iconicFontDrawable = new IconicFontDrawable(context);

        iconicFontDrawable.setIconColor(Color.WHITE);

        iconicFontDrawable.setIntrinsicWidth(10);

        iconicFontDrawable.setIcon(icons[position]);
        return iconicFontDrawable;
    }



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
		txtAge = (TextView) convertView.findViewById(R.id.txt_userAge);
        image = (ImageView)convertView.findViewById(R.id.image);


		if(lstDate.get(position)==null){
			txtAge.setText("+");
			txtAge.setBackgroundResource(R.drawable.red);
            image.setVisibility(View.INVISIBLE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                image.setBackgroundDrawable(null);
            } else {
                image.setBackground(null);
            }
		}
		else if(lstDate.get(position).equals("none")){
			txtAge.setText("");
            image.setVisibility(View.INVISIBLE);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                image.setBackgroundDrawable(null);
                txtAge.setBackgroundDrawable(null);
            } else {
                image.setBackground(null);
                txtAge.setBackground(null);
            }
		}else {


            txtAge.setText(lstDate.get(position));
            image.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                image.setBackgroundDrawable(getDrawable(position));
            } else {
                image.setBackground(getDrawable(position));
            }

        }
		return convertView;
	}

}
