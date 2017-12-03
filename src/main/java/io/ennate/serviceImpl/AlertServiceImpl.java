package io.ennate.serviceImpl;

import java.util.List;

import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.ennate.domain.Alert;
import io.ennate.model.AlertBO;
import io.ennate.repository.AlertRepository;
import io.ennate.service.AlertService;
import io.ennate.transformer.AlertTransformer;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertTransformer alerttransformer;

	@Autowired
	private AlertRepository alertRepo;

	@Override
	public Boolean saveAlert(AlertBO alertBO) {
		Alert alert = alerttransformer.alertBoToAlert(alertBO);
		Key<Alert> alerKey = alertRepo.create(alert);
		if (alerKey != null) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<List<AlertBO>> getAllAlert() {
		List<Alert> allAlertData = alertRepo.read();
		if (allAlertData != null) {
			List<AlertBO> alertBO = alerttransformer.alert_BOTransformer(allAlertData);

			return new ResponseEntity<>(alertBO, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	@Override
	public ResponseEntity<List<AlertBO>> getAlertsByTimeRange(Long startTime, Long endTime) {
		List<Alert> allAlertData = alertRepo.getByTimeRange(startTime, endTime);
		allAlertData.stream().forEach(allMatricsList -> System.out.println(allMatricsList));
		;
		if (allAlertData != null) {
			List<AlertBO> alertBoList = alerttransformer.alert_BOTransformer(allAlertData);
			return new ResponseEntity<>(alertBoList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
