package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantage15minTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, TreeMap<String, String>> TimeSeries15min;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeries15min;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeries15min = data;
	}
}
