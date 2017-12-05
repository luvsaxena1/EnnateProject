package io.ennate.platform.controller;

import io.ennate.platform.dto.SensorMetricsDto;
import io.ennate.platform.rules.WeightRule;
import io.ennate.platform.service.IAlertService;
import io.ennate.platform.service.IMetricsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/metrics")
@Api(value = "Metrics", description = "Sensor Metrics Api")
public class MetricsController {

	@Autowired
	private IMetricsService service;
	
	@Autowired
	private IAlertService alertService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricsController.class);

	@ApiOperation(value = "Create Metrics", notes = "Create a new Metric")
	@RequestMapping(method = RequestMethod.POST, value = "/create", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public SensorMetricsDto save(
			@ApiParam(value = "The Metric to be created") @RequestBody final SensorMetricsDto resource) {
		LOGGER.info("MetricsController.save():: POST Request received with parameters timeStamp={},value={}",
				resource.getTimeStamp(), resource.getValue());
		if (!ObjectUtils.isEmpty(resource)) {
			 runRule(resource);
			return service.save(resource);
		} else {
			throw new IllegalArgumentException("Invalid Metrics Object passed for Create: " + resource);
		}
	}

	@ApiOperation(value = "Find All Metrics", notes = "Find All Metrics")
	@RequestMapping(method = RequestMethod.GET, value = "/read", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public List<SensorMetricsDto> findAllMetrics() {
		LOGGER.info("MetricsController.findAllMetrics():: GET Request to get all metrics data");
		return service.getAllMetrics();
	}

	@ApiOperation(value = "Find Metrics", notes = "Find Metrics within a given time range")
	@RequestMapping(method = RequestMethod.GET, value = "/readByTimeRange")
	@ResponseStatus(HttpStatus.OK)
	public List<SensorMetricsDto> findByTimeRange(
			@ApiParam(value = "The Start Time in Millis") @RequestParam(value = "startDate") final String startDate,
			@ApiParam(value = "The End Time in Millis") @RequestParam(value = "endDate") final String endDate) {
		LOGGER.info(
				"MetricsController.findByTimeRange():: GET Request to get all metrics data between a given time range with parameters startTime={}, endTime={}",
				startDate, endDate);
		if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
			throw new IllegalArgumentException(
					"Bad Start Date or Bad Date was passed: startDate = " + startDate + ", endDate = " + endDate);
		}
		
		if (Long.parseLong(startDate) > Long.parseLong(endDate)) {
			throw new IllegalArgumentException(
					"Start Date should be less then End Date: startDate = " + startDate + ", endDate = " + endDate);
		}

		return service.getMetricsByTimeRange(Long.parseLong(startDate), Long.parseLong(endDate));
	}

	private void runRule(SensorMetricsDto dto) {
		Facts facts = new Facts();
		facts.put("sensorMetric", dto);
		facts.put("service", alertService);
		Rules rules = new Rules(new WeightRule());

		// fire rules on known facts
		RulesEngine rulesEngine = new DefaultRulesEngine();
		rulesEngine.fire(rules, facts);

	}

}
