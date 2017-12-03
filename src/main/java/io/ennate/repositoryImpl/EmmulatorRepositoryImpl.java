package io.ennate.repositoryImpl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.ennate.domain.EmmulatorDTO;
import io.ennate.repository.EmmulatorRepository;

@Repository
public class EmmulatorRepositoryImpl extends BaseRepository<EmmulatorDTO, ObjectId> implements EmmulatorRepository {

	@Autowired
	Datastore datastore;

	public EmmulatorRepositoryImpl() {
		super(EmmulatorDTO.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EmmulatorDTO> getByTimeRange(Long startTime, Long endTime) {
		
		Query<EmmulatorDTO> queryForEvent = datastore.createQuery(EmmulatorDTO.class);
	    queryForEvent.field("timeStamp").greaterThanOrEq(startTime);
	    queryForEvent.field("timeStamp").lessThan(endTime);
	    return queryForEvent.asList();
	}
}
