package io.ennate.platform.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.ennate.platform.ApplicationTests;
import io.ennate.platform.entity.Alert;

public class AlertsRepositoryTest extends ApplicationTests {

	@Autowired
	private IAlertsRepository alertsRepository;
	
	@Test
	public void testCreate(){
		
		Alert alerts = new Alert();
		alerts.setTimeStamp(1453637383L);
		alerts.setBaseWeight(150);
		alerts.setSensorWeight(168);
		alertsRepository.create(alerts);
		
		assertThat(alerts).isNotNull();
		assertThat(alerts.getId()).isNotNull();
	}
	
	@Test
	public void testGetByTimeRange(){
		Long startTime = 1455675755L;
		Long endTime = 1567633322323L;
		List<Alert> alertsList= alertsRepository.getByTimeRange(startTime, endTime);
		assertThat(alertsList).isNotNull();
	}
	
	@Test
	public void testFindAll(){
		List<Alert> alertsList= alertsRepository.findAll();
		assertThat(alertsList).isNotNull();
	}
}
