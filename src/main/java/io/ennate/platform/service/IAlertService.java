package io.ennate.platform.service;

import io.ennate.platform.dto.AlertDto;

import java.util.List;

public interface IAlertService {

    List<AlertDto> getAllAlerts();

    AlertDto save(AlertDto dto);

    List<AlertDto> getAlertsByTimeRange(Long startDate, Long endDate);
}
