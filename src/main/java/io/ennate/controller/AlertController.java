package io.ennate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ennate.model.AlertBO;
import io.ennate.model.AlertTimeRangeBO;
import io.ennate.service.AlertService;

@RestController
@RequestMapping("/v1/alert")
public class AlertController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricController.class);

	@Autowired
	private AlertService alertService;

	@RequestMapping(method = RequestMethod.GET, value = "/read", produces = "application/json")
	public ResponseEntity<List<AlertBO>> readAlertData() {
		LOGGER.info("AlertController.readAlertData():: GET Request to get all alert data");
		return alertService.getAllAlert();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/readByTimeRange", consumes = "application/json")
	public ResponseEntity<List<AlertBO>> readAlertDataByTimeRange(@RequestBody AlertTimeRangeBO alertTimeRangeBO) {
		LOGGER.info(
				"AlertController.readAlertDataByTimeRange():: POST Request to get all matrics data between time startTime1={}, endTime={}",
				alertTimeRangeBO.getIntialTime(), alertTimeRangeBO.getEndTime());
		ResponseEntity<List<AlertBO>> responseEntity = null;
		Long startTime = alertTimeRangeBO.getIntialTime();
		Long endTime = alertTimeRangeBO.getEndTime();
		if (startTime != null && endTime != null) {
			responseEntity = alertService.getAlertsByTimeRange(startTime, endTime);
		}
		return responseEntity;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	public ResponseEntity<Boolean> createAlertData(@RequestBody AlertBO alertBO) {
		Boolean isSave = false;
		// AlertServiceImpl alertServiceImpl = new AlertServiceImpl();
		isSave = alertService.saveAlert(alertBO);
		return new ResponseEntity<>(isSave, HttpStatus.OK);
	}
}
