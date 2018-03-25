package io.iot.platform.service;

import java.util.List;

import io.iot.platform.dto.AlertDto;

public interface IAlertService {

    List<AlertDto> getAllAlerts();

    AlertDto save(AlertDto dto);

    List<AlertDto> getAlertsByTimeRange(Long startDate, Long endDate);
}
