package io.zangshayang.finance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.zangshayang.finance.common.utils.GsonUtils;
import io.zangshayang.finance.data.persistent.alphavantage.dao.StockData1min;
import io.zangshayang.finance.data.persistent.alphavantage.dao.TargetStock;
import io.zangshayang.finance.data.persistent.alphavantage.jpa.StockData1minRepository;
import io.zangshayang.finance.data.persistent.alphavantage.jpa.TargetStockRepository;
import io.zangshayang.finance.data.restclient.alphavantage.AlphaVantageRESTClient;
import io.zangshayang.finance.data.restclient.alphavantage.jsonobj.AlphaVantage1minTimeSeries;

@Component
public class BatchUpdateDB {
	private static final Logger LOG = LoggerFactory.getLogger(BatchUpdateDB.class);
	private static final AlphaVantageRESTClient CLIENT = AlphaVantageRESTClient.getInstance();
	private static final Gson SLZR = GsonUtils.getSimpleSlzr();
	
	@Autowired private StockData1minRepository stockData1minRepository;
	@Autowired private TargetStockRepository targetStockRepo;
	
	/*
	 * Due to API call limit, less than 5 stocks can be kept updated at the same time. 
	 * */
	@Scheduled(cron = "0 * * * * *")
	public void update1min() {
		Iterator<TargetStock> itr = reloadTargetStocks();
		while (itr.hasNext()) {
			String symbol = itr.next().getSymbol();
			LOG.info("Batch Loading 1min Stock Data for " + symbol + " ...");
			
			String json = CLIENT.getStockData(symbol, "1min");
			AlphaVantage1minTimeSeries obj = SLZR.fromJson(json, AlphaVantage1minTimeSeries.class);
			TreeMap<String, Map<String, String>> timeSeries = obj.getTimeseries();
			
			StockData1min sdp = stockData1minRepository.findFirstBySymbolOrderByIdDesc(symbol);
			DateTime latestEntryTs = new DateTime(0);
			if (sdp != null) {
				latestEntryTs = getDatetimeFrom(sdp.getDatetime());
				LOG.info("Latest Update for: " + symbol + " by " + sdp.getDatetime());
			}
			
			boolean newDataComingIn = false;
			List<StockData1min> sdps = new ArrayList<>();
			for (Entry<String, Map<String, String>> e : timeSeries.entrySet()) {
				
				String ts = e.getKey();
				if (!newDataComingIn && getDatetimeFrom(ts).isBefore(latestEntryTs)) {
					continue;
				}
				newDataComingIn = true;
				Map<String, String> val = e.getValue();
				sdp = new StockData1min();
				sdp.setSymbol(symbol);
				sdp.setDatetime(ts);
				sdp.setHigh(Float.parseFloat(val.get("high")));
				sdp.setLow(Float.parseFloat(val.get("low")));
				sdp.setOpen(Float.parseFloat(val.get("open")));
				sdp.setClose(Float.parseFloat(val.get("close")));
				sdp.setVolume(Integer.parseInt(val.get("volume")));
				sdps.add(sdp);
			}
			stockData1minRepository.saveAll(sdps);
		}
	}

		
	private DateTime getDatetimeFrom(String str) {
		DateTimeFormatter dtFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		return dtFormatter.parseDateTime(str);
	}
	
	private Iterator<TargetStock> reloadTargetStocks() {
		Iterator<TargetStock> itr = targetStockRepo.findAll().iterator();
		return itr;
	}
}
