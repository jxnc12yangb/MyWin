package com.yangbang.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.atermenji.android.iconicdroid.icon.FontAwesomeIcon;
import com.atermenji.android.iconicdroid.icon.Icon;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataProperty;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;


public class ArrayListFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private List<DataProperty> mDatas;
    private List<String> mValues = new ArrayList<String>();
    private int position ;
    private ListView listView;
    private Bundle mArguments;
    private FadingActionBarHelper mFadingHelper;


    public static final String ARG_IMAGE_RES = "image_source";
    public static final String ARG_ACTION_BG_RES = "image_action_bs_res";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        /*TextActivity dd = (TextActivity)activity;
        mFadingHelper = dd.mFadingHelper;*/
        mArguments = getArguments();

        position = mArguments.getInt(Constant.position);
        int actionBarBg =  R.drawable.ab_background_light;


        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(MainApp.getDrawable())
                .contentLayout(R.layout.activity_listview)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);
       // this.getActionBar().setDisplayHomeAsUpEnabled(true);



        mFadingHelper.initActionBar(activity);

    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = mFadingHelper.createView(inflater);
        listView = (ListView) view.findViewById(android.R.id.list);


        if (mArguments != null){
            ImageView img = (ImageView) view.findViewById(R.id.image_header);
            img.setImageResource(mArguments.getInt(ARG_IMAGE_RES));
        }

        return view;
    }

    private Icon[] icons = {EntypoIcon.ADDRESS,EntypoIcon.MAP,EntypoIcon.TOOLS,EntypoIcon.LANGUAGE,EntypoIcon.USERS,EntypoIcon.NEW,EntypoIcon.USER, FontAwesomeIcon.GITHUB};

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

    }

    private void initData() {

        mDatas = MainApp.getData().get(position).getDataProperties();

        if(mArguments!=null)
        for(DataProperty dataProperty:mDatas){
            mValues.add(dataProperty.getValue());
        }


/*        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mValues);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.activity_list_item,, mValues);
        listView.setAdapter(new MyAdater());

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        getSherlockActivity().getActionBar().setTitle(MainApp.getData().get(position).getValue());
        getSherlockActivity().getActionBar().setDisplayHomeAsUpEnabled(true);


        super.onCreateOptionsMenu(menu, inflater);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e("text6","onOptionsItemSelecteddfdfdfd"+"");

        if ((item.getItemId() == android.R.id.home || item.getItemId() == 0) && getFragmentManager().getBackStackEntryCount()==0) {

            getActivity().finish();

            return true;
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
      /*  ListDataItemFragment listDataItemFragment = new ListDataItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.position,this.position);
        bundle.putInt(Constant.position2,position-1);
        listDataItemFragment.setArguments(bundle);*/

        Intent intent = new Intent(getActivity(),TextItemActivity.class);



        intent.putExtra(Constant.position,this.position);
        intent.putExtra(Constant.position2,position-1);

        startActivity(intent);

        //overridePendingTransition(int enterAnim,int exitAnim);

     /*   getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit).addToBackStack(null).add(R.id.content,listDataItemFragment,"ListDataItemFragment").commit();*/
    }

    public class MyAdater extends BaseAdapter {

        private final IconicFontDrawable iconicFontDrawable;
        private Context context;

        public MyAdater(){
            iconicFontDrawable = new IconicFontDrawable(getActivity());
            iconicFontDrawable.setIcon(icons[position]);
            iconicFontDrawable.setIconColor(Color.GREEN);

            iconicFontDrawable.setIntrinsicWidth(10);

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mDatas.size();
        }

        @Override
        public Object getItem(int position) {
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


                convertView = LayoutInflater.from(MainApp.getInstance()).inflate(R.layout.jie_item,null);

                TextView title = (TextView)convertView.findViewById(android.R.id.text1);
                ImageView selectIV = (ImageView) convertView.findViewById(R.id.icon);

                viewHolder.textView = title;
                viewHolder.selectIV = selectIV;

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(mDatas.get(position).getValue());

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                viewHolder.selectIV.setBackgroundDrawable(iconicFontDrawable);
            } else {
                viewHolder.selectIV.setBackground(iconicFontDrawable);
            }

            return convertView;
        }

        private class ViewHolder {

            ImageView selectIV;
            TextView textView;

        }


    }
}
