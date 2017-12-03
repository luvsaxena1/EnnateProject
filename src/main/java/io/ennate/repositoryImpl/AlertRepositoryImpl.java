package io.ennate.repositoryImpl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.ennate.domain.Alert;
import io.ennate.repository.AlertRepository;

@Service
public class AlertRepositoryImpl extends BaseRepository<Alert, ObjectId> implements AlertRepository {

	@Autowired
	Datastore datastore;

	public AlertRepositoryImpl() {
		super(Alert.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Alert> getByTimeRange(Long startTime, Long endTime) {

		Query<Alert> queryForAlert = datastore.createQuery(Alert.class);
		queryForAlert.field("timeStamp").greaterThanOrEq(startTime);
		queryForAlert.field("timeStamp").lessThan(endTime);
		return queryForAlert.asList();
	}
}
