package com.portfolio.entity.request;

public class CurrencyData {
	private String date;
	private Double usd;

	public CurrencyData(String date, Double usd) {
		this.date = date;
		this.usd = usd;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getUsd() {
		return usd;
	}

	public void setUsd(Double usd) {
		this.usd = usd;
	}

}
