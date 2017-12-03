package io.ennate.transformer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import io.ennate.domain.Alert;
import io.ennate.model.AlertBO;

@Component
public class AlertTransformer {

	public Alert alertBoToAlert(AlertBO bo) {
		Alert alert = new Alert();

		alert.setTimeStamp(bo.getTimeStamp());
		alert.setBaseWeight(bo.getBaseWeight());
		alert.setCurrentWeight(bo.getCurrentWeight());
		return alert;
	}

	public List<AlertBO> alert_BOTransformer(List<Alert> alertList) {
		List<AlertBO> alertBoList = new ArrayList<>();
		AlertBO bo = null;
		for (Alert alert : alertList) {
			bo = new AlertBO();
			bo.setTimeStamp(alert.getTimeStamp());
			bo.setBaseWeight(alert.getBaseWeight());
			bo.setCurrentWeight(alert.getCurrentWeight());
			alertBoList.add(bo);
		}
		return alertBoList;
	}

}
