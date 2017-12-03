package io.ennate.platform.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.ennate.platform.dto.AlertDto;
import io.ennate.platform.service.IAlertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/v1/alerts")
@Api(value = "Alerts Controller", description = "Sensor Alerts Api")
public class AlertsController {

	@Autowired
	private IAlertService service;

	private static final Logger LOGGER = LoggerFactory.getLogger(AlertsController.class);

	@ApiOperation(value = "Create Alert", notes = "Create a new Alert")
	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public AlertDto save(@ApiParam(value = "The Alert to be created") @RequestBody final AlertDto resource) {
		LOGGER.info(
				"AlertsController.save():: POST Request received with parameters timeStamp={}, baseWeight={}, sensorWeight={}",
				resource.getTimeStamp(), resource.getBaseWeight(), resource.getSensorWeight());
		if (!ObjectUtils.isEmpty(resource)) {
			return service.save(resource);
		} else {
			throw new IllegalArgumentException("Invalid Alerts Object passed for Create: " + resource);
		}
	}

	@ApiOperation(value = "Find All Alerts", notes = "Find All Alerts")
	@RequestMapping(method = RequestMethod.GET, value = "/read", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<AlertDto> findAllAlerts() {
		LOGGER.info("AlertsController.findAllAlerts():: GET Request to get all alerts data");
		return service.getAllAlerts();
	}

	@ApiOperation(value = "Find Alerts", notes = "Find Alerts within a given time range")
	@RequestMapping(method = RequestMethod.GET, value = "/readByTimeRange")
	@ResponseStatus(HttpStatus.OK)
	public List<AlertDto> findByTimeRange(
			@ApiParam(value = "The Start Time in Millis") @RequestParam(value = "startDate") final String startDate,
			@ApiParam(value = "The End Time in Millis") @RequestParam(value = "endDate") final String endDate) {
		LOGGER.info(
				"AlertsController.findByTimeRange():: GET Request to get all alerts data between a given time range with parameters startTime={}, endTime={}",
				startDate, endDate);
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			throw new IllegalArgumentException(
					"Bad Start Date or Bad Date was passed: startDate = " + startDate + ", endDate = " + endDate);
		}
		
		if (Long.parseLong(startDate) > Long.parseLong(endDate)) {
			throw new IllegalArgumentException(
					"Start Date should be less then End Date: startDate = " + startDate + ", endDate = " + endDate);
		}
		return service.getAlertsByTimeRange(Long.parseLong(startDate), Long.parseLong(endDate));
	}

}
