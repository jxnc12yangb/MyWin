package com.yangbang.text.item;

import android.content.Context;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemParser {
	private static final String TAG = "ItemParser";
	public static List<DataPProperty> homeDatalist = new ArrayList();

	private void loadHomePropertys(Context paramContext) {
			homeDatalist = null;
	        XmlPullParser parser = Xml.newPullParser();  
	        DataPProperty pp = null;
            DataProperty p = null;
            DataItem dataItem = null;

        try {
            parser.setInput(paramContext.getAssets().open("data.xml"), "UTF-8");

            int event = parser.getEventType();//产生第一个事件
            while(event!=XmlPullParser.END_DOCUMENT){
                switch(event){
                    case XmlPullParser.START_DOCUMENT://判断当前事件是否是文档开始事件
                        homeDatalist = new ArrayList<DataPProperty>();//初始化books集合
                        break;
                    case XmlPullParser.START_TAG://判断当前事件是否是标签元素开始事件
                        if("p-property".equals(parser.getName())){//判断开始标签元素是否是book
                            pp = new DataPProperty();
                            pp.setName(parser.getAttributeValue(0));
                            pp.setValue(parser.getAttributeValue(1));
                            pp.setIcon(parser.getAttributeValue(2));


                        }else if("property".equals(parser.getName())){
                            p = new DataProperty();
                            p.setDataItems(new ArrayList<DataItem>());
                            p.setName(parser.getAttributeValue(0));
                            p.setValue(parser.getAttributeValue(1));
                        }else if("item".equals(parser.getName())){

                            dataItem = new DataItem();
                            dataItem.setName(parser.getAttributeValue(0));
                            dataItem.setValue(parser.getAttributeValue(1));


                        }
                        break;
                    case XmlPullParser.END_TAG://判断当前事件是否是标签元素结束事件
                        if("p-property".equals(parser.getName())){//判断结束标签元素是否是book
                            homeDatalist.add(pp);
                            pp = null;
                        }else if("property".equals(parser.getName())){
                            pp.getDataProperties().add(p);
                            p = null;
                        }else if("item".equals(parser.getName())){
                            p.getDataItems().add(dataItem);
                            dataItem = null;
                        }
                        break;
                }
                event = parser.next();//进入下一个元素并触发相应事件
            }//end while

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}




    public void parseContent(Context context) {

        for(DataPProperty pProperty:homeDatalist){

            for(DataProperty dataProperty:pProperty.getDataProperties()){

                for(DataItem dataItem:dataProperty.getDataItems()){

                    parseContent(dataProperty,dataItem,context);

                }

            }
        }

    }

    private String readTextFile(InputStream inputStream) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];

        int len;

        try {

            while ((len = inputStream.read(buf)) != -1) {

                outputStream.write(buf, 0, len);

            }

            outputStream.close();

            inputStream.close();

        } catch (IOException e) {

        }

        return outputStream.toString();

    }

    public void parseContent(DataProperty dataProperty,DataItem dataItem,Context context){
        try {

            InputStream inputStream = context.getAssets().open(dataProperty.getName()+"/"+dataItem.getName()+".txt");
            dataItem.setContent(readTextFile(inputStream));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


	public DataPProperty findHomeDataPProperty(String paramString) {
		Iterator localIterator = this.homeDatalist.iterator();
		DataPProperty localDataPProperty;
		do {
			if (!localIterator.hasNext())
				return null;
			localDataPProperty = (DataPProperty) localIterator.next();
		} while (!localDataPProperty.getName().equalsIgnoreCase(paramString));
		return localDataPProperty;
	}

	public DataItem findJieDataItem(DataProperty paramDataProperty,
			String paramString) {
		Iterator localIterator = paramDataProperty.getDataItems().iterator();
		DataItem localDataItem;
		do {
			if (!localIterator.hasNext())
				return null;
			localDataItem = (DataItem) localIterator.next();
		} while (!localDataItem.getName().equalsIgnoreCase(paramString));
		return localDataItem;
	}

	public DataItem findJieDataItem(String paramString1, String paramString2,
			String paramString3) {
		Iterator localIterator = findZhangDataProperty(paramString1,
				paramString2).getDataItems().iterator();
		DataItem localDataItem;
		do {
			if (!localIterator.hasNext())
				return null;
			localDataItem = (DataItem) localIterator.next();
		} while (!localDataItem.getName().equalsIgnoreCase(paramString3));
		return localDataItem;
	}

	public DataProperty findZhangDataProperty(String paramString1,
			String paramString2) {
		DataPProperty localDataPProperty = findHomeDataPProperty(paramString1);
		if (localDataPProperty == null)
			return null;
		Iterator localIterator = localDataPProperty.getDataProperties()
				.iterator();
		DataProperty localDataProperty;
		do {
			if (!localIterator.hasNext())
				return null;
			localDataProperty = (DataProperty) localIterator.next();
		} while (!localDataProperty.getName().equalsIgnoreCase(paramString2));
		return localDataProperty;
	}

	public List<DataPProperty> parse(Context paramContext) {
		loadHomePropertys(paramContext);

        parseContent(paramContext);

		return this.homeDatalist;
	}
}

/*
 * Location: /home/yangbang/tool/jd-gui-0.3.5.linux.i686/classes_dex2jar.jar
 * Qualified Name: com.android.app.opensource.item.ItemParser JD-Core Version:
 * 0.6.2
 */