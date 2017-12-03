package io.ennate.domain;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("alert")
public class Alert {

	@Id
	private ObjectId objectId;

	private Long timeStamp;

	private Integer baseWeight;

	private Integer currentWeight;

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(Integer baseWeight) {
		this.baseWeight = baseWeight;
	}

	public Integer getCurrentWeight() {
		return currentWeight;
	}

	public void setCurrentWeight(Integer currentWeight) {
		this.currentWeight = currentWeight;
	}

	public ObjectId getObjectId() {
		return objectId;
	}

	public void setObjectId(ObjectId objectId) {
		this.objectId = objectId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((baseWeight == null) ? 0 : baseWeight.hashCode());
		result = prime * result + ((currentWeight == null) ? 0 : currentWeight.hashCode());
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
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
		Alert other = (Alert) obj;
		if (baseWeight == null) {
			if (other.baseWeight != null)
				return false;
		} else if (!baseWeight.equals(other.baseWeight))
			return false;
		if (currentWeight == null) {
			if (other.currentWeight != null)
				return false;
		} else if (!currentWeight.equals(other.currentWeight))
			return false;
		if (objectId == null) {
			if (other.objectId != null)
				return false;
		} else if (!objectId.equals(other.objectId))
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
		return "Alert [objectId=" + objectId + ", timeStamp=" + timeStamp + ", baseWeight=" + baseWeight
				+ ", currentWeight=" + currentWeight + "]";
	}
}
