package io.ennate.model;

import org.springframework.stereotype.Component;

@Component
public class AlertTimeRangeBO {

	private Long intialTime;

	private Long endTime;

	public Long getIntialTime() {
		return intialTime;
	}

	public void setIntialTime(Long intialTime) {
		this.intialTime = intialTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endTime == null) ? 0 : endTime.hashCode());
		result = prime * result + ((intialTime == null) ? 0 : intialTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertTimeRangeBO other = (AlertTimeRangeBO) obj;
		if (endTime == null) {
			if (other.endTime != null)
				return false;
		} else if (!endTime.equals(other.endTime))
			return false;
		if (intialTime == null) {
			if (other.intialTime != null)
				return false;
		} else if (!intialTime.equals(other.intialTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertTimeRangeBO [intialTime=" + intialTime + ", endTime=" + endTime + "]";
	}

}
