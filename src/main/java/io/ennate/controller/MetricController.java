package io.ennate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.ennate.model.EmmulatorBO;
import io.ennate.model.EmmulatorTimeRangeBO;
import io.ennate.service.EmmulatorService;

@RestController
@RequestMapping("/v1/metric")
public class MetricController {

	@Autowired
	private EmmulatorService emmulatorService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricController.class);

	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	public ResponseEntity<Boolean> createEmmulatorData(@RequestBody EmmulatorBO emmulatorBO) {
		LOGGER.info(
				"EmulatorController.getEmmulatorData():: POST Request received with parameters timeStamp={},value={}",
				emmulatorBO.getTimeStamp(), emmulatorBO.getValue());
		Long timeStamp = emmulatorBO.getTimeStamp();
		Integer weightValue = emmulatorBO.getValue();
		Boolean isSave = false;
		if (timeStamp != null && weightValue != null) {
			isSave = emmulatorService.saveEmmulatorData(emmulatorBO);
		} else {
			LOGGER.error(
					"EmulatorController.getEmmulatorData():: POST Request received null values in parameters timeStamp={},value={}",
					emmulatorBO.getTimeStamp(), emmulatorBO.getValue());
			return new ResponseEntity<>(isSave, HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<>(isSave, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/read", produces = "application/json")
	public ResponseEntity<List<EmmulatorBO>> readEmmulatorData() {
		LOGGER.info("EmulatorController.readEmmulatorData():: GET Request to get all matrics data");
		return emmulatorService.getAllMatrics();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/readByTimeRange", consumes = "application/json")
	public ResponseEntity<List<EmmulatorBO>> readEmmulatorDataByTimeRange(@RequestBody EmmulatorTimeRangeBO emmulatorTimeRangeBO) {
		LOGGER.info(
				"EmulatorController.readEmmulatorDataByTimeRange():: POST Request to get all matrics data between time startTime1={}, endTime={}",
				emmulatorTimeRangeBO.getIntialTime(), emmulatorTimeRangeBO.getEndTime());
		ResponseEntity<List<EmmulatorBO>> responseEntity = null;
		Long startTime = emmulatorTimeRangeBO.getIntialTime();
		Long endTime = emmulatorTimeRangeBO.getEndTime();
		if (startTime != null && endTime != null) {
			responseEntity = emmulatorService.getMatricsByTimeRange(startTime, endTime);
		}
		return responseEntity;
	}

}
