package io.ennate.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.ennate.model.EmmulatorBO;

public interface EmmulatorService {

	public ResponseEntity<List<EmmulatorBO>> getAllMatrics();

	public Boolean saveEmmulatorData(EmmulatorBO emmulatorBO);

	public ResponseEntity<List<EmmulatorBO>> getMatricsByTimeRange(Long range1, Long range2);
}
