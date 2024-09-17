package com.portfolio.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.entity.ForexData;
import com.portfolio.repository.ForexDataRepository;

@Component
public class ScheduleData {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleData.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ForexDataRepository forexDataRepository;

	private static final String API_URL = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";

	@SuppressWarnings("unchecked")
	@Scheduled(cron = "0 0 18 * * *")
	public void getData() {
		LocalDateTime now = LocalDateTime.now();
		String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		logger.info("getData method triggered at: {}", formattedDateTime);
		try {
			   HttpHeaders headers = new HttpHeaders();
	            HttpEntity<String> entity = new HttpEntity<>(headers);
	            
	            ResponseEntity<byte[]> response = restTemplate.exchange(API_URL, HttpMethod.GET, entity, byte[].class);
	            byte[] responseBody = response.getBody();

			if (responseBody != null && responseBody.length > 0) {
				String responseString = new String(responseBody);

				logger.info("Received data: {}", responseString);
				ObjectMapper objectMapper = new ObjectMapper();
				List<Map<String, String>> responseData = objectMapper.readValue(responseString, List.class);

				if (!responseData.isEmpty()) {
					for (Map<String, String> forexRate : responseData) {
						String usdNtdRate = forexRate.get("USD/NTD");
						String rmbNtdRate = forexRate.get("RMB/NTD");
						String eurUsdRate = forexRate.get("EUR/USD");
						String usdJpyRate = forexRate.get("USD/JPY");
						String gbpUsdRate = forexRate.get("GBP/USD");
						String audUsdRate = forexRate.get("AUD/USD");
						String usdHkdRate = forexRate.get("USD/HKD");
						String usdRmbRate = forexRate.get("USD/RMB");
						String usdZarRate = forexRate.get("USD/ZAR");
						String nzdUsdRate = forexRate.get("NZD/USD");
						String date = forexRate.get("Date");

						ForexData forexData = new ForexData();
						forexData.setDate(date);
						
						forexData.setUsdNtdRate(Double.parseDouble(usdNtdRate));
						forexData.setRmbNtdRate(Double.parseDouble(rmbNtdRate));
						forexData.setEurUsdRate(Double.parseDouble(eurUsdRate));
						forexData.setUsdJpyRate(Double.parseDouble(usdJpyRate));
						forexData.setGbpUsdRate(Double.parseDouble(gbpUsdRate));
						forexData.setAudUsdRate(Double.parseDouble(audUsdRate));
						forexData.setUsdHkdRate(Double.parseDouble(usdHkdRate));
						forexData.setUsdRmbRate(Double.parseDouble(usdRmbRate));
						forexData.setUsdZarRate(Double.parseDouble(usdZarRate));
						forexData.setNzdUsdRate(Double.parseDouble(nzdUsdRate));
	
						forexDataRepository.save(forexData);

						logger.info("Forex data saved: {}", forexData);
					}
				} else {
					logger.warn("No data received from API");
				}
			} else {
				logger.warn("No data received from API");
			}
		} catch (Exception e) {
			logger.error("Error occurred while processing forex data", e);
		}
	}
}