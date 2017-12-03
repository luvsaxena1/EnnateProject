package io.ennate.repository;

import java.util.List;

import org.bson.types.ObjectId;

import io.ennate.domain.EmmulatorDTO;

public interface EmmulatorRepository extends CrudRepository<EmmulatorDTO, ObjectId> {

	public List<EmmulatorDTO> getByTimeRange(Long rang1, Long range2);

}