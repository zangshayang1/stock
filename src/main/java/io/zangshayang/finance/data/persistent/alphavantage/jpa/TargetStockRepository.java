package io.zangshayang.finance.data.persistent.alphavantage.jpa;

import org.springframework.data.repository.CrudRepository;

import io.zangshayang.finance.data.persistent.alphavantage.dao.TargetStock;

public interface TargetStockRepository extends CrudRepository<TargetStock, Integer> {}
