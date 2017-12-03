package io.ennate.platform.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import io.ennate.platform.dto.AlertDto;
import io.ennate.platform.dto.SensorMetricsDto;
import io.ennate.platform.entity.Alert;
import io.ennate.platform.entity.SensorMetrics;

@Component
public class ConversionUtil {

	//Sensor Metrics Converters
	public SensorMetricsDto entityToDto(SensorMetrics entity) {
		SensorMetricsDto dto = new SensorMetricsDto();
		dto.setId(entity.getId());
		dto.setTimeStamp(entity.getTimeStamp());
		dto.setValue(entity.getValue());
		return dto;
	}

	public SensorMetrics dtoToEntity(SensorMetricsDto dto) {
		SensorMetrics entity = new SensorMetrics();
		if(!StringUtils.isEmpty(dto.getId())) {
			entity.setId(dto.getId());
		}
		entity.setId(UUID.randomUUID().toString());
		entity.setTimeStamp(dto.getTimeStamp());
		entity.setValue(dto.getValue());
		return entity;
	}

	public List<SensorMetrics> metricsDtoListToEntityList(List<SensorMetricsDto> externalList) {
		if(CollectionUtils.isEmpty(externalList)) {
			return Collections.emptyList();
		}
		List<SensorMetrics> internalList = new ArrayList<>();
		SensorMetrics entity = null;
		for (SensorMetricsDto dto : externalList) {
			entity = new SensorMetrics();
			entity.setTimeStamp(dto.getTimeStamp());
			entity.setValue(dto.getValue());
			internalList.add(entity);
		}
		return internalList;
	}

	public List<SensorMetricsDto> metricsEntityListToDtoList(List<SensorMetrics> internalList) {
		if(CollectionUtils.isEmpty(internalList)) {
			return Collections.emptyList();
		}
		List<SensorMetricsDto> externalList = new ArrayList<>();
		SensorMetricsDto dto = null;
		for (SensorMetrics entity : internalList) {
			dto = new SensorMetricsDto();
			dto.setId(entity.getId());
			dto.setTimeStamp(entity.getTimeStamp());
			dto.setValue(entity.getValue());
			externalList.add(dto);
		}
		return externalList;
	}

	//Alerts Converters
	public AlertDto entityToDto(Alert entity) {
		AlertDto dto = new AlertDto();
		dto.setId(entity.getId());
		dto.setTimeStamp(entity.getTimeStamp());
		dto.setBaseWeight(entity.getBaseWeight());
		dto.setSensorWeight((entity.getSensorWeight()));
		return dto;
	}

	public Alert dtoToEntity(AlertDto dto) {
		Alert entity = new Alert();
		if(!StringUtils.isEmpty(dto.getId())) {
			entity.setId(dto.getId());
		}
		entity.setId(UUID.randomUUID().toString());
		entity.setTimeStamp(dto.getTimeStamp());
		entity.setBaseWeight(dto.getBaseWeight());
		entity.setSensorWeight((dto.getSensorWeight()));
		return entity;
	}

	public List<Alert> alertDtoListToEntityList(List<AlertDto> externalList) {
		if(CollectionUtils.isEmpty(externalList)) {
			return Collections.emptyList();
		}
		List<Alert> internalList = new ArrayList<>();
		Alert entity = null;
		for (AlertDto dto : externalList) {
			entity = new Alert();
			entity.setTimeStamp(dto.getTimeStamp());
			entity.setBaseWeight(dto.getBaseWeight());
			entity.setSensorWeight((dto.getSensorWeight()));
			internalList.add(entity);
		}
		return internalList;
	}

	public List<AlertDto> alertEntityListToDtoList(List<Alert> internalList) {
		if(CollectionUtils.isEmpty(internalList)) {
			return Collections.emptyList();
		}
		List<AlertDto> externalList = new ArrayList<>();
		AlertDto dto = null;
		for (Alert entity : internalList) {
			dto = new AlertDto();
			dto.setId(entity.getId());
			dto.setTimeStamp(entity.getTimeStamp());
			dto.setBaseWeight(entity.getBaseWeight());
			dto.setSensorWeight((entity.getSensorWeight()));
			externalList.add(dto);
		}
		return externalList;
	}

}
