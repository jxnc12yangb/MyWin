package com.yangbang.text.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataPProperty implements Serializable {
	private List<DataProperty> dataProperties = new ArrayList<DataProperty>();
	private String icon;
	private String name;
	private String value;

	public DataPProperty() {
	}

	public DataPProperty(String paramString1, String paramString2,
			String paramString3) {
		this.name = paramString1;
		this.value = paramString2;
		this.icon = paramString3;
	}

	public List<DataProperty> getDataProperties() {
		return this.dataProperties;
	}

	public String getIcon() {
		return this.icon;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public void setDataProperties(List<DataProperty> paramList) {
		this.dataProperties = paramList;
	}

	public void setIcon(String paramString) {
		this.icon = paramString;
	}

	public void setName(String paramString) {
		this.name = paramString;
	}

	public void setValue(String paramString) {
		this.value = paramString;
	}
}
