package io.zangshayang.finance.data.restclient.alphavantage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

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
	private static Gson gson;
	
	@BeforeClass
	public static void setupClass() {
		 client = AlphaVantageRESTClient.getInstance();
		 gson = new Gson();
	}
	
	@Test
	public void test_monthly() {
		String json = client.getStockData("MSFT", "monthly");
		AlphaVantageMonthlyTimeSeries data = gson.fromJson(json, AlphaVantageMonthlyTimeSeries.class);
		
		Assert.assertEquals("MSFT", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_weekly() {
		String json = client.getStockData("AAPL", "weekly");
		AlphaVantageWeeklyTimeSeries data = gson.fromJson(json, AlphaVantageWeeklyTimeSeries.class);
		
		Assert.assertEquals("AAPL", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_daily() {
		String json = client.getStockData("GOOG", "daily");
		AlphaVantageDailyTimeSeries data = gson.fromJson(json, AlphaVantageDailyTimeSeries.class);
		
		Assert.assertEquals("GOOG", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
		
	@Test
	public void test_1hour() {
		String json = client.getStockData("EBAY", "1hour");
		AlphaVantage1hourTimeSeries data = gson.fromJson(json, AlphaVantage1hourTimeSeries.class);
		
		Assert.assertEquals("EBAY", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_30min() {
		String json = client.getStockData("AMZN", "30min");
		AlphaVantage30minTimeSeries data = gson.fromJson(json, AlphaVantage30minTimeSeries.class);
		
		Assert.assertEquals("AMZN", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}

	@Test
	public void test_15min() {
		String json = client.getStockData("AMD", "15min");
		AlphaVantage15minTimeSeries data = gson.fromJson(json, AlphaVantage15minTimeSeries.class);
		
		Assert.assertEquals("AMD", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_5min() {
		String json = client.getStockData("TCEHY", "5min");
		AlphaVantage5minTimeSeries data = gson.fromJson(json, AlphaVantage5minTimeSeries.class);
		
		Assert.assertEquals("TCEHY", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	@Test
	public void test_1min() {
		String json = client.getStockData("BABA", "1min");
		AlphaVantage1minTimeSeries data = gson.fromJson(json, AlphaVantage1minTimeSeries.class);
		
		Assert.assertEquals("BABA", data.getMetadata().get("Symbol"));
		Assert.assertNotNull(data.getTimeseries());
	}
	
	private void write2File(String content, String filename) throws IOException {
		
		String fp = System.getProperty("user.dir") + File.separator + filename;
		OutputStream os = null;
		try {
			os = new FileOutputStream(fp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		os.write(content.getBytes());
		os.close();
	}

}
