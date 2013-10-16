package com.yangbang.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataItem;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;


public class ListDataItemFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private List<DataItem> mDatas;
    private List<String> mValues = new ArrayList<String>();
    private int position;
    private int position2;
    private Bundle mArguments;
    private FadingActionBarHelper mFadingHelper;
    private ListView listView;

    public static final String ARG_IMAGE_RES = "image_source";
    public static final String ARG_ACTION_BG_RES = "image_action_bs_res";

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.e("text6","ListDataItemFragment");

        if (item.getItemId() == android.R.id.home || item.getItemId() == 0) {
            FragmentManager fm = getSherlockActivity().getSupportFragmentManager();

            if (fm.getBackStackEntryCount() > 0) {

                fm.popBackStack(fm.getBackStackEntryAt(fm.getBackStackEntryCount()-1).getId(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }

            return true;
        }

        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        getSherlockActivity().getActionBar().setTitle(MainApp.homeDatalist.get(position).getDataProperties().get(position2).getValue());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mArguments = getArguments();
        int actionBarBg = mArguments != null ? R.drawable.ab_background_light : R.drawable.ab_background_light;

        position = getArguments().getInt(Constant.position);
        position2 = getArguments().getInt(Constant.position2);


        mFadingHelper = new FadingActionBarHelper()
                .actionBarBackground(actionBarBg)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.activity_listview)
                .lightActionBar(actionBarBg == R.drawable.ab_background_light);
        getSherlockActivity().getActionBar().setDisplayHomeAsUpEnabled(true);




        mFadingHelper.initActionBar(activity);


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

    @Override
     public void onActivityCreated(Bundle savedInstanceState) {

         super.onActivityCreated(savedInstanceState);

        initData();

     }

    private void initData() {

        mDatas = MainApp.homeDatalist.get(position).getDataProperties().get(position2).getDataItems();

        for(DataItem dataProperty:mDatas){
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
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
         setHasOptionsMenu(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e("text6","ListDataItemFragment"+position);

      /*  TextDetailFragment listDataItemFragment = new TextDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.position3,position-1);
        bundle.putInt(Constant.position2,this.position2);
        bundle.putInt(Constant.position,this.position);
        listDataItemFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit).addToBackStack("ListDataItemFragment").add(android.R.id.content,listDataItemFragment,"TextDetailFragment").commit();*/

        Intent bundle = new Intent(getActivity(),TextDetailActivity.class);

        bundle.putExtra(Constant.position3,position-1);
        bundle.putExtra(Constant.position2,this.position2);
        bundle.putExtra(Constant.position,this.position);

        getActivity().startActivity(bundle);

    }

    public class MyAdater extends BaseAdapter {

        private final IconicFontDrawable iconicFontDrawable;
        private Context context;

        public MyAdater(){
            iconicFontDrawable = new IconicFontDrawable(getActivity());
            iconicFontDrawable.setIcon(EntypoIcon.LEFT);
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
