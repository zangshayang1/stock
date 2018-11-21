package io.zangshayang.finance.data.persistent.alphavantage.jpa;

import org.springframework.data.repository.CrudRepository;

import io.zangshayang.finance.data.persistent.alphavantage.dao.StockData1min;

public interface StockData1minRepository extends CrudRepository<StockData1min, Integer> {

	StockData1min findFirstBySymbolOrderByIdDesc(String symbol);
}
