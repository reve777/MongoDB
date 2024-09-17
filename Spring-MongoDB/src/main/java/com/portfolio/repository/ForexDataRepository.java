package com.portfolio.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.entity.ForexData;

import java.util.List;

@Repository
public interface ForexDataRepository extends MongoRepository<ForexData, String> {
	
	/**
	 * $gte 是 MongoDB 的 "greater than or equal" 操作符，表示大于或等于某个值。它用于确保 date 字段的值不小于第一个参数 (?0)。
	 * $lte 是 MongoDB 的 "less than or equal" 操作符，表示小于或等于某个值。它用于确保 date 字段的值不大于第二个参数 (?1)
	 */
    @Query("{'date': { $gte: ?0, $lte: ?1 }}")
    List<ForexData> findByDateBetweenInclusive(String startDate, String endDate);
}
