package io.zangshayang.finance.data.persistent.alphavantage.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table( name = "stockdata1min", 
		indexes = {
				@Index(name = "symbol_index", columnList = "symbol")
			})
public class StockData1min extends AbsStockData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
