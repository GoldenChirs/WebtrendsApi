package com.hsbc.webtrends.json;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.annotations.Expose;

/* JW [Jul-2014] Project Connect Q3-2014
 * Refactor the class name from Webtrend.java to WebtrendsConfigItem.java
 */
public class Webtrend {
	@Expose
	private String eventPath;
	@Expose
	private String eventDesc;
	@Expose
	private ArrayList<HashMap<String, String>> customData;
	
	public String getEventPath() {
		return eventPath;
	}
	public void setEventPath(String eventPath) {
		this.eventPath = eventPath;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public ArrayList<HashMap<String, String>> getCustomData() {
		return customData;
	}
	public void setCustomData(ArrayList<HashMap<String, String>> customData) {
		this.customData = customData;
	}
}
