package fuse.sample.cxf.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Driver {

	private Integer id;
	private String name;
	private Integer safetyScore;
	private Integer serviceScore;
	private Integer serviceCredits;
	private Integer rewardPoints;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSafetyScore() {
		return safetyScore;
	}

	public void setSafetyScore(Integer safetyScore) {
		this.safetyScore = safetyScore;
	}

	public Integer getServiceScore() {
		return serviceScore;
	}

	public void setServiceScore(Integer serviceScore) {
		this.serviceScore = serviceScore;
	}

	public Integer getServiceCredits() {
		return serviceCredits;
	}

	public void setServiceCredits(Integer serviceCredits) {
		this.serviceCredits = serviceCredits;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
