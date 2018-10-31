package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantageMonthlyTimeSeries extends AlphaVantageTimeSeriesBase {
	
	private TreeMap<String, TreeMap<String, String>> MonthlyTimeSeries;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return MonthlyTimeSeries;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.MonthlyTimeSeries = data;
	}
}
