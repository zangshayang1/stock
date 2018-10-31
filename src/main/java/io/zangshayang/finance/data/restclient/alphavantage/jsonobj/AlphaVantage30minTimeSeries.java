package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantage30minTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, TreeMap<String, String>> TimeSeries30min;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeries30min;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeries30min = data;
	}
}
