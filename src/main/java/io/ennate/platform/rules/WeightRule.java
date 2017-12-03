package io.ennate.platform.rules;

import io.ennate.platform.dto.AlertDto;
import io.ennate.platform.dto.SensorMetricsDto;
import io.ennate.platform.service.IAlertService;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Rule(name = "Weight Rule", description = "Fires if the weight ways by more than 10 percent" )
public class WeightRule {

    @Autowired
    private IAlertService alertService;

    private SensorMetricsDto metric;
    private int baseWeight = 150;

    @Condition
    public boolean weightRule(@Fact("sensorMetric") SensorMetricsDto dto) {
        boolean isWeightDifferent = false;
        this.metric = dto;

        int sensorWeight = dto.getValue();
        if (!StringUtils.isEmpty(sensorWeight)) {
            boolean isOverThreshold = sensorWeight > (baseWeight + ((baseWeight * 10) / 100));
            boolean isUnderThreshold = sensorWeight < (baseWeight - ((baseWeight * 10) / 100));

            if (isOverThreshold || isUnderThreshold) {
                isWeightDifferent = true;
            }
        }
        return isWeightDifferent;
    }

    @Action
    public void persistAlert() {
        AlertDto alert = new AlertDto();
        alert.setBaseWeight(baseWeight);
        alert.setSensorWeight(metric.getValue());
        alert.setTimeStamp(metric.getTimeStamp());
        alertService.save(alert);
    }
}