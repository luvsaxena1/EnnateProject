package io.ennate.repository;

import java.util.List;

import org.bson.types.ObjectId;

import io.ennate.domain.Alert;

public interface AlertRepository extends CrudRepository<Alert, ObjectId> {

	public List<Alert> getByTimeRange(Long startTime, Long endTime);
}
