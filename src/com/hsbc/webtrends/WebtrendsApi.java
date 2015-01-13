package com.hsbc.webtrends;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsbc.webtrends.json.Webtrend;
import com.webtrends.mobile.analytics.IllegalWebtrendsParameterValueException;
import com.webtrends.mobile.analytics.WebtrendsDataCollector;

public class WebtrendsApi {
	private static final Logger LOG = LoggerFactory.getLogger(WebtrendsApi.class);
	
	protected static final WebtrendsDataCollector DC = WebtrendsDataCollector.getInstance();
	protected static final String WEBTID = "wt_vtid";
	
	
	protected static HashMap<String, String> webtrendData = new HashMap<String, String>();
	
	public static void init(Map<String, String> customData) {
		LOG.debug("init default webtrends custom data");
		if (webtrendData.isEmpty()) {
			webtrendData.putAll(customData);
		}
	}
	
	public static String shouldAppendURLconst(String url) {
		if (!url.contains(WEBTID)) {
			url = DC.appendSessionParamsToURL(url);
		}
		return url;
	}
	
	protected static void logCustomEvent(String eventPath, String eventDesc, Map<String, String> customData) {
		try {
			if (customData == null) {
				customData = new HashMap<String, String>();
			}
			customData.putAll(webtrendData);
			DC.onCustomEvent(eventPath, eventDesc, customData);
			
		} catch (IllegalWebtrendsParameterValueException e) {
			LOG.error("log webtrend exception",e);
		} catch (Exception e) {
			LOG.error("log webtrend exception",e);
		}
	}
	
	public static void logCustomEvent(List<Webtrend> moduleWebtrendsConfig) {
		try {
			if(moduleWebtrendsConfig==null || moduleWebtrendsConfig.size()==0){
				LOG.warn("module webtrend settings is null");
				return;
			}
			
			Webtrend webtrend = moduleWebtrendsConfig.get(0);
			HashMap<String, String> map = null;
			if (webtrend.getCustomData()!=null && webtrend.getCustomData().size()>0) {
				map = webtrend.getCustomData().get(0);
				LOG.debug("module webtrends custom data: {}", String.valueOf(map));
			}
			logCustomEvent(webtrend.getEventPath(), webtrend.getEventDesc(), map);
			
		} catch (Exception e) {
			LOG.error("log webtrend exception",e);
		}
	}
}
