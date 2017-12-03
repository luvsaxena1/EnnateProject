package io.ennate.platform.service.impl;

import java.util.List;

import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import io.ennate.platform.dto.AlertDto;
import io.ennate.platform.entity.Alert;
import io.ennate.platform.repository.IAlertsRepository;
import io.ennate.platform.service.IAlertService;
import io.ennate.platform.util.ConversionUtil;

@Service
public class AlertsServiceImpl implements IAlertService {
	
	@Autowired
	private ConversionUtil converter;
	
	@Autowired
	private IAlertsRepository dao;

	@Override
	public List<AlertDto> getAllAlerts() {
		List<Alert> results = dao.findAll();
		return converter.alertEntityListToDtoList(results);
	}

	@Override
	public AlertDto save(AlertDto resource) {
		Alert entity = converter.dtoToEntity(resource);
		Key<Alert> savedEntity = dao.create(entity);
		if(!ObjectUtils.isEmpty(savedEntity)){
			return converter.entityToDto(dao.read(savedEntity.getId().toString()));
		}
		return null;
	}

	@Override
	public List<AlertDto> getAlertsByTimeRange(Long startTime, Long endTime) {
		List<Alert> queriedData = dao.getByTimeRange(startTime, endTime);
		return converter.alertEntityListToDtoList(queriedData);
	}
}
