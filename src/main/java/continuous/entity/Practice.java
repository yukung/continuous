package continuous.entity;

import java.util.Date;

/**
 * 達成状況エンティティ.
 *
 * @author yukung
 */
public class Practice {
	
	private Long id;
	
	private Long userId;
	
	private Long achievementId;
	
	private Date practicedOn;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public Long getAchievementId() {
		return achievementId;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Long getId() {
		return id;
	}
	
	public Date getPracticedOn() {
		return practicedOn;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setAchievementId(Long achievementId) {
		this.achievementId = achievementId;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setPracticedOn(Date practicedOn) {
		this.practicedOn = practicedOn;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
