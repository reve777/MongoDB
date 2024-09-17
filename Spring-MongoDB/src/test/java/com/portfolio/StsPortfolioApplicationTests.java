package com.portfolio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.portfolio.entity.ForexData;
import com.portfolio.repository.ForexDataRepository;

@SpringBootTest
class StsPortfolioApplicationTests {

	@Autowired
    private ForexDataRepository forexDataRepository;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    @Test
    public void testSaveAndRetrieveForexData() {
//        ForexData forexData = new ForexData("1", "20240801", 32.7);
//        forexDataRepository.save(forexData);
        String testDate = "20240801";
        String testDate2 = "20240805";
        List<ForexData> result = forexDataRepository.findByDateBetweenInclusive(testDate, testDate2);

        // Assertions
        assertFalse(result.isEmpty());
        assertEquals("1", result.get(0).getId());
        assertEquals("20240801", result.get(0).getDate());
        assertEquals(32.7, result.get(0).getUsdNtdRate());
    }

    @Test
    public void testRetrieveForexDataRange() {
        // Create and save multiple ForexData objects
//        forexDataRepository.save(new ForexData("2", "20240821", 31.9));
//        forexDataRepository.save(new ForexData("3", "20240822", 31.9));
//        forexDataRepository.save(new ForexData("4", "20240823", 31.9));

        // Retrieve the data for a date range
        String startDate = "20240807";
        String endDate = "20240809";
        List<ForexData> result = forexDataRepository.findByDateBetweenInclusive(startDate, endDate);

        // Assertions
        assertEquals(3, result.size());
        assertTrue(result.stream().anyMatch(data -> data.getDate().equals("20240807")));
        assertTrue(result.stream().anyMatch(data -> data.getDate().equals("20240808")));
        assertTrue(result.stream().anyMatch(data -> data.getDate().equals("20240809")));
        assertTrue(result.stream().anyMatch(data -> data.getUsdNtdRate().equals(32.681)));
        assertTrue(result.stream().anyMatch(data -> data.getUsdNtdRate().equals(32.593)));
        assertTrue(result.stream().anyMatch(data -> data.getUsdNtdRate().equals(32.44)));
    }
}
