package io.zangshayang.finance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.zangshayang.finance.data.restclient.alphavantage.AlphaVantageRESTClient;

@RestController
public class Controller {
	
	@GetMapping("/")
	String f() {
		return AlphaVantageRESTClient.getInstance().getStockData("AAPL", "30min");
	}
}
