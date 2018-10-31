package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantage1minTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, TreeMap<String, String>> TimeSeries1min;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeries1min;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeries1min = data;
	}
}
