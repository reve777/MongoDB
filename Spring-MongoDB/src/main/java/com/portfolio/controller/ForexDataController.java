package com.portfolio.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.component.ScheduleData;
import com.portfolio.entity.ForexData;
import com.portfolio.entity.request.CurrencyData;
import com.portfolio.entity.request.ErrorResponse;
import com.portfolio.entity.request.ForexRequest;
import com.portfolio.entity.request.SuccessResponse;
import com.portfolio.repository.ForexDataRepository;

@RestController
@RequestMapping("/api/forex")
public class ForexDataController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleData.class);
	
    @Autowired
    private ForexDataRepository forexDataRepository;
    
    @Autowired
    private ScheduleData data;
    @GetMapping("/create")
    public boolean get() {
    	try {
    		data.getData();
    		System.out.println("success");
		} catch (Exception e) {
			logger.error("Error", e);
		}
    	
    	return true;
    }
    @RequestMapping(value = "/historical", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> getHistoricalData(@RequestBody ForexRequest request) {
        // Parse and validate the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate startDate, endDate;
        try {
            startDate = LocalDate.parse(request.getStartDate(), formatter);
            endDate = LocalDate.parse(request.getEndDate(), formatter);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("E001", "Invalid date format"));
        }

        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        if (startDate.isBefore(oneYearAgo) || endDate.isAfter(yesterday) || startDate.isAfter(endDate)) {
            return ResponseEntity.badRequest().body(new ErrorResponse("E001", "Date range is not valid"));
        }
        String startDateString = startDate.format(formatter);
        String endDateString = endDate.format(formatter);

//        List<ForexData> forexDataList = forexDataRepository.findByDateBetween(startDateString, endDateString);
        List<ForexData> forexDataList = forexDataRepository.findByDateBetweenInclusive(startDateString, endDateString);

        if (forexDataList.isEmpty()) {
            return ResponseEntity.ok(new SuccessResponse("0000", "Success", new ArrayList<>()));
        }

        List<CurrencyData> currencyList = forexDataList.stream().map(data -> {
            double currencyValue;
            switch (request.getCurrency()) {
             	case "usd": //需求request
                case "usdNtdRate":
                    currencyValue = data.getUsdNtdRate();
                    break;
                case "rmbNtdRate":
                    currencyValue = data.getRmbNtdRate();
                    break;
                case "eurUsdRate":
                    currencyValue = data.getEurUsdRate();
                    break;
                case "usdJpyRate":
                    currencyValue = data.getUsdJpyRate();
                    break;
                case "gbpUsdRate":
                    currencyValue = data.getGbpUsdRate();
                    break;
                case "audUsdRate":
                    currencyValue = data.getAudUsdRate();
                    break;
                case "usdHkdRate":
                    currencyValue = data.getUsdHkdRate();
                    break;
                case "usdRmbRate":
                    currencyValue = data.getUsdRmbRate();
                    break;
                case "usdZarRate":
                    currencyValue = data.getUsdZarRate();
                    break;
                case "nzdUsdRate":
                    currencyValue = data.getNzdUsdRate();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported currency: " + request.getCurrency());
            }
            return new CurrencyData(data.getDate(), currencyValue);
        }).collect(Collectors.toList());

        return ResponseEntity.ok(new SuccessResponse("0000", "Success", currencyList));
    }
}
