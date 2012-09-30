package continuous.entity;

import java.util.Date;

/**
 * 達成目標エンティティ.
 *
 * @author yukung
 */
public class Achievement {
	
	private Long id;
	
	private Long userId;
	
	private String name;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
