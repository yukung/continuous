package continuous.dto;

import java.util.Date;
import java.util.Map;

import continuous.entity.Achievement;
import continuous.entity.User;

public class SummaryDto {
	
	private User user;
	
	private Achievement achievement;
	
	private Map<Date, Boolean> status;
	
	
	public Achievement getAchievement() {
		return achievement;
	}
	
	public Map<Date, Boolean> getStatus() {
		return status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
	public void setStatus(Map<Date, Boolean> status) {
		this.status = status;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
