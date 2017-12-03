package io.ennate.serviceImpl;

import java.util.List;

import org.mongodb.morphia.Key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.ennate.domain.EmmulatorDTO;
import io.ennate.model.EmmulatorBO;
import io.ennate.repository.EmmulatorRepository;
import io.ennate.service.EmmulatorService;
import io.ennate.transformer.EmmulatorTransformer;

@Service
public class EmmulatorServiceImpl implements  EmmulatorService {

//	@Autowired
//	MorphiaService morphyService;
	
	@Autowired
	EmmulatorTransformer emmulatorTransformer;
	
	@Autowired
	private EmmulatorRepository emmulatorRepo;	
	
	
	
	@Override
	public ResponseEntity<List<EmmulatorBO>> getAllMatrics() {
		List<EmmulatorDTO> allMatricsData = emmulatorRepo.read();
		if(allMatricsData !=null){
		List<EmmulatorBO> emmulatorBoList = emmulatorTransformer.emmulatorDTO_BOTransformer(allMatricsData); 
		return new ResponseEntity<>(emmulatorBoList, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@Override
	public Boolean saveEmmulatorData(EmmulatorBO emmulatorBO) {
		EmmulatorDTO emmulatorDTO = emmulatorTransformer.emmulatorDTOTransformer(emmulatorBO);
		Key<EmmulatorDTO> emmulatorDtoKey = emmulatorRepo.create(emmulatorDTO);
		if(emmulatorDtoKey !=null){
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<List<EmmulatorBO>> getMatricsByTimeRange(Long startTime, Long endTime) {
		List<EmmulatorDTO> allMatricsData = emmulatorRepo.getByTimeRange(startTime, endTime);
		allMatricsData.stream().forEach(allMatricsList -> System.out.println(allMatricsList));;
		if(allMatricsData !=null){
			List<EmmulatorBO> emmulatorBoList = emmulatorTransformer.emmulatorDTO_BOTransformer(allMatricsData); 
			return new ResponseEntity<>(emmulatorBoList, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
