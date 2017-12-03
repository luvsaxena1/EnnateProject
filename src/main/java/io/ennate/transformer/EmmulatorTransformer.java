package io.ennate.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.ennate.domain.EmmulatorDTO;
import io.ennate.model.EmmulatorBO;

@Component
public class EmmulatorTransformer {

	public EmmulatorDTO emmulatorDTOTransformer(EmmulatorBO bo) {
		EmmulatorDTO dto = new EmmulatorDTO();

		dto.setTimeStamp(bo.getTimeStamp());
		dto.setValue(bo.getValue());

		return dto;
	}

	public List<EmmulatorBO> emmulatorDTO_BOTransformer(List<EmmulatorDTO> emmulatorDtoList) {
		List<EmmulatorBO> emmulatorBoList = new ArrayList<>();
		EmmulatorBO bo = null;
		for (EmmulatorDTO dto : emmulatorDtoList) {
			bo = new EmmulatorBO();
			bo.setTimeStamp(dto.getTimeStamp());
			bo.setValue(dto.getValue());
			emmulatorBoList.add(bo);
		}
		return emmulatorBoList;
	}

}
