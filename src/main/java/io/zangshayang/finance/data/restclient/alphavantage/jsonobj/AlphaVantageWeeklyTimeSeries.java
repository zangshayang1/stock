package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantageWeeklyTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, TreeMap<String, String>> WeeklyTimeSeries;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return WeeklyTimeSeries;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.WeeklyTimeSeries = data;
	}
}
