package io.zangshayang.finance.data.persistent.alphavantage.dao;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class AbsStockData {
	
	protected String datetime;
	protected String symbol;
	protected float open;
	protected float high;
	protected float low;
	protected float close;
	protected int volume;

	public float getOpen() {
		return open;
	}
	public void setOpen(float open) {
		this.open = open;
	}
	public float getHigh() {
		return high;
	}
	public void setHigh(float high) {
		this.high = high;
	}
	public float getLow() {
		return low;
	}
	public void setLow(float low) {
		this.low = low;
	}
	public float getClose() {
		return close;
	}
	public void setClose(float close) {
		this.close = close;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return String.format("StockDataPoint(%s, %s, %f, %f, %f, %f, %d)", symbol, datetime, open, high, low, close, volume);
	}
}
