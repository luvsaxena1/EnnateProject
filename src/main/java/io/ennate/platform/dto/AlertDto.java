package io.ennate.platform.dto;

public class AlertDto {
	
    private String id;

    private Long timeStamp;

    private int baseWeight;

    private int sensorWeight;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(int baseWeight) {
		this.baseWeight = baseWeight;
	}

	public int getSensorWeight() {
		return sensorWeight;
	}

	public void setSensorWeight(int sensorWeight) {
		this.sensorWeight = sensorWeight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseWeight;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + sensorWeight;
		result = prime * result + ((timeStamp == null) ? 0 : timeStamp.hashCode());
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
		AlertDto other = (AlertDto) obj;
		if (baseWeight != other.baseWeight)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (sensorWeight != other.sensorWeight)
			return false;
		if (timeStamp == null) {
			if (other.timeStamp != null)
				return false;
		} else if (!timeStamp.equals(other.timeStamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AlertDto [id=" + id + ", timeStamp=" + timeStamp + ", baseWeight=" + baseWeight + ", sensorWeight="
				+ sensorWeight + "]";
	}
    
    
}
