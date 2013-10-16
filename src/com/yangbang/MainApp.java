package com.yangbang;

import android.app.Application;
import android.app.ProgressDialog;

import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.ItemParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangbang on 13-10-9.
 */
public class MainApp extends Application{

    private static MainApp globalContext;

    public static List<DataPProperty> homeDatalist = new ArrayList();

    public static ProgressDialog progressDialog = null;

    public static List<DataPProperty> getData(){

        if(homeDatalist.size()==0){

            progressDialog.show();
            ItemParser itemParser = new ItemParser();
            itemParser.parse(globalContext);

            progressDialog.dismiss();
        }

        return homeDatalist;
    }

    public static boolean Debug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        globalContext = this;

        progressDialog = new ProgressDialog(this);
    }

    public static MainApp getInstance() {
        return globalContext;
    }
}
