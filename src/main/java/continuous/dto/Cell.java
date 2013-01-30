package continuous.dto;

import java.util.Date;

/**
 * 実績カレンダーの1日分のセルを表すオブジェクト.
 *
 * @since 0.0.1 for yukung
 * @version $Id$
 * @author yukung
 */
public class Cell {
	
	/** 対象日付 */
	private Date date;
	
	/** 曜日 */
	private int dayOfWeek;
	
	/** 実施したか */
	private boolean isCarriedOut;
	
	
	public Date getDate() {
		return date;
	}
	
	public int getDayOfWeek() {
		return dayOfWeek;
	}
	
	public boolean isCarriedOut() {
		return isCarriedOut;
	}
	
	public void setCarriedOut(boolean isCarriedOut) {
		this.isCarriedOut = isCarriedOut;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
}
