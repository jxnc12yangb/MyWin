package com.yangbang;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.SharedPreferences;

import com.yangbang.text.item.DataPProperty;
import com.yangbang.text.item.ItemParser;
import com.yangbang.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangbang on 13-10-9.
 */
public class MainApp extends Application{

    private static MainApp globalContext;

    public static List<DataPProperty> homeDatalists = new ArrayList();

    public static ProgressDialog progressDialog = null;

    public static List<DataPProperty> getData(){

        if(homeDatalists.size()==0){

            progressDialog.show();
            ItemParser itemParser = new ItemParser();
            itemParser.parse(globalContext);

            progressDialog.dismiss();
        }

        return homeDatalists;
    }

    public static List<DataPProperty> getDatas(){


        return homeDatalists;
    }


    public static boolean Debug = true;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences("sp",MODE_APPEND|MODE_WORLD_WRITEABLE);

        SharedPreferencesUtil.setSharedPreferences(sharedPreferences);

        globalContext = this;

        progressDialog = new ProgressDialog(this);
    }

    public static MainApp getInstance() {
        return globalContext;
    }
}
