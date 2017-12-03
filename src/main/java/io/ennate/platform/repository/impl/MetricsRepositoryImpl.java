package io.ennate.platform.repository.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import io.ennate.platform.entity.SensorMetrics;
import io.ennate.platform.repository.IMetricsRepository;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MetricsRepositoryImpl extends BaseRepository<SensorMetrics, String> implements IMetricsRepository {

	@Autowired
    Datastore datastore;

	public MetricsRepositoryImpl() {
		super(SensorMetrics.class);
	}

	@Override
	public List<SensorMetrics> getByTimeRange(Long startTime, Long endTime) {
		BasicDBObject rangeQuery = new BasicDBObject("timeStamp",  new BasicDBObject("$gte", startTime).append("$lte", endTime));
		DBCursor cursor = datastore.getCollection(SensorMetrics.class).find(rangeQuery);
		return cursorToList(cursor);
	}

	private List<SensorMetrics> cursorToList(DBCursor cursor) {
		List<SensorMetrics> result = new ArrayList<>();
		while(cursor.hasNext()) {
			BasicDBObject obj = (BasicDBObject) cursor.next();
			SensorMetrics entity = new SensorMetrics();
			entity.setId(obj.get("_id").toString());
			entity.setTimeStamp(Long.parseLong(obj.get("timeStamp").toString()));
			entity.setValue(Integer.parseInt(obj.get("value").toString()));
			result.add(entity);
		}
		return result;
	}

	@Override
	public List<SensorMetrics> findAll() {
		DBCursor cursor =  datastore.getCollection(SensorMetrics.class).find();
		return cursorToList(cursor);
	}
}
