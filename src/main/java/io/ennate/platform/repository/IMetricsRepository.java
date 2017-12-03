package io.ennate.platform.repository;

import io.ennate.platform.entity.SensorMetrics;

import java.util.List;

public interface IMetricsRepository extends CrudRepository<SensorMetrics, String>  {
	
	List<SensorMetrics> getByTimeRange(Long rang1, Long range2);

	List<SensorMetrics> findAll();
	
}