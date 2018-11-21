package io.zangshayang.finance;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import io.zangshayang.finance.common.date.FinCalendar;
import io.zangshayang.finance.common.utils.GsonUtils;
import io.zangshayang.finance.data.persistent.alphavantage.dao.TargetStock;
import io.zangshayang.finance.data.persistent.alphavantage.jpa.TargetStockRepository;
import io.zangshayang.finance.data.restclient.alphavantage.AlphaVantageRESTClient;

@RestController
public class Controller {
	
	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	private static final Gson SLZR = GsonUtils.getSimpleSlzr();
	private static final AlphaVantageRESTClient CLIENT = AlphaVantageRESTClient.getInstance();
	private static Set<String> SYMBOLS_CACHED;
	
	@Autowired private TargetStockRepository targetStockRepo;
	
	@PostConstruct
	private void setup() {
		
		SYMBOLS_CACHED = new HashSet<>();
		Iterator<TargetStock> itr = targetStockRepo.findAll().iterator();
		while (itr.hasNext()) {
			SYMBOLS_CACHED.add(itr.next().getSymbol());
		}
	}
	
	
	@RequestMapping(value="/stock/{symbol}", method=RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:3000")
	String getStock(@PathVariable("symbol") String symbol, @RequestParam("interval") String interval) {
		LOG.info("Received Request For Stock Data on Symbol: " + symbol + "at Interval: "+ interval);
		
		if (!SYMBOLS_CACHED.contains(symbol.toUpperCase())) {
			targetStockRepo.save(new TargetStock(symbol));
			SYMBOLS_CACHED.add(symbol.toUpperCase());
		}
		
		return CLIENT.getStockData(symbol.toUpperCase(), interval);
	}
	
	@RequestMapping(value="latestTransactionDate", method=RequestMethod.GET)
	@CrossOrigin(origins="http://localhost:3000")
	String getLatestTxDay() {
		DateTime dt = FinCalendar.latestTransactionDate();
		HashMap<String, String> hm = new HashMap<>();
		hm.put("date", dt.toString());
		return SLZR.toJson(hm); 
	}

}
