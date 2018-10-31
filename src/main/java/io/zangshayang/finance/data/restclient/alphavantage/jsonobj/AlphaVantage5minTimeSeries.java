package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantage5minTimeSeries extends AlphaVantageTimeSeriesBase {
	
	private TreeMap<String, TreeMap<String, String>> TimeSeries5min;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeries5min;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeries5min = data;
	}
}
