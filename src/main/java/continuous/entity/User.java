package continuous.entity;

import java.util.Date;

/**
 * ユーザ情報エンティティ.
 *
 * @author yukung
 */
public class User {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String aboutMe;
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public String getAboutMe() {
		return aboutMe;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
