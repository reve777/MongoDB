package com.portfolio.entity.request;

import java.util.List;

public class SuccessResponse {
	private ErrorResponse error;
	private List<CurrencyData> currency;

	public SuccessResponse(String code, String message, List<CurrencyData> currency) {
		this.error = new ErrorResponse(code, message);
		this.currency = currency;
	}

	public ErrorResponse getError() {
		return error;
	}

	public void setError(ErrorResponse error) {
		this.error = error;
	}

	public List<CurrencyData> getCurrency() {
		return currency;
	}

	public void setCurrency(List<CurrencyData> currency) {
		this.currency = currency;
	}

}
