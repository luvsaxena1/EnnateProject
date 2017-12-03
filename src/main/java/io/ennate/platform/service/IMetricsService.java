package io.ennate.platform.service;

import io.ennate.platform.dto.SensorMetricsDto;

import java.util.List;

public interface IMetricsService {

	List<SensorMetricsDto> getAllMetrics();

	SensorMetricsDto save(SensorMetricsDto dto);

	List<SensorMetricsDto> getMetricsByTimeRange(Long startDate, Long endDate);
}
