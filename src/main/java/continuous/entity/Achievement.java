package continuous.entity;

import java.util.Date;

/**
 * 達成目標エンティティ.
 *
 * @author yukung
 */
public class Achievement {
	
	private long id;
	
	private long userId;
	
	private String name;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
}
