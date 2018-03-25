package io.iot.platform.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import io.iot.platform.entity.Alert;
import io.iot.platform.repository.IAlertsRepository;

@Repository
public class AlertsRepositoryImpl extends BaseRepository<Alert, String> implements IAlertsRepository {

	@Autowired
    Datastore datastore;

	public AlertsRepositoryImpl() {
		super(Alert.class);
	}

	@Override
	public List<Alert> getByTimeRange(Long startTime, Long endTime) {
		
		Query<Alert> queryForMatrics = datastore.createQuery(Alert.class);
		queryForMatrics.field("timeStamp").greaterThanOrEq(startTime);
		queryForMatrics.field("timeStamp").lessThan(endTime);
		return queryForMatrics.asList();
	}

	private List<Alert> cursorToList(DBCursor cursor) {
		List<Alert> result = new ArrayList<>();
		while(cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			Alert entity = new Alert();
			entity.setId(obj.get("_id").toString());
			entity.setTimeStamp(Long.parseLong(obj.get("timeStamp").toString()));
			entity.setBaseWeight(Integer.parseInt(obj.get("baseWeight").toString()));
			entity.setSensorWeight(Integer.parseInt(obj.get("sensorWeight").toString()));
			result.add(entity);
		}
		return result;
	}

	@Override
	public List<Alert> findAll() {
		DBCursor cursor =  datastore.getCollection(Alert.class).find();
		return cursorToList(cursor);
	}
}
