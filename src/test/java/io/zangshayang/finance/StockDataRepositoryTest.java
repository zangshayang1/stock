package io.zangshayang.finance;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import io.zangshayang.finance.common.utils.GsonUtils;
import io.zangshayang.finance.data.persistent.alphavantage.dao.StockData1min;
import io.zangshayang.finance.data.persistent.alphavantage.jpa.StockData1minRepository;
import io.zangshayang.finance.data.restclient.alphavantage.AlphaVantageRESTClient;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage1minTimeSeries;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StockDataRepositoryTest {
	@Autowired private TestEntityManager entityManager;
	@Autowired private StockData1minRepository stockDataRepository;
	
	private static final Gson SLZR = GsonUtils.getSimpleSlzr();
	private static final AlphaVantageRESTClient CLIENT = AlphaVantageRESTClient.getInstance();
	
	@Test
	public void test1_save() {
		String json = CLIENT.getStockData("msft".toUpperCase(), "1min");
		AlphaVantage1minTimeSeries obj = SLZR.fromJson(json, AlphaVantage1minTimeSeries.class);
		TreeMap<String, Map<String, String>> timeSeries = obj.getTimeseries();
		for (Entry<String, Map<String, String>> e : timeSeries.entrySet()) {
			StockData1min sdp = new StockData1min();
			String k = e.getKey();
			Map<String, String> v = e.getValue();
			sdp.setDatetime(k);
			sdp.setHigh(Float.parseFloat(v.get("high")));
			sdp.setLow(Float.parseFloat(v.get("low")));
			sdp.setOpen(Float.parseFloat(v.get("open")));
			sdp.setClose(Float.parseFloat(v.get("close")));
			sdp.setVolume(Integer.parseInt(v.get("volume")));
			stockDataRepository.save(sdp);
		}
		Iterator<StockData1min> sdpItr = stockDataRepository.findAll().iterator();
		
		int countdown = 10;
		while (sdpItr.hasNext() && countdown > 0) {
			System.out.println(sdpItr.next().toString());
			countdown--;
		}
		System.out.println("haha");
	}
	
}
