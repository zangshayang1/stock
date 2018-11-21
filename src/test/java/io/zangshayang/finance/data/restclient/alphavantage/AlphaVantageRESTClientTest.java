package io.zangshayang.finance.data.restclient.alphavantage;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import io.zangshayang.finance.common.utils.GsonUtils;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage15minTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage1hourTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage1minTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage30minTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage5minTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantageDailyTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantageMonthlyTimeSeries;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantageWeeklyTimeSeries;

public class AlphaVantageRESTClientTest {
	
	private static AlphaVantageRESTClient client;
	private static Gson SIMPLE_SLZR = GsonUtils.getSimpleSlzr();
	
	@BeforeClass
	public static void setupClass() {
		 client = AlphaVantageRESTClient.getInstance();
	}
	
	@Test
	public void test_monthly() { // 250 records
		String json = client.getStockData("MSFT", "monthly");
		AlphaVantageMonthlyTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantageMonthlyTimeSeries.class);
		
		Assert.assertEquals("MSFT", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_weekly() { // 1087 records
		String json = client.getStockData("AAPL", "weekly");
		AlphaVantageWeeklyTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantageWeeklyTimeSeries.class);
		
		Assert.assertEquals("AAPL", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries()); 
	}
	
	@Test
	public void test_daily() { // 1162 records
		String json = client.getStockData("GOOG", "daily");
		AlphaVantageDailyTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantageDailyTimeSeries.class);
		
		Assert.assertEquals("GOOG", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
		
	@Test
	public void test_1hour() {
		String json = client.getStockData("EBAY", "1hour");
		AlphaVantage1hourTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantage1hourTimeSeries.class);
		
		Assert.assertEquals("EBAY", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_30min() {
		String json = client.getStockData("AMZN", "30min");
		AlphaVantage30minTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantage30minTimeSeries.class);
		
		Assert.assertEquals("AMZN", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}

	@Test
	public void test_15min() {
		String json = client.getStockData("AMD", "15min");
		AlphaVantage15minTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantage15minTimeSeries.class);
		
		Assert.assertEquals("AMD", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_5min() { // 1168 records
		String json = client.getStockData("TCEHY", "5min");
		AlphaVantage5minTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantage5minTimeSeries.class);
		
		Assert.assertEquals("TCEHY", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_1min() { // 2000 records
		String json = client.getStockData("BABA", "1min");
		AlphaVantage1minTimeSeries data = SIMPLE_SLZR.fromJson(json, AlphaVantage1minTimeSeries.class);
		
		Assert.assertEquals("BABA", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
}
