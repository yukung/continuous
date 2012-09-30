package continuous.entity;

import java.util.Date;

/**
 * 達成状況エンティティ.
 *
 * @author yukung
 */
public class Practice {
	
	private long id;
	
	private long userId;
	
	private long achievementId;
	
	private Date practicedOn;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public long getAchievementId() {
		return achievementId;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public long getId() {
		return id;
	}
	
	public Date getPracticedOn() {
		return practicedOn;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setAchievementId(long achievementId) {
		this.achievementId = achievementId;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setPracticedOn(Date practicedOn) {
		this.practicedOn = practicedOn;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
