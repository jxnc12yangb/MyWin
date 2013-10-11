package com.yangbang.text;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.yangbang.Constant;
import com.yangbang.text.item.DataItem;
import com.yangbang.text.item.ItemParser;
import com.yangbang.xiaohua.R;

import java.util.ArrayList;
import java.util.List;


public class ListDataItemFragment extends ListFragment{

    private List<DataItem> mDatas;
    private List<String> mValues = new ArrayList<String>();
    private int position;
    private int position2;


    @Override
     public void onActivityCreated(Bundle savedInstanceState) {

         super.onActivityCreated(savedInstanceState);

         position = getArguments().getInt(Constant.position);
        position2 = getArguments().getInt(Constant.position2);

         mDatas = ItemParser.homeDatalist.get(position).getDataProperties().get(position2).getDataItems();

         for(DataItem dataProperty:mDatas){
             mValues.add(dataProperty.getValue());
         }

         setListAdapter(new ArrayAdapter<String>(getActivity(),R.layout.jie_item,R.id.title,mValues));

         getListView().setBackgroundResource(R.drawable.btn_menu_normal);
        getListView().setCacheColorHint(Color.argb(0,0,0,0));
        /*getListView().setSelector(R.drawable.grouplist_item_bg_normal);*/
        getListView().setDivider(getResources().getDrawable(R.drawable.spinner_divider));

        /**
         *   android:cacheColorHint="#00000000"
         android:listSelector="@drawable/grouplist_item_bg_normal"
         android:divider="@drawable/spinner_divider"
         */
     }

     @Override
     public void onListItemClick(ListView l, View v, int position, long id) {
         Log.i("FragmentList", "Item clicked: " + id);
         TextDetailFragment listDataItemFragment = new TextDetailFragment();
         Bundle bundle = new Bundle();
         bundle.putInt(Constant.position3,position);
         bundle.putInt(Constant.position2,this.position2);
         bundle.putInt(Constant.position,this.position);
         listDataItemFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().addToBackStack("ListDataItemFragment").add(android.R.id.content,listDataItemFragment,"TextDetailFragment").commit();
     }
     
     @Override
    public void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    }

   /* public class MyAdater extends BaseAdapter{

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

                ImageView selectIV = (ImageView)convertView.findViewById(R.id.jieImageView);

                TextView title = (TextView)convertView.findViewById(R.id.title);

                viewHolder.selectIV = selectIV;
                viewHolder.textView = title;

                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(mDatas.get(position).getValue());


            //

            return null;
        }

        private class ViewHolder {

            ImageView selectIV;
            TextView textView;

        }


    }*/
}
