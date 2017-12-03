package io.ennate.platform.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.ennate.platform.ApplicationTests;
import io.ennate.platform.entity.SensorMetrics;

public class MetricsRepositoryTest extends ApplicationTests {
	
	@Autowired
	private IMetricsRepository metricsRepository;
	
	@Test
	public void testCreate(){
		
		SensorMetrics metrics = new SensorMetrics();
		metrics.setTimeStamp(1453637383L);
		metrics.setValue(150);
		metricsRepository.create(metrics);
		
		assertThat(metrics).isNotNull();
		assertThat(metrics.getId()).isNotNull();
	}
	
	@Test
	public void testGetByTimeRange(){
		Long startTime = 1455675755L;
		Long endTime = 1567633322323L;
		List<SensorMetrics> sensorMetricsList= metricsRepository.getByTimeRange(startTime, endTime);
		assertThat(sensorMetricsList).isNotNull();
	}
	
	@Test
	public void testFindAll(){
		List<SensorMetrics> sensorMetricsList= metricsRepository.findAll();
		assertThat(sensorMetricsList).isNotNull();
	}
	

}
