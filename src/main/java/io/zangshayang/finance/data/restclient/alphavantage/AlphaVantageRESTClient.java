package io.zangshayang.finance.data.restclient.alphavantage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.zangshayang.finance.common.string.RegexHelper;
import io.zangshayang.finance.common.utils.rest.UriBuilder;
import io.zangshayang.finance.data.restclient.alphavantage.constants.ClientInterval;
import io.zangshayang.finance.data.restclient.alphavantage.constants.QueryFunction;
import io.zangshayang.finance.data.restclient.alphavantage.constants.QueryInterval;
import io.zangshayang.finance.data.restclient.alphavantage.constants.QueryParam;

/**
 * This class directly faces Alpha Vantage (https://www.alphavantage.co/) REST API.
 * */
public class AlphaVantageRESTClient {
	
	// TODO: a interface is needed at which level? at rest level or query level (including db and rest)??
	
	// Singleton Pattern
	private static AlphaVantageRESTClient INSTANCE;
	
	private AlphaVantageRESTClient() {}
	
	public static AlphaVantageRESTClient getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AlphaVantageRESTClient();
		}
		return INSTANCE;
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(AlphaVantageRESTClient.class);
	
	private static final String APIKEY = "IH5SP9W2G7VNXMT3";
	
	private static final String HOST = "https://www.alphavantage.co/query";
	
	private static final RestTemplate REST = new RestTemplate();
	
	private static final UriBuilder MIN_INTERVAL_TEMPLATE = new UriBuilder()
															.base(HOST)
															.query()
															.appendParam(QueryParam.FUNCTION, QueryFunction.INTRADAY)
															.appendParam(QueryParam.SYMBOL, "MSTF")
															.appendParam(QueryParam.INTERVAL, "1min")
															.appendParam(QueryParam.OUTPUTSIZE, "full")
															.appendParam(QueryParam.DATATYPE, "json")
															.appendParam(QueryParam.APIKEY, APIKEY);
	
	private static final UriBuilder DAY_INTERVAL_TEMPLATE = new UriBuilder()
															.base(HOST)
															.query()
															.appendParam(QueryParam.FUNCTION, QueryFunction.DAILY)
															.appendParam(QueryParam.SYMBOL, "MSTF")
															.appendParam(QueryParam.OUTPUTSIZE, "full")
															.appendParam(QueryParam.DATATYPE, "json")
															.appendParam(QueryParam.APIKEY, APIKEY);
	
	private String restCallForJson(String uri) {
		LOG.info(String.format("Rest call for json made on: %s", uri));
		ResponseEntity<String> re = REST.getForEntity(uri, String.class);
		if (re.getStatusCode() == HttpStatus.OK) {
			return re.getBody();
		} else {
			LOG.error(String.format("Failed to fetch json due to bad response with status code: %d", re.getStatusCodeValue()));
			return null;
		}
	}
	
	private boolean eligibleSymbol(String symbol) {
		// TODO
		return true;
	}
	
	public synchronized String getStockData(String symbol, String interval) {
		// limit API call 5 times per minute
		try {
			Thread.sleep(1000 * 12);
		} catch (InterruptedException e) {
			// TODO
		}
		String ret = helpGetStockData(symbol, interval);
		return ret;
	}
	
	private String helpGetStockData(String symbol, String interval) {
		
		if (!eligibleSymbol(symbol)) {
			LOG.error(String.format("Illegal company symbol entered: %s", symbol));
			return null;
		}
		
		UriBuilder ub;
		
		switch (interval) {
		
		case ClientInterval.ONE_MIN:
			ub = MIN_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.INTERVAL, QueryInterval.ONE_MIN); 
			break;
		case ClientInterval.FIVE_MIN:
			ub = MIN_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.INTERVAL, QueryInterval.FIVE_MIN); 
			break;
		case ClientInterval.FIFTEEN_MIN:
			ub = MIN_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.INTERVAL, QueryInterval.FIFTEEN_MIN); 
			break;			
		case ClientInterval.THIRTY_MIN:
			ub = MIN_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.INTERVAL, QueryInterval.THIRTY_MIN); 
			break;
		case ClientInterval.ONE_HOUR:
			ub = MIN_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.INTERVAL, QueryInterval.ONE_HOUR);
			break;
		case ClientInterval.DAILY:
			ub = DAY_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.FUNCTION, QueryFunction.DAILY);
			break;
		case ClientInterval.WEEKLY:
			ub = DAY_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.FUNCTION, QueryFunction.WEEKLY);
			break;
		case ClientInterval.MONTHLY:
			ub = DAY_INTERVAL_TEMPLATE.deepCopy();
			ub.replaceParam(QueryParam.FUNCTION, QueryFunction.MONTHLY);
			break;
		default:
			LOG.error(String.format("Illegal interval entered: %s", interval));
			return null;
		}
		
		ub.replaceParam(QueryParam.SYMBOL, symbol);
		
		String json = restCallForJson(ub.build());
		json = RegexHelper.replace(json, "\\d\\.\\s", "");
		json = RegexHelper.replace(json, "\\n", "");
		json = RegexHelper.replace(json, "(\\D)\\s(\\D)", "$1$2"); // take out space between two not-digit char 
		json = RegexHelper.replace(json, "\\(", "");
		json = RegexHelper.replace(json, "\\)", "");
		return json;
	}	
}
