package io.iot.platform.repository;

import java.util.List;

import io.iot.platform.entity.Alert;

public interface IAlertsRepository extends CrudRepository<Alert, String>  {
	
	List<Alert> getByTimeRange(Long startDate, Long endDate);

	List<Alert> findAll();
	
}