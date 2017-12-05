package io.ennate.platform.service.impl;

import java.util.List;

import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import io.ennate.platform.dto.SensorMetricsDto;
import io.ennate.platform.entity.SensorMetrics;
import io.ennate.platform.repository.IMetricsRepository;
import io.ennate.platform.service.IMetricsService;
import io.ennate.platform.util.ConversionUtil;

@Service
public class MetricsServiceImpl implements IMetricsService {

	@Autowired
	private ConversionUtil converter;

	@Autowired
	private IMetricsRepository dao;

//	@Autowired
//	private IAlertService alertService;

	@Value("${base.weight}")
	private Integer baseWeight;

	@Override
	public List<SensorMetricsDto> getAllMetrics() {
		List<SensorMetrics> results = dao.findAll();
		return converter.metricsEntityListToDtoList(results);
	}

	@Override
	public SensorMetricsDto save(SensorMetricsDto resource) {
//		Boolean isRuleValid = Rule(resource);
//		if (isRuleValid) {
//			AlertDto alertDto = new AlertDto();
//			alertDto.setTimeStamp(resource.getTimeStamp());
//			alertDto.setSensorWeight(resource.getValue());
//			alertDto.setBaseWeight(baseWeight);
//			alertService.save(alertDto);
//		}
		SensorMetrics entity = converter.dtoToEntity(resource);
		Key<SensorMetrics> savedEntity = dao.create(entity);
		if (!ObjectUtils.isEmpty(savedEntity)) {
			return converter.entityToDto(dao.read(savedEntity.getId().toString()));
		}
		return null;
	}

	@Override
	public List<SensorMetricsDto> getMetricsByTimeRange(Long startTime, Long endTime) {
		List<SensorMetrics> queriedData = dao.getByTimeRange(startTime, endTime);
		return converter.metricsEntityListToDtoList(queriedData);
	}
	
	// Previous code without the hack

/*	private Boolean Rule(SensorMetricsDto resource) {
		Integer recievedWeight = resource.getValue();
		if (recievedWeight != null) {

			boolean overThreashold = recievedWeight > (baseWeight + ((baseWeight * 10) / 100));

			boolean underThreashold = recievedWeight < (baseWeight - ((baseWeight * 10) / 100));

			if (underThreashold || overThreashold) {
				return true;
			}
		}
		return false;
	}*/
}
