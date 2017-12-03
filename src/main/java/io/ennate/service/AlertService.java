package io.ennate.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.ennate.model.AlertBO;

public interface AlertService {

	public ResponseEntity<List<AlertBO>> getAllAlert();

	public Boolean saveAlert(AlertBO alertBO);

	public ResponseEntity<List<AlertBO>> getAlertsByTimeRange(Long range1, Long range2);
}
