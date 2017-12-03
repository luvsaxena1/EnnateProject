package io.ennate.platform.repository;

import io.ennate.platform.entity.Alert;

import java.util.List;

public interface IAlertsRepository extends CrudRepository<Alert, String>  {
	
	List<Alert> getByTimeRange(Long startDate, Long endDate);

	List<Alert> findAll();
	
}