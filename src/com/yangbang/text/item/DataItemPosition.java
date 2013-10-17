package com.yangbang.text.item;

/**
 * Created by yangbang on 13-10-17.
 */
public class DataItemPosition {
    public DataItem dataItem;
    public int position1;
    public int position2;
    public int position3;

    public int getPosition1() {
        return position1;
    }

    public void setPosition1(int position1) {
        this.position1 = position1;
    }

    public int getPosition2() {
        return position2;
    }

    public void setPosition2(int position2) {
        this.position2 = position2;
    }

    public int getPosition3() {
        return position3;
    }

    public void setPosition3(int position3) {
        this.position3 = position3;
    }

    public DataItem getDataItem() {

        return dataItem;
    }

    public void setDataItem(DataItem dataItem) {
        this.dataItem = dataItem;
    }
}
