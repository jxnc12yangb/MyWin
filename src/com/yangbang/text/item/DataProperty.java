package com.yangbang.text.item;

import java.io.Serializable;
import java.util.List;

public class DataProperty implements Serializable {
	private String content;
	private List<DataItem> dataItems;
	private String name;
	public DataProperty nextProperty;
	public DataProperty preProperty;
	private String value;

	public DataProperty() {
	}

	public DataProperty(String paramString1, String paramString2) {
		this.name = paramString1;
		this.value = paramString2;
	}

	public String getContent() {
		return this.content;
	}

	public List<DataItem> getDataItems() {
		return this.dataItems;
	}

	public DataItem getFirstDataItem() {
		if ((this.dataItems == null) || (this.dataItems.size() == 0))
			return null;
		return (DataItem) this.dataItems.get(0);
	}

	public String getName() {
		return this.name;
	}

	public DataProperty getNextProperty() {
		return this.nextProperty;
	}

	public DataProperty getPreProperty() {
		return this.preProperty;
	}

	public String getValue() {
		return this.value;
	}

	public void setContent(String paramString) {
		this.content = paramString;
	}

	public void setDataItems(List<DataItem> paramList) {
		this.dataItems = paramList;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setNextProperty(DataProperty paramDataProperty) {
		this.nextProperty = paramDataProperty;
	}

	public void setPreProperty(DataProperty paramDataProperty) {
		this.preProperty = paramDataProperty;
	}

	public void setValue(String paramString) {
		this.value = paramString;
	}
}
