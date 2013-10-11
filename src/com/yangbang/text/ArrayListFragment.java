package com.yangbang.text;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.yangbang.Constant;
import com.yangbang.FragmentDemo;
import com.yangbang.MainApp;
import com.yangbang.text.item.DataProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;


public class ArrayListFragment extends FragmentDemo implements AdapterView.OnItemClickListener {

    private List<DataProperty> mDatas;
    private List<String> mValues = new ArrayList<String>();
    private int position ;
    private ListView listView;

    @Override
     public void onActivityCreated(Bundle savedInstanceState) {

         super.onActivityCreated(savedInstanceState);
         //setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.jie_item,R.id.title,mValues));

     }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.single_listview,container,false);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    protected void initData() {
        position = getArguments().getInt(Constant.position);

        mDatas = ItemParser.homeDatalist.get(position).getDataProperties();

        for(DataProperty dataProperty:mDatas){
            mValues.add(dataProperty.getValue());
        }

        listView.setAdapter(new MyAdater());
    }


    @Override
    protected void initEvents() {

    }

     @Override
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        ListDataItemFragment listDataItemFragment = new ListDataItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constant.position,this.position);
        bundle.putInt(Constant.position2,position);
        listDataItemFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().addToBackStack("ArrayListFragment").add(android.R.id.content,listDataItemFragment,"ListDataItemFragment").commit();
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
