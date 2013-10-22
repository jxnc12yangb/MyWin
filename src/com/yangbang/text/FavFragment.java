package com.yangbang.text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.yangbang.Constant;
import com.yangbang.MainApp;
import com.yangbang.adapter.SimpleAdapter;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.DataItemPosition;
import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.DataProperty;
import com.yangbang.utils.SharedPreferencesUtil;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;


public class FavFragment extends SherlockFragment implements AdapterView.OnItemClickListener {

    private List<DataProperty> mDatas;
    private List<String> mValues = new ArrayList<String>();
    private ListView listView;

    public static final String ARG_IMAGE_RES = "image_source";
    public static final String ARG_ACTION_BG_RES = "image_action_bs_res";
    private List<DataItemPosition> mdata = new ArrayList<DataItemPosition>();
    private SimpleAdapter adapter;

    private String TAG = "FavFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_listview,container,false);
        listView = (ListView) view.findViewById(android.R.id.list);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();

    }

    private void initData() {

/*        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mValues);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);*/

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.activity_list_item,, mValues);
        String sets = SharedPreferencesUtil.getSharedPreferences(Constant.favs, null);

        List<DataPProperty> data = MainApp.getData();


        if(sets!=null&&!sets.equals("")){

            String[] lists = sets.split(",");

            if(MainApp.Debug)
                Log.e(TAG,"FavFragment:lists:"+lists.toString()+"---");

            if(lists!=null&&lists.length>0){
                for(String string:lists){

                    DataItemPosition dataItemPosition = new DataItemPosition();

                    if(MainApp.Debug)
                        Log.e(TAG,"FavFragment:initData:"+string);

                    String[] positions = string.split(":");
                    DataItem item = data.get(Integer.valueOf(positions[0])).getDataProperties().get(Integer.valueOf(positions[1])).getDataItems().get(Integer.valueOf(positions[2]));
                    dataItemPosition.setDataItem(item);
                    dataItemPosition.setPosition1(Integer.valueOf(positions[0]));
                    dataItemPosition.setPosition2(Integer.valueOf(positions[1]));
                    dataItemPosition.setPosition3(Integer.valueOf(positions[2]));



                    mdata.add(dataItemPosition);
                }
            }

        }

        if(mdata!=null&&mdata.size()>0){
            adapter = new SimpleAdapter(getActivity(),mdata);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

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
        Intent bundle = new Intent(getActivity(),TextDetailActivity.class);

        bundle.putExtra(Constant.position3,mdata.get(position).getPosition3());
        bundle.putExtra(Constant.position2,mdata.get(position).getPosition2());
        bundle.putExtra(Constant.position,mdata.get(position).getPosition1());

        bundle.putExtra(Constant.type,"");

        getActivity().startActivity(bundle);
    }

   }
