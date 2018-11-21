package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.Map;
import java.util.TreeMap;

public class AlphaVantage1minTimeSeries extends AlphaVantageTimeSeriesBase {

	private TreeMap<String, Map<String, String>> TimeSeries1min;
	
	public TreeMap<String, Map<String,String>> getTimeseries() {
		return TimeSeries1min;
	}
	
	public void setTimeseries(TreeMap<String, Map<String, String>> data) {
		this.TimeSeries1min = data;
	}
}
