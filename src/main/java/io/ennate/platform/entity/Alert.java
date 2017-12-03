package io.ennate.platform.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

@Entity("alert")
public class Alert {

	@Id
	@Property("id")
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
	public String toString() {
		return "Alert [id=" + id + ", timeStamp=" + timeStamp + ", baseWeight=" + baseWeight + ", sensorWeight="
				+ sensorWeight + "]";
	}

}
