package io.zangshayang.finance.data.persistent.alphavantage.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "targetstock")
public class TargetStock {
	@Column(name = "symbol", unique = true)
	private String symbol;
	
	// default constructor is required when using JPA
	// if not explicitly implemented, it will be override by explicitly declared constructor
	public TargetStock() {};
	
	public TargetStock(String symbol) {
		this.symbol = symbol.toUpperCase();
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
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
