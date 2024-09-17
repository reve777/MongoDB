package com.portfolio.forexTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.component.ScheduleData;
import com.portfolio.entity.ForexData;
import com.portfolio.repository.ForexDataRepository;

public class ScheduleDataTest {

    @InjectMocks
    private ScheduleData scheduleData;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ForexDataRepository forexDataRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetData() throws Exception {
        List<Map<String, String>> testData = new ArrayList<>();
        Map<String, String> forexRate = new HashMap<>();
        forexRate.put("Date", "2024-09-17");
        forexRate.put("USD/NTD", "30.123");
        forexRate.put("RMB/NTD", "4.567");
        forexRate.put("EUR/USD", "1.234");
        forexRate.put("USD/JPY", "110.45");
        forexRate.put("GBP/USD", "1.345");
        forexRate.put("AUD/USD", "0.789");
        forexRate.put("USD/HKD", "7.789");
        forexRate.put("USD/RMB", "6.789");
        forexRate.put("USD/ZAR", "15.678");
        forexRate.put("NZD/USD", "0.678");
        testData.add(forexRate);

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] responseBody = objectMapper.writeValueAsBytes(testData);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
        when(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        )).thenReturn(responseEntity);

        scheduleData.getData();

        verify(restTemplate, times(1)).exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        );
        verify(forexDataRepository, times(1)).save(any(ForexData.class));
    }

    @Test
    void testGetDataWithEmptyResponse() throws Exception {
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(new byte[0], HttpStatus.OK);
        when(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        )).thenReturn(responseEntity);


        scheduleData.getData();

        verify(restTemplate, times(1)).exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        );
        verify(forexDataRepository, never()).save(any(ForexData.class));
    }

    @Test
    void testGetDataWithException() throws Exception {
        when(restTemplate.exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        )).thenThrow(new RuntimeException("API Error"));

        scheduleData.getData();

        verify(restTemplate, times(1)).exchange(
            anyString(),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(byte[].class)
        );
        verify(forexDataRepository, never()).save(any(ForexData.class));
    }
}