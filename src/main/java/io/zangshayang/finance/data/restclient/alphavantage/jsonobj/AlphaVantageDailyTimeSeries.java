package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantageDailyTimeSeries extends AlphaVantageTimeSeriesBase {
	
	private TreeMap<String, TreeMap<String, String>> TimeSeriesDaily;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeriesDaily;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeriesDaily = data;
	}

}
