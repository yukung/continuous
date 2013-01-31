package continuous.dto;

import java.util.Date;
import java.util.List;

import continuous.entity.Achievement;

/**
 * インデックスページの表示DTOです.
 *
 * @since 0.0.1 yukung
 * @version $Id$
 * @author yukung
 */
public class Summary {
	
	private Achievement achievement;
	
	private Date date;
	
	private List<List<Cell>> practices;
	
	
	public Achievement getAchievement() {
		return achievement;
	}
	
	public List<List<Cell>> getPractices() {
		return practices;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setAchievement(Achievement achievement) {
		this.achievement = achievement;
	}
	
	public void setPractices(List<List<Cell>> practices) {
		this.practices = practices;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
