package io.iot.platform.service;

import java.util.List;

import io.iot.platform.dto.SensorMetricsDto;

public interface IMetricsService {

	List<SensorMetricsDto> getAllMetrics();

	SensorMetricsDto save(SensorMetricsDto dto);

	List<SensorMetricsDto> getMetricsByTimeRange(Long startDate, Long endDate);
}
