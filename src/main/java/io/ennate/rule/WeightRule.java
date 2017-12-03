package io.ennate.rule;

import javax.xml.ws.Action;

import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.ennate.controller.MetricController;
import io.ennate.model.AlertBO;
import io.ennate.model.EmmulatorBO;
import io.ennate.service.AlertService;

@Rule(name = "WeightRule", description = "Check if person's is below or above 10% of his/her intial weight to produce alert")
public class WeightRule {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetricController.class);

	private Integer baseWeight = 150;

	private EmmulatorBO emmulatorBO;

	@Autowired
	private AlertService alertService;

	@Condition
	public boolean isUnderOrOverThreasholdWeight() {
		LOGGER.info("Emulator Object Recieved by RuleEngine has recieved Weight()", emmulatorBO.getValue());

		Integer recievedWeight = emmulatorBO.getValue();
		if (recievedWeight != null) {

			boolean overThreashold = recievedWeight > (baseWeight + ((baseWeight * 10) / 100));

			boolean underThreashold = recievedWeight < (baseWeight - ((baseWeight * 10) / 100));

			if (underThreashold || overThreashold) {
				return true;
			}
		}
		return false;
	}

	@Action
	public void createAlert() {
		AlertBO alertBO = new AlertBO();
		LOGGER.error("Error Found");
		alertBO.setTimeStamp(emmulatorBO.getTimeStamp());
		alertBO.setCurrentWeight(emmulatorBO.getValue());
		alertBO.setBaseWeight(baseWeight);
		// AlertService alertService = new AlertServiceImpl();
		alertService.saveAlert(alertBO);
	}
}
