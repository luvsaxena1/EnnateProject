package io.iot.platform.repository;

import java.util.List;

import io.iot.platform.entity.SensorMetrics;

public interface IMetricsRepository extends CrudRepository<SensorMetrics, String>  {
	
	List<SensorMetrics> getByTimeRange(Long rang1, Long range2);

	List<SensorMetrics> findAll();
	
}