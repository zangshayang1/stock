package io.zangshayang.finance.data.restclient.alphavantage.jsonobj;

import java.util.TreeMap;

abstract class AlphaVantageTimeSeriesBase {
	
	private TreeMap<String, String> MetaData;
	
	public TreeMap<String, String> getMetadata() {
		return MetaData;
	}
	
	public void setMetadata(TreeMap<String, String> data) {
		this.MetaData = data;
	}
}
