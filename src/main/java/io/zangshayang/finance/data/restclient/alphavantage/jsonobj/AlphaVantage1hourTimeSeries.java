package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

public class AlphaVantage1hourTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, TreeMap<String, String>> TimeSeries60min;
	
	public TreeMap<String, TreeMap<String, String>> getTimeseries() {
		return TimeSeries60min;
	}
	
	public void setTimeseries(TreeMap<String, TreeMap<String, String>> data) {
		this.TimeSeries60min = data;
	}
}
