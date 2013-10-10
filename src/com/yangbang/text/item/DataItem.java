package com.yangbang.text.item;

import java.io.Serializable;

public class DataItem implements Serializable {
	private String content;
	private String name;
	private DataItem nextItem;
	private DataItem preItem;
	private String value;

	public DataItem() {
	}

	public DataItem(String paramString) {
		this.name = paramString;
	}

	public DataItem(String paramString1, String paramString2) {
		this.name = paramString1;
		this.content = paramString2;
	}

	public DataItem(String paramString1, String paramString2,
			String paramString3) {
		this.name = paramString1;
		this.value = paramString2;
		this.content = paramString3;
	}

	public String getContent() {
		return this.content;
	}

	public String getName() {
		return this.name;
	}

	public DataItem getNextItem() {
		return this.nextItem;
	}

	public DataItem getPreItem() {
		return this.preItem;
	}

	public String getValue() {
		return this.value;
	}

	public void setContent(String paramString) {
		this.content = paramString;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setNextItem(DataItem paramDataItem) {
		this.nextItem = paramDataItem;
	}

	public void setPreItem(DataItem paramDataItem) {
		this.preItem = paramDataItem;
	}

	public void setValue(String paramString) {
		this.value = paramString;
	}
}

