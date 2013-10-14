package com.yangbang.text;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.MenuItem;
import com.manuelpeinado.fadingactionbar.FadingActionBarHelper;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mArguments = getArguments();
        int actionBarBg = mArguments != null ? R.drawable.ab_background_light : R.drawable.ab_background_light;

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

        position = getArguments().getInt(Constant.position);

        mDatas = ItemParser.homeDatalist.get(position).getDataProperties();

        for(DataProperty dataProperty:mDatas){
            mValues.add(dataProperty.getValue());
        }

/*        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mValues);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);*/



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, mValues);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    /**
     * @return A list of Strings read from the specified resource
     */
    private ArrayList<String> loadItems(int rawResourceId) {
        try {
            ArrayList<String> countries = new ArrayList<String>();
            InputStream inputStream = getResources().openRawResource(rawResourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                countries.add(line);
            }
            reader.close();
            return countries;
        } catch (IOException e) {
            return null;
        }
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
        ListDataItemFragment listDataItemFragment = new ListDataItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.position,this.position);
        bundle.putInt(Constant.position2,position);
        listDataItemFragment.setArguments(bundle);

        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_left_exit,
                R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_right_exit).addToBackStack(null).add(android.R.id.content,listDataItemFragment,"ListDataItemFragment").commit();
    }

    public class MyAdater extends BaseAdapter {

        private Context context;


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

                TextView title = (TextView)convertView.findViewById(R.id.title);

                viewHolder.textView = title;

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(mDatas.get(position).getValue());

            //

            return convertView;
        }

        private class ViewHolder {

            ImageView selectIV;
            TextView textView;

        }


    }
}
